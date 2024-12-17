package com.xxvlang;

public enum XXVFlag {

    REVERSE_JUMP_CONDITION(0,'Z'),
    OFFSET_TO_STACK(1,'A'),
    CONNECT_IN_SAME_ORDER(2,'B'),
    PUSH_PHRASE_WITH_LF(3,'C'),
    OUTPUT_AS_XXVINT(4,'D'),
    JUMP_WHEN_EMPTY(5,'E'),
    LF_EVERY_OUTPUT(6,'F'),
    DIGIT_ARG_AS_MOD(7,'G'),
    MOVE_TARGET_REVERSE(8,'H'),
    INPUT_AS_XXVINT(9,'I'),
    INPUT_AS_DECIMAL(10,'J'),
    LITERAL_TO_STACK(11,'K'),
    FIXED_LOOP_STACK(12,'L'),
    JUMP_WHEN_NEGATIVE(13,'M'),
    IS_END(14,'N'),
    CAN_OVERFLOW(15,'O'),
    QUICK_COMPUTE(17,'Q'),
    PC_PUSH_WHEN_JUMP(18,'R'),
    OUTPUT_AS_STRING(19,'O'),
    TRIM_INPUT(20,'T'),
    CAN_FALLBACK_ARG(21,'U'),
    DUP_IN_RANGE(23,'W'),
    IS_XMAS(24,'X');
    
    private int index;
    private char xxvDigit;

    public int getIndex() {
        return this.index;
    }

    public char getDigit() {
        return this.xxvDigit;
    }

    private XXVFlag(int index, char xxvDigit) {
        this.index = index;
        this.xxvDigit = xxvDigit;
    }

}
