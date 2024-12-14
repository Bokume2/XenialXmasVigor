package com.xxvlang;

import java.nio.file.Path;
import java.nio.file.Files;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;

import com.xxvlang.statement.*;
import com.xxvlang.exception.*;

public class XenialXmasVigor {

    public static final HashMap<Character,String> INSTS_MAP;
    static {
        INSTS_MAP = new HashMap<>();
        INSTS_MAP.put('Z',"pushZero");
        INSTS_MAP.put('A',"add");
        INSTS_MAP.put('B',"connect");
        INSTS_MAP.put('C',"addDigit");
        INSTS_MAP.put('D',"divide");
        INSTS_MAP.put('E',"exponentiate");
        INSTS_MAP.put('F',"extractDigit");
        INSTS_MAP.put('G',"move");
        INSTS_MAP.put('H',"helloworld");
        INSTS_MAP.put('I',"input");
        INSTS_MAP.put('J',"jump");
        INSTS_MAP.put('K',"shift");
        INSTS_MAP.put('L',"pushPC");
        INSTS_MAP.put('M',"multiply");
        INSTS_MAP.put('N',"reverseFlag");
        INSTS_MAP.put('O',"output");
        INSTS_MAP.put('P',"push");
        INSTS_MAP.put('Q',"modulo");
        INSTS_MAP.put('R',"floatToTop");
        INSTS_MAP.put('S',"subtract");
        INSTS_MAP.put('T',"trash");
        INSTS_MAP.put('U',"pushRandom");
        INSTS_MAP.put('V',"pushSize");
        INSTS_MAP.put('W',"dup");
        INSTS_MAP.put('X',"merrychristmas");
    }
    
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
            ArrayList<Statement> program = Parser.parse(Tokenizer.tokenize(code));
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
