package com.xxvlang;

public enum XXVFlag {

    DIGIT_ARG_AS_MOD(7,'G',"digitArgAsMod"),
    MOVE_TARGET_REVERSE(8,'H',"moveTargetReverse"),
    CONNECT_IN_SAME_ORDER(16,'P',"connectInSameOrder"),
    IS_XMAS(24,'X',"isXmas");
    
    private int index;
    private char xxvDigit;
    private String name;

    public int getIndex() {
        return this.index;
    }

    public char getDigit() {
        return this.xxvDigit;
    }

    public String getName() {
        return this.name;
    }

    private XXVFlag(int index, char xxvDigit, String name) {
        this.index = index;
        this.xxvDigit = xxvDigit;
        this.name = name;
    }

}
