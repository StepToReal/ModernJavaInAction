package modernjava;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println(filterApples(inventory, FilteringApples::isGreenApple));
        System.out.println(filterApples(inventory, FilteringApples::isHeavyApple));
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
