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

    @Override
    public String toString() {
        char subjectChar = (char)(this.subject != 0 ? this.subject + 'A' - 1 : 'Z');
        char instructionChar = (char)(this.instruction != 0 ? this.instruction + 'A' - 1 : 'Z');
        char targetChar = (char)(this.target != 0 ? this.target + 'A' - 1 : 'Z');
        return "[" + subjectChar + instructionChar + targetChar + "]";
    }
}
