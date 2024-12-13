package com.xxvlang.exception;

public class XXVException extends Exception {

    /* for XenialXmasVigor */
    public static final String MESSAGE_SHORT_ARGUMENT =
        "Error: Source file name is not passed.";
    public static final String MESSAGE_TOO_MUCH_ARGUMENT =
        "Error: Number of argument is wrong.";
    public static final String MESSAGE_FILE_NOT_FOUND =
        "Error: Source file is not found.";

    /*  for XXVInt */
    public static final String MESSAGE_OVERFLOW =
        "Error: A cell overflowed without option.";
    public static final String MESSAGE_LARGE_FOR_CHAR =
        "Error: Casting from int to char failed.";
    public static final String MESSAGE_ILLEGAL_DIGIT =
        "Error: Casting from String to XXVInt failed";
    public static final String MESSAGE_ZERO_DEVIDE =
        "Arithmetic Error: You devided some number by 0(Z).";

    /* others */
    public static final String MESSAGE_OTHER_EXCEPTION =
        """
        Error: Some exception is thrown by JVM.
               Please check following informations.
        """;

    private XXVExceptionType type;

    public XXVExceptionType getType() {
        return this.type;
    }

    public XXVException(XXVExceptionType type) {
        super(type.getMessage());
        this.type = type;
    }
    
}
