package modernjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

import static modernjava.Type.*;

public class FilteringApple {
    public static void main(String[] args) {
        Comparator<Apple> byWeight = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        };

        Comparator<Apple> byWeight2 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

        process(() -> System.out.println("hello"));

        List<Apple> inventory = Arrays.asList(
                new Apple(GREEN, 150),
                new Apple(RED, 100)
        );

        System.out.println(filter(inventory, (a) -> a.getWeight() > 120)); // Parameter 형식 추론 가능
        Comparator<Apple> c = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight()); // 형식 추론 가능


    }

    public static void process(Runnable r) {
        r.run();
    }

    public Callable<String> fetch() {
        return () -> "Tricky example";
    }

    public static List<Apple> filter(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> resultList = new ArrayList<>();

        for (Apple a : inventory) {
            if (p.test(a)) {
                resultList.add(a);
            }
        }

        return resultList;
    }
}
