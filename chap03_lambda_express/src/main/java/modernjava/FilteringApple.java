package modernjava;

import java.util.Comparator;
import java.util.concurrent.Callable;

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
    }

    public static void process(Runnable r) {
        r.run();
    }

    public Callable<String> fetch() {
        return () -> "Tricky example";
    }
}
