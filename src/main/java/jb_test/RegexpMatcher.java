package jb_test;

import java.util.concurrent.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


public class RegexpMatcher {

    public static boolean matches(String text, String regex) {
        Logger logger = Logger.getLogger("regexpParserLogger");

        Pattern pattern;
        try {
            pattern = Pattern.compile(regex);
        } catch (PatternSyntaxException ex) {
            logger.severe("Error: unable to parse regexp");
            return false;
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Boolean> result =
                executor.submit(new Callable<Boolean>() {
                                      public Boolean call() {
                                          try {
                                              Matcher matcher = pattern.matcher(new StringWrapper(text));
                                              return matcher.matches();
                                          } catch (PatternSyntaxException ex) {
                                              logger.severe("Error: expression syntax is invalid");
                                              return false;
                                          }
                                      }
                                      });


        try {
            Boolean answer = result.get(3, TimeUnit.SECONDS);
            executor.shutdownNow();
            return answer;
        }catch (TimeoutException ex) {
            logger.severe("Error: timeout while regexp matching");
        } catch (CancellationException ex) {
            logger.severe("Error: regexp computation was cancelled");
        } catch (ExecutionException ex) {
            logger.severe("Error: execution failed " + ex.getMessage());
        } catch (InterruptedException ex) {
            logger.severe("Error: regexp matching was interrupted");
        } finally {
            result.cancel(true);
            executor.shutdownNow();
        }
        return false;
    }
}
