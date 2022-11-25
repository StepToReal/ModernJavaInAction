package modernjava.methodrefer;

import modernjava.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;
import static modernjava.Type.GREEN;
import static modernjava.Type.RED;

public class ReferMethodExam {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(GREEN, 100),
                new Apple(RED, 200)
        );

        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        inventory.sort(comparing(Apple::getWeight)); // 이게 메서드참조

        //메서드 참조 = 특정 메서드만을 호출하는 람다의 축양형

        List<String> list = Arrays.asList("Add", "minus", "Multi", "divide", "Equal");

        System.out.println(filter(list, ReferMethodExam::isValidName)); // (String s) -> Character.isUpperCase(s.charAt(0))
    }

    private static boolean isValidName(String s) {
        return Character.isUpperCase(s.charAt(0));
    }

    private static List<String> filter(List<String> list, Predicate<String> p) {
        List<String> result = new ArrayList<>();

        for (String s : list) {
            if (p.test(s)) result.add(s);
        }

        return result;
    }
}
