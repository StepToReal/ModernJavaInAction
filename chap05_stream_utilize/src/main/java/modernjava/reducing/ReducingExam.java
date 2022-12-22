package modernjava.reducing;

import modernjava.Common;
import modernjava.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

public class ReducingExam {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        int multiple = numbers.stream().reduce(1, (a, b) -> a * b);

        System.out.println(sum + ", " + multiple);

        int max = numbers.stream().reduce(Integer::max).orElse(0);

        quiz5_3();
    }

    private static void quiz5_3() {
        List<Dish> menu = Common.getDishes();

        int dishCount = menu.stream().mapToInt(d -> 1).reduce(0, Integer::sum);
        System.out.println(dishCount);

        dishCount = (int)menu.stream().count();
        System.out.println(dishCount);
    }
}
