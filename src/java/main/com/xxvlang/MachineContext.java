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
            opr = trees.popStack(sub).add(new XXVInt(arg));
            opl = trees.popStack(sub);
        }
        trees.pushStack(opl.add(opr),sub);
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
