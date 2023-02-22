package modernjava.partitioning;

import modernjava.Common;
import modernjava.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class Exam01 {
    public static void main(String[] args) {
        List<Dish> menu = Common.getDishes();

        // partitioning 은 key 값이 true or false 임.
        Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(partitioningBy(Dish::isVegetarian));
        List<Dish> vegetarianDishes = partitionedMenu.get(true);

        // 위와 동일
        List<Dish> vegetarianDishes2 = menu.stream().filter(Dish::isVegetarian).collect(toList());

        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = menu.stream()
                .collect(
                        partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType))
                );

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu.stream()
                .collect(
                        partitioningBy(Dish::isVegetarian,
                                collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)
                        )
                );

        quiz6_2();
    }

    private static void quiz6_2() {
        List<Dish> menu = Common.getDishes();

        Map<Boolean, Map<Boolean, List<Dish>>> vegetarianDishesByCaloric = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        partitioningBy(d -> d.getCalories() > 500))
                );

        Map<Boolean, Long> vegetarianCount = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian, counting()));
    }
}
