package com.xxvlang;

import java.util.List;
import java.util.ArrayList;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.Month;

import com.xxvlang.statement.Statement;
import com.xxvlang.data.*;
import com.xxvlang.exception.*;

import static com.xxvlang.XXVFlag.*;

public class MachineContext {

    private ArrayList<Statement> program;
    private XXVTrees trees;

    public static void pushZero(Statement statement, XXVTrees trees) {
        for (int i = 0; i < statement.argument(); i++) {
            try {
                trees.pushStack(new XXVInt(0),statement.subject());
            } catch (XXVException xe) {
                // never thrown exception
            }
        }
    }

    public static void add(Statement statement, XXVTrees trees) throws XXVException {
        int sub = statement.subject();
        XXVInt offset = calcOffsetArg(statement.argument(),trees);
        XXVInt opr = trees.popStack(sub).add(offset);
        XXVInt opl = trees.popStack(sub);
        trees.pushStack(opl.add(opr,trees.getFlag(CAN_OVERFLOW)),sub);
    }

    public static void connect(Statement statement, XXVTrees trees) throws XXVException {
        trees.connect(statement.subject(),statement.argument());
    }

    public static void addDigit(Statement statement, XXVTrees trees) throws XXVException {
        XXVInt addedDigit = calcLiteralArg(statement.argument(),trees);
        trees.pushStack(
            trees.popStack(statement.subject()).shift(1).add(addedDigit),
            statement.subject()
        );
    }

    public static void divide(Statement statement, XXVTrees trees) throws XXVException {
        int sub = statement.subject();
        XXVInt offset = calcOffsetArg(statement.argument(),trees);
        XXVInt opr = trees.popStack(sub).add(offset);
        XXVInt opl = trees.popStack(sub);
        trees.pushStack(opl.divide(opr,trees.getFlag(CAN_OVERFLOW)),sub);
    }

    public static void exponentiate(Statement statement, XXVTrees trees) throws XXVException {
        int sub = statement.subject();
        XXVInt offset = calcOffsetArg(statement.argument(),trees);
        XXVInt opr = trees.popStack(sub).add(offset);
        XXVInt opl = trees.popStack(sub);
        trees.pushStack(opl.exponentiate(
            opr,trees.getFlag(CAN_OVERFLOW),trees.getFlag(QUICK_COMPUTE)
        ),sub);
    }

    public static void extractDigit(Statement statement, XXVTrees trees) throws XXVException {
        byte[] resultBytes = new byte[XXVInt.DIGITS_NUM];
        byte[] beforeBytes = trees.popStack(statement.subject()).getDigits();
        int index = calcLiteralArg(statement.argument(),trees).intValue();
        if (trees.getFlag(DIGIT_ARG_AS_MOD)) {
            index %= XXVInt.DIGITS_NUM;
        } else if (index >= XXVInt.DIGITS_NUM) {
            throw new XXVException(XXVExceptionType.ILLEGAL_ARGUMENT);
        }
        resultBytes[index] = beforeBytes[index];
        trees.pushStack(new XXVInt(resultBytes), statement.subject());
    }

    public static void move(Statement statement, XXVTrees trees) throws XXVException {
        trees.move(statement.subject(),statement.argument(),1);
    }

    public static void helloworld(Statement statement, XXVTrees trees) {
        throw new UnsupportedOperationException("Critical Error: Not implemented.");
    }

    public static void input(Statement statement, XXVTrees trees) throws XXVException {
        throw new UnsupportedOperationException("Critical Error: Not implemented.");
    }

    public static void jump(Statement statement, XXVTrees trees) throws XXVException {
        boolean condition;
        if (trees.stackIsEmpty(statement.subject()) && trees.getFlag(JUMP_WHEN_EMPTY)) {
            condition = true;
        } else {
            int criteria = trees.popStack(statement.subject()).intValue();
            condition = (
               criteria > 0 && !trees.getFlag(JUMP_WHEN_NEGATIVE) ||
               criteria < 0 && trees.getFlag(JUMP_WHEN_NEGATIVE)
            );
        }
        if (trees.getFlag(REVERSE_JUMP_CONDITION)) condition = !condition;
        if (condition) trees.setPC(trees.popStack(statement.argument()).intValue());
    }

    public static void shift(Statement statement, XXVTrees trees) throws XXVException {
        int length = calcLiteralArg(statement.argument(),trees).intValue();
        if (trees.getFlag(DIGIT_ARG_AS_MOD)) length %= XXVInt.DIGITS_NUM;
        trees.pushStack(trees.popStack(statement.subject()).shift(length),statement.subject());
    }

    public static void pushPC(Statement statement, XXVTrees trees) throws XXVException {
        trees.pushPC(statement.subject(),calcOffsetArg(statement.argument(),trees).intValue());
    }

    public static void multiply(Statement statement, XXVTrees trees) throws XXVException {
        int sub = statement.subject();
        XXVInt offset = calcOffsetArg(statement.argument(),trees);
        XXVInt opr = trees.popStack(sub).add(offset);
        XXVInt opl = trees.popStack(sub);
        trees.pushStack(opl.multiply(
            opr,trees.getFlag(CAN_OVERFLOW),trees.getFlag(QUICK_COMPUTE)
        ),sub);
    }

    public static void reverseFlag(Statement statement, XXVTrees trees) {
        trees.reverseFlag(statement.argument());
    }

    public static void output(Statement statement, XXVTrees trees) throws XXVException {
        throw new UnsupportedOperationException("Critical Error: Not implemented.");
    }

    public static void push(Statement statement, XXVTrees trees) throws XXVException {
        trees.pushStack(calcLiteralArg(statement.argument(),trees),statement.subject());
    }

    public static void modulo(Statement statement, XXVTrees trees) throws XXVException {
        int sub = statement.subject();
        XXVInt offset = calcOffsetArg(statement.argument(),trees);
        XXVInt opr = trees.popStack(sub).add(offset);
        XXVInt opl = trees.popStack(sub);
        trees.pushStack(opl.modulo(opr,trees.getFlag(CAN_OVERFLOW)),sub);
    }

    public static void floatToTop(Statement statement, XXVTrees trees) throws XXVException {
        throw new UnsupportedOperationException("Critical Error: Not implemented.");
    }

    public static void subtract(Statement statement, XXVTrees trees) throws XXVException {
        int sub = statement.subject();
        XXVInt offset = calcOffsetArg(statement.argument(),trees);
        XXVInt opr = trees.popStack(sub).add(offset);
        XXVInt opl = trees.popStack(sub);
        trees.pushStack(opl.subtract(opr,trees.getFlag(CAN_OVERFLOW)),sub);
    }

    public static void trash(Statement statement, XXVTrees trees) throws XXVException {
        throw new UnsupportedOperationException("Critical Error: Not implemented.");
    }

    public static void pushRandom(Statement statement, XXVTrees trees) throws XXVException {
        throw new UnsupportedOperationException("Critical Error: Not implemented.");
    }

    public static void pushSize(Statement statement, XXVTrees trees) {
        throw new UnsupportedOperationException("Critical Error: Not implemented.");
    }

    public static void dup(Statement statement, XXVTrees trees) throws XXVException {
        trees.dupStack(calcLiteralArg(statement.argument(),trees).intValue(),statement.subject());
    }

    public static void merrychristmas(Statement statement, XXVTrees trees) {
        throw new UnsupportedOperationException("Critical Error: Not implemented.");
    }

    private static void checkXmas() throws XXVException {
        ZonedDateTime today = ZonedDateTime.now(ZoneOffset.UTC);
        boolean isReallyXmas = today.getMonth() == Month.DECEMBER && today.getDayOfMonth() == 25;
        if (!isReallyXmas) throw new XXVException(XXVExceptionType.IS_NOT_XMAS);
    }

    private static boolean canUse_ToStack(XXVFlag flag, int index, XXVTrees trees) {
        return (
            trees.getFlag(flag) &&
            (!trees.stackIsEmpty(index) || !trees.getFlag(CAN_FALLBACK_ARG))
        );
    }

    private static XXVInt calcOffsetArg(int arg, XXVTrees trees) throws XXVException {
        if (canUse_ToStack(OFFSET_TO_STACK,arg,trees)) {
            return trees.popStack(arg);
        } else {
            return new XXVInt(arg);
        }
    }

    private static XXVInt calcLiteralArg(int arg, XXVTrees trees) throws XXVException {
        if (canUse_ToStack(LITERAL_TO_STACK,arg,trees)) {
            return trees.popStack(arg);
        } else {
            return new XXVInt(arg);
        }
    }

    public void run() throws XXVException {
        run(this.program,this.trees);
    }

    public static void run(List<Statement> program, XXVTrees trees) throws XXVException {
        while (!trees.getFlag(IS_END) && trees.getPC() < program.size()) {
            if (trees.getFlag(IS_XMAS)) {
                checkXmas();
            }
            exec(program.get(trees.getPC()),trees);
        }
    }

    public static void run(List<Statement> program) throws XXVException {
        run(program,new XXVTrees());
    }

    public static void exec(Statement statement, XXVTrees trees) throws XXVException {
        if (!trees.getFlag(IS_END)) {
            switch (statement.instruction()) {
                case 0 -> pushZero(statement,trees);
                case 1 -> add(statement,trees);
                case 2 -> connect(statement,trees);
                case 3 -> addDigit(statement,trees);
                case 4 -> divide(statement,trees);
                case 5 -> exponentiate(statement,trees);
                case 6 -> extractDigit(statement,trees);
                case 7 -> move(statement,trees);
                case 8 -> helloworld(statement,trees);
                case 9 -> input(statement,trees);
                case 10 -> jump(statement,trees);
                case 11 -> shift(statement,trees);
                case 12 -> pushPC(statement,trees);
                case 13 -> multiply(statement,trees);
                case 14 -> reverseFlag(statement,trees);
                case 15 -> output(statement,trees);
                case 16 -> push(statement,trees);
                case 17 -> modulo(statement,trees);
                case 18 -> floatToTop(statement,trees);
                case 19 -> subtract(statement,trees);
                case 20 -> trash(statement,trees);
                case 21 -> pushRandom(statement,trees);
                case 22 -> pushSize(statement,trees);
                case 23 -> dup(statement,trees);
                case 24 -> merrychristmas(statement,trees);
                
                default -> 
                    throw new UnsupportedOperationException(XXVException.MESSAGE_WRONG_IMPL);
            }
        }
    }
    
    public MachineContext(ArrayList<Statement> program) {
        this.program = program;
        this.trees = new XXVTrees();
    }

}
