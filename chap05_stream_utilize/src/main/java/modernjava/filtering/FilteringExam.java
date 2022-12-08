package modernjava.filtering;

import modernjava.Common;
import modernjava.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class FilteringExam {
    public static void main(String[] args) {
        List<Dish> menu = Common.getDishes();

        List<Dish> vegetarianMenu = getVegetarianMenu(menu);
        examUniqueElementFiltering();
    }

    private static void examUniqueElementFiltering() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    private static List<Dish> getVegetarianMenu(List<Dish> menu) {
        return menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());
    }
}
