package jb_test;

import org.junit.jupiter.api.Test;

import static jb_test.RegexpMatcher.matches;
import static org.junit.jupiter.api.Assertions.*;

public class TestRegexpMatching {

    @Test
    public void simpleRegexpsTest() {
        assertTrue(matches("hehe", "hehe"));
        assertTrue(matches("10:56", "(2[0-3]|[0-1]\\d):[0-5]\\d"));
        assertFalse(matches("10:666", "(2[0-3]|[0-1]\\d):[0-5]\\d"));
    }

    @Test
    public void incorrectRegexpFailsTest() {
        assertFalse(matches("10:56", "(2[0-3]|[0-1]\\d):[0-5]\\d\\\\.\\"));
    }

    @Test
    public void regextOutOfTimeFailsTest() {
        assertFalse(matches(randomAlphaNumeric(403403), ".*HUNG.* .* PHONE|PRICE.*"));
    }

    public static String randomAlphaNumeric(int count) {
        String ALPHA_NUMERIC_STRING = "ABC DEFGH IJKLM NOPQR STUVWXY Z 01 234 567 89";
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
