package modernjava;

import modernjava.predicate.AppleGreenColorPredicate;
import modernjava.predicate.AppleHeavyPredicate;
import modernjava.predicate.ApplePredicate;
import modernjava.prettyprint.AppleColorAndWeightPrint;
import modernjava.prettyprint.AppleColorPrint;
import modernjava.prettyprint.ApplePrint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Predicate;

public class FilteringApples {

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>() {{
            add(new Apple(Color.GREEN, 100));
            add(new Apple(Color.GREEN, 150));
            add(new Apple(Color.RED, 120));
            add(new Apple(Color.RED, 200));
        }};

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        System.out.println(filterGreenApples(inventory));
        System.out.println(filterApplesByColor(inventory, Color.RED));

        System.out.println("\n<<<filterApples>>>");
        System.out.println(filterApples(inventory, new AppleHeavyPredicate()));
        System.out.println(filterApples(inventory, new AppleGreenColorPredicate()));
        System.out.println(filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return Color.RED.equals(apple.getColor());
            }
        }));

        System.out.println("\n<<<prettyPrintApple>>>");
        prettyPrintApple(inventory, new AppleColorPrint());
        prettyPrintApple(inventory, new AppleColorAndWeightPrint());

        System.out.println("\n<<<lambda express>>>");
        System.out.println(filterApples(inventory, (Apple apple) -> Color.RED.equals(apple.getColor())));
        System.out.println(filterApples(inventory, apple -> Color.RED.equals(apple.getColor())));

        System.out.println("\n<<<abstract list>>>");
        System.out.println(filter(inventory, apple -> Color.GREEN.equals(apple.getColor())));
        System.out.println(filter(numbers, num -> num % 2 == 0));

        // using lambda
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

        ////////
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world");
            }
        });

        Thread t2 = new Thread(() -> System.out.println("Hello world"));

        ////////
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> threadName = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName();
            }
        });

        Future<String> threadName2 = executorService.submit(() -> Thread.currentThread().getName());
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();

        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static void prettyPrintApple(List<Apple> inventory, ApplePrint print) {
        for (Apple apple : inventory) {
            System.out.println(print.print(apple));
        }
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (Color.GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if (color.equals(apple.getColor())) {
                result.add(apple);
            }
        }

        return result;
    }

    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if (weight >= apple.getWeight()) {
                result.add(apple);
            }
        }

        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }

        return result;
    }
}
