package com.xxvlang;

import java.util.ArrayList;

public class Tokenizer {

    public static ArrayList<ArrayList<Integer>> tokenize(String code) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        String[] codeLines = code.split("\r|\n|(\r\n)");
        for (String codeLine : codeLines) {
            result.add(new ArrayList<>());
            for (char c : codeLine.toUpperCase().toCharArray()) {
                if (c >= 'A' && c <= 'X') {
                    result.getLast().add(c - 'A' + 1);
                } else if (c == 'Z') {
                    result.getLast().add(0);
                } else {
                    result.getLast().add(-1);
                }
            }
        }
        return result;
    }
    
    private Tokenizer() {}

}
