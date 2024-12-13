package com.xxvlang.exception;

public enum XXVExceptionType {
    
    /* for XXVInt */
    OVERFLOW(XXVException.MESSAGE_OVERFLOW),
    LARGE_FOR_CHAR(XXVException.MESSAGE_LARGE_FOR_CHAR),
    ILLEGAL_DIGIT(XXVException.MESSAGE_ILLEGAL_DIGIT),

    /* others */
    OTHER_EXCEPTION(XXVException.MESSAGE_OTHER_EXCEPTION);

    private String message;

    public String getMessage() {
        return this.message;
    }

    private XXVExceptionType(String message) {
        this.message = message;
    }
    
}
