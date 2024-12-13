package com.xxvlang;

import java.nio.file.Path;
import java.nio.file.Files;

import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;

import com.xxvlang.data.*;
import com.xxvlang.exception.*;

public class XenialXmasVigor {
    
    public static final String MESSAGE_USAGE = 
        "Usage: java com.xxvlang.XenialXmasVigor <source file>";

    public static final int ERR_CODE_SHORT_ARGUMENT = 1;
    public static final int ERR_CODE_TOO_MUCH_ARGUMENT = 2;
    public static final int ERR_CODE_FILE_NOT_FOUND = 3;
    public static final int ERR_CODE_XXV = 25;
    public static final int ERR_CODE_OTHER = 10;
    
    public static void main(String args[]) {
        if (args.length < 1) {
            System.err.println(XXVException.MESSAGE_SHORT_ARGUMENT);
            System.err.println();
            System.err.println(MESSAGE_USAGE);
            System.exit(ERR_CODE_SHORT_ARGUMENT);
        } else if (args.length > 1) {
            System.err.println(XXVException.MESSAGE_TOO_MUCH_ARGUMENT);
            System.err.println();
            System.err.println(MESSAGE_USAGE);
            System.exit(ERR_CODE_TOO_MUCH_ARGUMENT);
        }

        try {
            String code = Files.readString(Path.of(args[0]));
        } catch (FileNotFoundException fn) {
            System.err.println(XXVException.MESSAGE_FILE_NOT_FOUND);
            System.exit(ERR_CODE_FILE_NOT_FOUND);
        } catch (NoSuchFileException ns) {
            System.err.println(XXVException.MESSAGE_FILE_NOT_FOUND);
            System.exit(ERR_CODE_FILE_NOT_FOUND);
        } catch (XXVException xe) {
            System.err.println(xe.getMessage());
            System.exit(ERR_CODE_XXV);
        } catch (Exception e) {
            System.err.println(XXVException.MESSAGE_OTHER_EXCEPTION);
            e.printStackTrace();
            System.exit(ERR_CODE_OTHER);
        }
    }

}
