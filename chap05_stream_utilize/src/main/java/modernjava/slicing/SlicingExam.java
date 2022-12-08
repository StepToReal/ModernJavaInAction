package modernjava.slicing;

import modernjava.Common;
import modernjava.Dish;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.*;

public class SlicingExam {
    public static void main(String[] args) {
        List<Dish> specialMenu = Arrays.asList(
                new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER)
        );

        examTakeWhile(specialMenu);
        examDropWhile(specialMenu);
        examSkip(specialMenu);
        quiz5_1(Common.getDishes());
    }

    private static void quiz5_1(List<Dish> menu) {
        List<Dish> meatMenu = menu.stream()
                .filter(d -> d.getType().equals(Dish.Type.MEAT))
                .skip(2)
                .collect(toList());

        System.out.println(meatMenu);
    }

    private static void examSkip(List<Dish> specialMenu) {
        List<Dish> dishes = specialMenu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList());

        System.out.println(dishes);
    }

    private static void examDropWhile(List<Dish> specialMenu) {
        // find more 320 calories
        System.out.println("<Use Filter>");
        List<Dish> slicedMenu0 = specialMenu.stream()
                .filter(dish -> {
                    System.out.println(dish.getName());
                    return dish.getCalories() > 320;
                })
                .collect(toList());

        //조건이 불일치 하는 순간 조건에 일치하던 요소를 버리고 나머지를 취한다.
        System.out.println("\n<Use dropWhile>");
        List<Dish> slicedMenu1 = specialMenu.stream()
                .dropWhile(dish -> {
                    System.out.println(dish.getName());
                    return dish.getCalories() < 320;
                })
                .collect(toList());
    }

    private static void examTakeWhile(List<Dish> specialMenu) {
        // find less 320 calories
        System.out.println("<Use Filter>");
        List<Dish> slicedMenu0 = specialMenu.stream()
                .filter(dish -> {
                    System.out.println(dish.getName());
                    return dish.getCalories() < 320;
                })
                .collect(toList());

        //조건에 불일치 하는 순간 만족하는 요소만 취한다. filter에 비해 비교군을 줄일 수 있다.
        System.out.println("\n<Use takeWhile>");
        List<Dish> slicedMenu1 = specialMenu.stream()
                .takeWhile(dish -> {
                    System.out.println(dish.getName());
                    return dish.getCalories() < 320;
                })
                .collect(toList());
    }
}
