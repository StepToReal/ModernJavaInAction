package modernjava.searchmatchine;

import modernjava.Common;
import modernjava.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SearchAndMatchingExam {
    public static void main(String[] args) {
        List<Dish> menu = Common.getDishes();

        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        boolean isHealthy = menu.stream().allMatch(dish -> dish.getCalories() < 1000);

        isHealthy = menu.stream().noneMatch(dish -> dish.getCalories() >= 1000);

        Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();

        // optional의 ifPresent 사용 - 값이 있으면 람다식 실행 없으면 아무것도 실행 안함.
        menu.stream().filter(Dish::isVegetarian).findAny()
                .ifPresent(d -> System.out.println(d.getName()));

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers
                        .stream()
                        .map(n -> n * n)
                        .filter(n -> n % 3 == 0)
                        .findFirst();
    }
}
