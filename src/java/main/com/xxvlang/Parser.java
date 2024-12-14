package com.xxvlang;

import java.util.List;
import java.util.ArrayList;

import com.xxvlang.statement.Statement;
import com.xxvlang.exception.*;

public class Parser {

    public static ArrayList<Statement> parse(List<List<Integer>> orderedTokens)
        throws XXVException
    {
        ArrayList<Integer> tokens = preParse(orderedTokens);
        if (tokens == null) return null;
        if (tokens.size() % 3 != 0) throw new XXVException(XXVExceptionType.SYNTAX_ERROR);
        ArrayList<Statement> result = new ArrayList<>();
        for (int i = 0; i < tokens.size(); i += 3) {
            result.add(new Statement(tokens.get(i),tokens.get(i+1),tokens.get(i+2)));
        }
        return result;
    }

    private static ArrayList<Integer> preParse(List<List<Integer>> orderedTokens) 
        throws XXVException
    {
        ArrayList<Integer> result = new ArrayList<>();
        int treeTop = -1;
        int l = 0;
        for (List<Integer> tokenLine : orderedTokens) {
            if (treeTop < 0) {
                for (int i = 0; i < tokenLine.size(); i++) {
                    int token = tokenLine.get(i);
                    if (token >= 0) {
                        result.add(token);
                        treeTop = i;
                        break;
                    }
                }
            } else {
                l++;
                final int VALID_TOKEN_NUM = l / 3 + l % 3 + 1;
                final int LEAF_LEFT = treeTop - VALID_TOKEN_NUM + 1;
                final int LEAF_RIGHT = treeTop - VALID_TOKEN_NUM + 1;
                int tokenNum = 0;
                boolean isInLeaf = false;
                for (int i = 0; i < tokenLine.size(); i++) {
                    int token = tokenLine.get(i);
                    if (i == LEAF_LEFT) {
                        if (token < 0) {
                            return preParseTrunk(orderedTokens,result,l,LEAF_LEFT,LEAF_RIGHT);
                        }
                        isInLeaf = true;
                    }
                    if (token >= 0) {
                        if (isInLeaf) {
                            result.add(token);
                            tokenNum++;
                            if (tokenLine.get(i+1) < 0) {
                                i++;
                                continue;
                            }
                        } else {
                            throw new XXVException(XXVExceptionType.SYNTAX_ERROR);
                        }
                    }
                    if (i == LEAF_RIGHT) {
                        if (tokenNum == VALID_TOKEN_NUM) {
                            break;
                        } else {
                            throw new XXVException(XXVExceptionType.SYNTAX_ERROR);
                        }
                    }
                }
            }
        }
        if (result.isEmpty()) {
            return null;
        } else {
            throw new XXVException(XXVExceptionType.SYNTAX_ERROR);
        }
    }

    private static ArrayList<Integer> preParseTrunk(
        List<List<Integer>> orderedTokens, ArrayList<Integer> result,
        final int trunkTop, final int leafLeft, final int leafRight
    ) {
        int[] trunkPlace = new int[leafRight];
        int trunkLeft = -1;
        int trunkRight = -1;
        for (int i = trunkTop; i < orderedTokens.size(); i++) {
            List<Integer> tokenLine = orderedTokens.get(i);
            if (i == trunkTop) {
                for (int j = leafLeft + 1; j < leafRight && j < tokenLine.size(); j++) {
                    int token = tokenLine.get(j);
                    if (token >= 0) {
                        result.add(token);
                        trunkPlace[j] = 1;
                        trunkRight = j;
                        if (trunkLeft < 0) trunkLeft = j;
                    }
                }
            } else {
                ArrayList<Integer> tmpList = new ArrayList<>();
                for (int j = trunkLeft; j <= trunkRight && j < tokenLine.size(); j++) {
                    int token = tokenLine.get(j);
                    if (token >= 0) {
                        if (trunkPlace[j] == 1) {
                            tmpList.add(token);
                        } else {
                            return result;
                        }
                    }
                    if (j == trunkRight && !tmpList.isEmpty()) {
                        result.addAll(tmpList);
                        break;
                    }
                }
            }
        }
        return result;
    }
    
    private Parser() {}

}
