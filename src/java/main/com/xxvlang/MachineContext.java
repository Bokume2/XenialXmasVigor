package com.xxvlang;

import java.util.ArrayList;

import com.xxvlang.statement.Statement;
import com.xxvlang.data.*;
import com.xxvlang.exception.*;

import static com.xxvlang.XXVFlag.*;

public class MachineContext {

    private ArrayList<Statement> program;
    private XXVTrees trees;

    public void connect(Statement statement, XXVTrees trees) throws XXVException {
        trees.connect(statement.subject(),statement.argument());
    }

    public void addDigit(Statement statement, XXVTrees trees) throws XXVException {
        XXVInt addedDigit;
        if (
            !trees.getFlag(LITERAL_TO_STACK) || 
            trees.stackIsEmpty(statement.argument()) && trees.getFlag(CAN_FALLBACK_ARG)
        ) {
            addedDigit = new XXVInt(statement.argument());
        } else {
            addedDigit = trees.popStack(statement.argument());
        }
        
        trees.pushStack(
            trees.popStack(statement.subject()).shift(1).add(addedDigit),
            statement.subject()
        );
    }

    public void extractDigit(Statement statement, XXVTrees trees) throws XXVException {
        byte[] resultBytes = new byte[XXVInt.DIGITS_NUM];
        byte[] beforeBytes = trees.popStack(statement.subject()).getDigits();
        int index;
        if (
            !trees.getFlag(LITERAL_TO_STACK) || 
            trees.stackIsEmpty(statement.argument()) && trees.getFlag(CAN_FALLBACK_ARG)
        ) {
            index = statement.argument();
        } else {
            index = trees.popStack(statement.argument()).intValue();
        }

        if (trees.getFlag(DIGIT_ARG_AS_MOD)) {
            index %= XXVInt.DIGITS_NUM;
        } else if (index >= XXVInt.DIGITS_NUM) {
            throw new XXVException(XXVExceptionType.ILLEGAL_ARGUMENT);
        }
        
        resultBytes[index] = beforeBytes[index];
        trees.pushStack(new XXVInt(resultBytes), statement.subject());
    }
    
    public MachineContext(ArrayList<Statement> program) {
        this.program = program;
        this.trees = new XXVTrees();
    }

}
