package modernjava.repetition;

import modernjava.Common;
import modernjava.Dish;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Repetition {
    public static void main(String[] args) {
        quiz4_1(Common.getDishes());
    }

    private static void testCollectionOuterRepetition(List<Dish> menu) {
        List<String> names = new ArrayList<>();

        //기본적으로 컬렉션은 외부반복 (사용자가 직접 제어)
        for (Dish dish : menu) {
            names.add(dish.getName());
        }

        //아래는 내부적으로 숨겼던 반복자를 사용한 외부 반복
        names = new ArrayList<>();
        Iterator<Dish> iterator = menu.iterator();
        while (iterator.hasNext()) {
            names.add(iterator.next().getName());
        }
    }

    private static void testStreamInnerRepetition(List<Dish> menu) {
        //stream은 내부 반복 (사용자가 직접 제어하지 않음)
        List<String> names = menu.stream()
                .map(Dish::getName)
                .collect(toList());
    }

    private static void quiz4_1(List<Dish> menu) {
        List<String> highCaloricDishes = new ArrayList<>();
        Iterator<Dish> iterator = menu.iterator();
        while (iterator.hasNext()) {
            Dish dish = iterator.next();
            if (dish.getCalories() > 300) {
                highCaloricDishes.add(dish.getName());
            }
        }

        System.out.println(highCaloricDishes);

        highCaloricDishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .collect(toList());

        System.out.println(highCaloricDishes);
    }
}
