package modernjava.constructor_refer;

import modernjava.Apple;
import modernjava.Fruit;
import modernjava.Orange;
import modernjava.Type;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConstructorReferExam {
    public static void main(String[] args) {
        Supplier<Apple> c = () -> new Apple();
        Supplier<Apple> c1 = Apple::new;  // 위와 아래는 같은 코드.
        Apple a1 = c1.get();

        //new Apple(weight) 생성자 참조
        Function<Integer, Apple> c2 = Apple::new;
        // Function<Integer, Apple> c2 = (weight) -> new Apple(weight);
        Apple a2 = c2.apply(100);

        testConstructorReference();

        //new Apple(Type, weight) reference
        BiFunction<Type, Integer, Apple> c3 = Apple::new;
        Apple a3 = c3.apply(Type.RED, 100);

        Fruit f1 = giveMeFruit("apple", 100);
        Fruit f2 = giveMeFruit("orange", 200);

        System.out.println(f1 + "\n" + f2);
    }

    static Map<String, Function<Integer, Fruit>> map = new HashMap<>();
    static {
        map.put("apple", Apple::new);
        map.put("orange", Orange::new);
    }

    private static Fruit giveMeFruit(String fruit, int weight) {
        return map.get(fruit).apply(weight);
    }

    private static void testConstructorReference() {
        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new);
    }

    private static List<Apple> map(List<Integer> weights, Function<Integer, Apple> f) {
        List<Apple> apples = new ArrayList<>();

        for (Integer weight : weights) {
            apples.add(f.apply(weight));
        }

        return apples;
    }
}
