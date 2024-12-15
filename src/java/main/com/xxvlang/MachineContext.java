package com.xxvlang;

import java.util.ArrayList;

import com.xxvlang.statement.Statement;
import com.xxvlang.data.*;
import com.xxvlang.exception.*;

public class MachineContext {

    private ArrayList<Statement> program;
    private XXVTrees trees;

    public void connect(Statement statement, XXVTrees trees) throws XXVException {
        trees.connect(statement.target(),statement.subject());
    }

    public void addDigit(Statement statement, XXVTrees trees) throws XXVException {
        trees.pushStack(
            trees.popStack(statement.subject()).shift(1).add(new XXVInt(statement.target())),
            statement.subject()
        );
    }

    public void extractDigit(Statement statement, XXVTrees trees) throws XXVException {
        byte[] resultBytes = new byte[XXVInt.DIGITS_NUM];
        byte[] beforeBytes = trees.popStack(statement.subject()).getDigits();
        int index = statement.target();
        if (trees.getFlag(7)) index %= XXVInt.DIGITS_NUM;
        else if (index >= XXVInt.DIGITS_NUM)
            throw new XXVException(XXVExceptionType.ILLEGAL_ARGUMENT);
        resultBytes[index] = beforeBytes[index];
        trees.pushStack(new XXVInt(resultBytes), statement.subject());
    }
    
    public MachineContext(ArrayList<Statement> program) {
        this.program = program;
        this.trees = new XXVTrees();
    }

}
