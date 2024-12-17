package com.xxvlang.statement;

import com.xxvlang.exception.XXVException;

public record Statement(
    int subject,
    int instruction,
    int argument
) {
    public Statement(int subject, int instruction, int argument) {
        if (
            subject < 0 || subject >= 25 ||
            instruction < 0 || instruction >= 25 ||
            argument < 0 || argument >= 25
        ) {
            System.err.println(XXVException.MESSAGE_IMPL_ERROR);
            System.exit(25);
        }

        this.subject = subject;
        this.instruction = instruction;
        this.argument = argument;
    }

    @Override
    public String toString() {
        char subjectChar = (char)(this.subject != 0 ? this.subject + 'A' - 1 : 'Z');
        char instructionChar = (char)(this.instruction != 0 ? this.instruction + 'A' - 1 : 'Z');
        char argumentChar = (char)(this.argument != 0 ? this.argument + 'A' - 1 : 'Z');
        return "[" + subjectChar + instructionChar + argumentChar + "]";
    }
}
