package modernjava.methodrefer;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class ReferMethodQuiz {
    public static void main(String[] args) {
        ToIntFunction<String> stringToInt = (String s) -> Integer.parseInt(s);
        ToIntFunction<String> stringToIntAnswer = Integer::parseInt;

        BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element);
        BiPredicate<List<String>, String> containsAnswer = List::contains;

    }

    private void test() {
        Predicate<String> startWithNumber = (String s) -> this.startsWithNumber(s);
        Predicate<String> startWithNumberAnswer = this::startsWithNumber;
    }

    private boolean startsWithNumber(String s) {
        try {
            Integer.parseInt(s.substring(0, 1));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
