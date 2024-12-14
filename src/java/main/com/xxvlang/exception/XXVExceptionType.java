package com.xxvlang.exception;

public enum XXVExceptionType {

    /* for Parser */
    SYNTAX_ERROR(XXVException.MESSAGE_SYNTAX_ERROR),
    
    /* for XXVInt */
    OVERFLOW(XXVException.MESSAGE_OVERFLOW),
    TOO_MUCH_STR_DIGITS(XXVException.MESSAGE_TOO_MUCH_STR_DIGITS),
    TOO_MUCH_DIGITS(XXVException.MESSAGE_TOO_MUCH_DIGITS),
    ILLEGAL_STR_DIGIT(XXVException.MESSAGE_ILLEGAL_STR_DIGIT),
    ILLEGAL_DIGIT(XXVException.MESSAGE_ILLEGAL_DIGIT),
    LARGE_FOR_CHAR(XXVException.MESSAGE_LARGE_FOR_CHAR),

    /* for XXVStack */
    STACK_EMPTY(XXVException.MESSAGE_STACK_EMPTY),

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
