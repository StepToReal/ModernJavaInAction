package modernjava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FilteringApples {
    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple("RED", 100));
        inventory.add(new Apple("RED", 160));
        inventory.add(new Apple("GREEN", 100));
        inventory.add(new Apple("GREEN", 120));
        inventory.add(new Apple("GREEN", 170));

        System.out.println(filterGreenApples(inventory));
        System.out.println(filterHeavyApples(inventory));
        System.out.println();

        System.out.println(filterApples(inventory, FilteringApples::isGreenApple));
        System.out.println(filterApples(inventory, FilteringApples::isHeavyApple));
        System.out.println();

        System.out.println(filterApples(inventory, (Apple a) -> "GREEN".equals(a.getColor())));
        System.out.println(filterApples(inventory, (Apple a) -> a.getWeight() > 150));
        System.out.println(filterApples(inventory, (Apple a) -> a.getWeight() < 80 || "RED".equals(a.getColor())));
        System.out.println();

        List<Apple> heavyApples = inventory.stream().filter((Apple a) -> a.getWeight() > 150).collect(Collectors.toList());
        System.out.println(heavyApples);

        List<Apple> parallelHeavyApples = inventory.parallelStream()
                .filter((Apple a) -> a.getWeight() > 150)
                .collect(Collectors.toList());
        System.out.println(parallelHeavyApples);

//        Collections.sort();
//        heavyApples.sort();
    }

    static List<Apple> filterApples(List<Apple> inventory, MyPredicate<Apple> p) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static boolean isGreenApple(Apple apple) {
        return "GREEN".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if ("GREEN".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }
}
