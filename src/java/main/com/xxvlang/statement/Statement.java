package com.xxvlang.statement;

import com.xxvlang.exception.XXVException;

public record Statement(
    int subject,
    int instruction,
    int target
) {
    public Statement(int subject, int instruction, int target) {
        if (
            subject < 0 || subject >= 25 ||
            instruction < 0 || instruction >= 25 ||
            target < 0 || target >= 25
        ) {
            System.err.println(XXVException.MESSAGE_IMPL_ERROR);
            System.exit(25);
        }

        this.subject = subject;
        this.instruction = instruction;
        this.target = target;
    }
}
