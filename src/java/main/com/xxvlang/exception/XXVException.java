package com.xxvlang.exception;

public class XXVException extends Exception {

    /* for XenialXmasVigor */
    public static final String MESSAGE_SHORT_ARGUMENT =
        "Error: Source file name is not passed.";
    public static final String MESSAGE_TOO_MUCH_ARGUMENT =
        "Error: Number of argument is wrong.";
    public static final String MESSAGE_FILE_NOT_FOUND =
        "Error: Source file is not found.";

    /* for Statement */
    public static final String MESSAGE_IMPL_ERROR =
        "Implementation error: Wrong usage of class.";

    /* for Parser */
    public static final String MESSAGE_SYNTAX_ERROR =
        "Syntax error: Some syntax of the XXV code are wrong.";

    /* for MachineContext */
    public static final String MESSAGE_WRONG_IMPL =
        "Implementation Error: Wrong usage of internal method.";
    public static final String MESSAGE_ILLEGAL_ARGUMENT =
        "Error: An instruction is given an illegal argument.";
    public static final String MESSAGE_IS_NOT_XMAS =
        "Critical error: You told me a lie! Today is not Christmas!";
    public static final String MESSAGE_ILLEGAL_CHAR_CODE =
        "Error: Cannot resolve char code as UTF-8.";
    
    /*  for XXVInt */
    public static final String MESSAGE_OVERFLOW =
        "Error: A cell overflowed without option.";
    public static final String MESSAGE_TOO_MUCH_STR_DIGITS =
        "Error: Casting from String to XXVInt failed. (Too much digits)";
    public static final String MESSAGE_TOO_MUCH_DIGITS =
        "Error: Casting from byte[] to XXVInt failed. (Too much digits)";
    public static final String MESSAGE_ILLEGAL_STR_DIGIT =
        "Error: Casting from String to XXVInt failed. (Illegal digit)";
    public static final String MESSAGE_ILLEGAL_DIGIT =
        "Error: Casting from byte[] to XXVInt failed. (Illegal digit)";
    public static final String MESSAGE_LARGE_FOR_CHAR =
        "Error: Casting from int to char failed.";
    public static final String MESSAGE_ZERO_DEVIDE =
        "Arithmetic Error: You devided some number by 0(Z).";
    public static final String MESSAGE_ILLEGAL_SHIFT_LENGTH =
        "Error: You can shift XXVInt only 5 digits to either left or right.";

    /* for XXVStack */
    public static final String MESSAGE_STACK_EMPTY =
        "Error: Popped from empty stack.";

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
