package com.xxvlang;

public enum XXVFlag {

    ARITH_ARG_AS_STACK(1,'A'),
    CONNECT_IN_SAME_ORDER(2,'B'),
    OUTPUT_AS_CHAR(3,'C'),
    DIGIT_ARG_AS_MOD(7,'G'),
    MOVE_TARGET_REVERSE(8,'H'),
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
