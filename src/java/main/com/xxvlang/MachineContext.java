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
    
    public MachineContext(ArrayList<Statement> program) {
        this.program = program;
        this.trees = new XXVTrees();
    }

}
