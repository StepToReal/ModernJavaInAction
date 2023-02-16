package modernjava.reducingsummarize;

import modernjava.Common;
import modernjava.Dish;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Exam01 {
    public static void main(String[] args) {
        implementsExam();
        quiz6_1();
    }

    private static void quiz6_1() {
        List<Dish> menu = Common.getDishes();

        String shortMenu = menu.stream().map(Dish::getName).collect(joining());

        String shortMenu2 = menu.stream().map(Dish::getName).collect(reducing((s1, s2) -> s1 + s2)).get();

//        컴파일 안됨 reducing 인자는 BinaryOperator 로 파라미터와 return 값의 형식이 일치 해야 함.
//        String shortMenu3 = menu.stream().collect(reducing((d1, d2) -> d1.getName() + d2.getName())).get();

        String shortMenu3 = menu.stream().collect(reducing("", Dish::getName, (s1, s2) -> s1 + s2));
    }

    private static void implementsExam() {
        List<Dish> menu = Common.getDishes();

        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().max(dishCaloriesComparator);
        mostCalorieDish = menu.stream().collect(maxBy(dishCaloriesComparator));

        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));

        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);

        String shortMenu = menu.stream().map(Dish::getName).collect(joining(","));
        System.out.println(shortMenu);

        int totalCalories2 = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        Optional<Dish> mostCalories2 = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));

        Stream<Integer> stream = Arrays.asList(1,2,3,4,5,6).stream();
        List<Integer> numbers = stream.reduce(new ArrayList<>(),
                (List<Integer> l, Integer e) -> {
                    l.add(e);
                    return l;
                },
                (List<Integer> l1, List<Integer> l2) -> {
                    l1.addAll(l2);
                    return l1;
                });
    }
}
