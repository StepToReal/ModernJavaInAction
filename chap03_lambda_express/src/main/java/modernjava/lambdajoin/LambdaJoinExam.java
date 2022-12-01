package modernjava.lambdajoin;

import modernjava.Apple;
import modernjava.Type;

import java.util.Arrays;
import java.util.List;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

public class LambdaJoinExam {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(Type.RED, 100),
                new Apple(Type.GREEN, 120),
                new Apple(Type.GREEN, 90),
                new Apple(Type.RED, 200)
        );

        //comparing, reversed, thenComparing 모두 Comparator<T> 를 반환
        inventory.sort(comparing(Apple::getWeight).reversed());
        inventory.sort(comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getCountry));

        Predicate<Apple> redApple = (a) -> a.getType().equals(Type.RED);
        Predicate<Apple> notRedApple = redApple.negate();
        Predicate<Apple> redAndHeavyApple = redApple.and(a -> a.getWeight() > 150);
        Predicate<Apple> redAndHeavyAppleOrGreen = redApple
                .and(a -> a.getWeight() > 150)
                .or(a -> a.getType().equals(Type.GREEN));

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        System.out.println(h.apply(1)); // g(f(x))

        Function<Integer, Integer> h2 = f.compose(g);
        System.out.println(h2.apply(1)); // f(g(x))

        integrate(d -> d + 10, 3, 7);
    }

    public static double integrate(DoubleFunction<Double> f, double a, double b) {
        return (f.apply(a) + f.apply(b)) * (b - a) / 2.0;
    }
}
