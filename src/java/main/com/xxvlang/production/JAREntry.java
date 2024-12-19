package com.xxvlang.production;

import com.xxvlang.XenialXmasVigor;

public class JAREntry {

    public static final String MESSAGE_USAGE_JAR =
        "Usage: java -jar xxvlang.jar <source file>";
    
    public static void main(String[] args) {
        XenialXmasVigor._entryPoint(args,MESSAGE_USAGE_JAR);
    }

}
