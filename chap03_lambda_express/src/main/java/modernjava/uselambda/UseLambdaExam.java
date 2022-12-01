package modernjava.uselambda;

import modernjava.Apple;
import modernjava.Type;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class UseLambdaExam {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(Type.RED, 100),
                new Apple(Type.GREEN, 120),
                new Apple(Type.GREEN, 90),
                new Apple(Type.RED, 200)
        );

        inventory.sort(new AppleComparator());

        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

        Comparator<Apple> c = Comparator.comparing((Apple a) -> a.getWeight());
        inventory.sort(c);
        inventory.sort(comparing(apple -> apple.getWeight()));

        inventory.sort(comparing(Apple::getWeight));
    }
}

class AppleComparator implements Comparator<Apple> {
    @Override
    public int compare(Apple a1, Apple a2) {
        return a1.getWeight().compareTo(a2.getWeight());
    }
}
