package jb_test;

import java.util.Set;
import java.util.concurrent.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static jb_test.RegexpMatcher.matches;

public class Main {
    private static final String ALPHA_NUMERIC_STRING = "ABC DEFGH IJKLM NOPQR STUVWXY Z 01 234 567 89";

    public static void main(String[] args) throws InterruptedException {
        System.out.println("looking for pattern : ");
        boolean match = matches(randomAlphaNumeric(403403), ".*HUNG.* .* PHONE|PRICE.*");
        System.out.println("string matches pattern: " + match);

    }
    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
            if(count % 1000 == 0){
                builder.append("HUNG");
            }
        }
        return builder.toString();
    }
}