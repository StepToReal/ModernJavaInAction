package modernjava.predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateExam {

    public static void main(String[] args) {
        new PredicateExam().execute();
    }

    public void execute() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

        List<Integer> evenNumList = filter(list, (Integer n) -> n % 2 == 0);
        System.out.println(evenNumList);

        List<Integer> oddNumList = filter(list, (n) -> n % 2 != 0);
        System.out.println(oddNumList);
    }

    public <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();

        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }

        return result;
    }
}
