package modernjava.streamprocess;

import modernjava.Common;
import modernjava.Dish;

import java.util.List;

import static java.util.stream.Collectors.*;

public class StreamProcess {
    public static void main(String[] args) {
        List<Dish> menu = Common.getDishes();
        printStreamProcess(menu);
        quiz4_2(menu);
    }

    private static void printStreamProcess(List<Dish> menu) {
        /*
        실행 결과 :
        filtering: pork
        mapping: pork
        filtering: beef
        mapping: beef
        filtering: chicken
        mapping: chicken
        [pork, beef, chicken]

        - 300 칼로리가 넘는 요리가 많지만 limit에 의해 3개만 선택됨 (쇼트서킷)
        - filter, map은 다른 연산이지만 한 과정으로 병합 (루프 퓨전)
        - 이와 같이 stream은 실제 연산 수행 전 까지 중간과정을 수행하지 않는다. (laze 한 특성이 있다.)
         */
        List<String> names = menu
                .stream()
                .filter(dish -> {
                    System.out.println("filtering: " + dish.getName());
                    return dish.getCalories() > 300;
                })
                .map(dish -> {
                    System.out.println("mapping: " + dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .collect(toList());

        System.out.println(names);
    }

    private static void quiz4_2(List<Dish> menu) {
        long count = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .distinct()
                .limit(3)
                .count();

        System.out.println(count);
    }
}
