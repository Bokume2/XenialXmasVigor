package com.xxvlang;

import java.util.ArrayList;

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
        int sub = statement.subject(), arg = statement.argument();
        XXVInt opl, opr;
        if (canUse_ToStack(OFFSET_TO_STACK,arg,trees)) {
            opl = trees.popStack(sub);
            opr = trees.popStack(arg);
        } else {
            opr = trees.popStack(sub).add(new XXVInt(arg),trees.getFlag(CAN_OVERFLOW));
            opl = trees.popStack(sub);
        }
        trees.pushStack(opl.add(opr,trees.getFlag(CAN_OVERFLOW)),sub);
    }

    public static void connect(Statement statement, XXVTrees trees) throws XXVException {
        trees.connect(statement.subject(),statement.argument());
    }

    public static void addDigit(Statement statement, XXVTrees trees) throws XXVException {
        XXVInt addedDigit;
        if (canUse_ToStack(LITERAL_TO_STACK,statement.argument(),trees)) {
            addedDigit = trees.popStack(statement.argument());
        } else {
            addedDigit = new XXVInt(statement.argument());
        }
        
        trees.pushStack(
            trees.popStack(statement.subject()).shift(1).add(addedDigit),
            statement.subject()
        );
    }

    public static void divide(Statement statement, XXVTrees trees) throws XXVException {
        int sub = statement.subject(), arg = statement.argument();
        XXVInt opl, opr;
        if (canUse_ToStack(OFFSET_TO_STACK,arg,trees)) {
            opl = trees.popStack(sub);
            opr = trees.popStack(arg);
        } else {
            opr = trees.popStack(sub).divide(new XXVInt(arg),trees.getFlag(CAN_OVERFLOW));
            opl = trees.popStack(sub);
        }
        trees.pushStack(opl.divide(opr,trees.getFlag(CAN_OVERFLOW)),sub);
    }

    public static void exponentiate(Statement statement, XXVTrees trees) throws XXVException {
        int sub = statement.subject(), arg = statement.argument();
        XXVInt opl, opr;
        if (canUse_ToStack(OFFSET_TO_STACK,arg,trees)) {
            opl = trees.popStack(sub);
            opr = trees.popStack(arg);
        } else {
            opr = trees.popStack(sub).exponentiate(
                new XXVInt(arg),trees.getFlag(CAN_OVERFLOW),trees.getFlag(QUICK_COMPUTE)
            );
            opl = trees.popStack(sub);
        }
        trees.pushStack(opl.exponentiate(
            opr,trees.getFlag(CAN_OVERFLOW),trees.getFlag(QUICK_COMPUTE)
        ),sub);
    }

    public static void extractDigit(Statement statement, XXVTrees trees) throws XXVException {
        byte[] resultBytes = new byte[XXVInt.DIGITS_NUM];
        byte[] beforeBytes = trees.popStack(statement.subject()).getDigits();
        int index;
        if (canUse_ToStack(LITERAL_TO_STACK,statement.argument(),trees)) {
            index = trees.popStack(statement.argument()).intValue();
        } else {
            index = statement.argument();
        }

        if (trees.getFlag(DIGIT_ARG_AS_MOD)) {
            index %= XXVInt.DIGITS_NUM;
        } else if (index >= XXVInt.DIGITS_NUM) {
            throw new XXVException(XXVExceptionType.ILLEGAL_ARGUMENT);
        }
        
        resultBytes[index] = beforeBytes[index];
        trees.pushStack(new XXVInt(resultBytes), statement.subject());
    }

    public static void move(Statement statement, XXVTrees trees) throws XXVException {
        throw new UnsupportedOperationException("Critical Error: Not implemented.");
    }

    public static void helloworld(Statement statement, XXVTrees trees) {
        throw new UnsupportedOperationException("Critical Error: Not implemented.");
    }

    public static void input(Statement statement, XXVTrees trees) throws XXVException {
        throw new UnsupportedOperationException("Critical Error: Not implemented.");
    }

    public static void jump(Statement statement, XXVTrees trees) throws XXVException {
        throw new UnsupportedOperationException("Critical Error: Not implemented.");
    }

    public static void shift(Statement statement, XXVTrees trees) throws XXVException {
        throw new UnsupportedOperationException("Critical Error: Not implemented.");
    }

    public static void pushPC(Statement statement, XXVTrees trees) throws XXVException {
        throw new UnsupportedOperationException("Critical Error: Not implemented.");
    }

    public static void multiply(Statement statement, XXVTrees trees) throws XXVException {
        int sub = statement.subject(), arg = statement.argument();
        XXVInt opl, opr;
        if (canUse_ToStack(OFFSET_TO_STACK,arg,trees)) {
            opl = trees.popStack(sub);
            opr = trees.popStack(arg);
        } else {
            opr = trees.popStack(sub).multiply(
                new XXVInt(arg),trees.getFlag(CAN_OVERFLOW),trees.getFlag(QUICK_COMPUTE)
            );
            opl = trees.popStack(sub);
        }
        trees.pushStack(opl.multiply(
            opr,trees.getFlag(CAN_OVERFLOW),trees.getFlag(QUICK_COMPUTE)
        ),sub);
    }

    public static void reverseFlag(Statement statement, XXVTrees trees) {
        throw new UnsupportedOperationException("Critical Error: Not implemented.");
    }

    public static void output(Statement statement, XXVTrees trees) throws XXVException {
        throw new UnsupportedOperationException("Critical Error: Not implemented.");
    }

    public static void push(Statement statement, XXVTrees trees) throws XXVException {
        throw new UnsupportedOperationException("Critical Error: Not implemented.");
    }

    public static void modulo(Statement statement, XXVTrees trees) throws XXVException {
        int sub = statement.subject(), arg = statement.argument();
        XXVInt opl, opr;
        if (canUse_ToStack(OFFSET_TO_STACK,arg,trees)) {
            opl = trees.popStack(sub);
            opr = trees.popStack(arg);
        } else {
            opr = trees.popStack(sub).modulo(new XXVInt(arg),trees.getFlag(CAN_OVERFLOW));
            opl = trees.popStack(sub);
        }
        trees.pushStack(opl.modulo(opr,trees.getFlag(CAN_OVERFLOW)),sub);
    }

    public static void floatToTop(Statement statement, XXVTrees trees) throws XXVException {
        throw new UnsupportedOperationException("Critical Error: Not implemented.");
    }

    public static void subtract(Statement statement, XXVTrees trees) throws XXVException {
        int sub = statement.subject(), arg = statement.argument();
        XXVInt opl, opr;
        if (canUse_ToStack(OFFSET_TO_STACK,arg,trees)) {
            opl = trees.popStack(sub);
            opr = trees.popStack(arg);
        } else {
            opr = trees.popStack(sub).subtract(new XXVInt(arg),trees.getFlag(CAN_OVERFLOW));
            opl = trees.popStack(sub);
        }
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
        throw new UnsupportedOperationException("Critical Error: Not implemented.");
    }

    public static void merrychristmas(Statement statement, XXVTrees trees) {
        throw new UnsupportedOperationException("Critical Error: Not implemented.");
    }

    private static boolean canUse_ToStack(XXVFlag flag, int index, XXVTrees trees) {
        return (
            trees.getFlag(flag) &&
            !trees.stackIsEmpty(index) || !trees.getFlag(CAN_FALLBACK_ARG)
        );
    }
    
    public MachineContext(ArrayList<Statement> program) {
        this.program = program;
        this.trees = new XXVTrees();
    }

}
