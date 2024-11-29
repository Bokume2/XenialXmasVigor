package com.xxvlang;

import java.nio.file.Path;
import java.nio.file.Files;

import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;

public class XenialXmasVigor {
    
    public static final String MESSAGE_USAGE = 
        "Usage: java com.xxvlang.XenialXmasVigor <source>";
    
    public static final String MESSAGE_SHORT_ARGUMENT =
        "Error: Source file name is not passed.";
    public static final String MESSAGE_TOO_MUCH_ARGUMENT =
        "Error: Number of argument is wrong.";
    public static final String MESSAGE_FILE_NOT_FOUND =
        "Error: Source file is not found.";
    public static final String MESSAGE_OTHER_EXCEPTION =
        """
        Error: Some exception is thrown by JVM.
               Please check following informations.
        """;

    public static final int ERR_CODE_SHORT_ARGUMENT = 1;
    public static final int ERR_CODE_TOO_MUCH_ARGUMENT = 2;
    public static final int ERR_CODE_FILE_NOT_FOUND = 3;
    public static final int ERR_CODE_OTHER = 10;
    
    public static void main(String args[]) {
        if (args.length < 1) {
            System.err.println(MESSAGE_SHORT_ARGUMENT);
            System.err.println();
            System.err.println(MESSAGE_USAGE);
            System.exit(ERR_CODE_SHORT_ARGUMENT);
        } else if (args.length > 1) {
            System.err.println(MESSAGE_TOO_MUCH_ARGUMENT);
            System.err.println();
            System.err.println(MESSAGE_USAGE);
            System.exit(ERR_CODE_TOO_MUCH_ARGUMENT);
        }

        try {
            String code = Files.readString(Path.of(args[0]));
        } catch (FileNotFoundException fn) {
            System.err.println(MESSAGE_FILE_NOT_FOUND);
            System.exit(ERR_CODE_FILE_NOT_FOUND);
        } catch (NoSuchFileException ns) {
            System.err.println(MESSAGE_FILE_NOT_FOUND);
            System.exit(ERR_CODE_FILE_NOT_FOUND);
        } catch (Exception e) {
            System.err.println(MESSAGE_OTHER_EXCEPTION);
            e.printStackTrace();
            System.exit(ERR_CODE_OTHER);
        }
    }

}
