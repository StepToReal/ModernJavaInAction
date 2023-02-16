package modernjava.grouping;

import modernjava.Common;
import modernjava.Dish;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Exam02 {
    public static void main(String[] args) {
        // 두 수준으로 스트림 항목 그룹화
        List<Dish> menu = Common.getDishes();

        Map<Dish.Type, Map<Exam01.CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                menu.stream().collect(
                        groupingBy(Dish::getType,
                                groupingBy(dish -> {
                                    if (dish.getCalories() <= 400) return Exam01.CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return Exam01.CaloricLevel.NORMAL;
                                    else return Exam01.CaloricLevel.FAT;
                                })
                        )
                );

        // 메뉴 타입 별 개수
        Map<Dish.Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));

        // 메뉴에서 가장 높은 칼로리 찾기
        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                maxBy(Comparator.comparingInt(Dish::getCalories)))
                        );

        // 그룹화 연산 결과에서 Optional 지우기
        Map<Dish.Type, Dish> mostCaloricByType2 =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                collectingAndThen(
                                        maxBy(Comparator.comparing(Dish::getCalories)),
                                        Optional::get
                                )));

        // 타입 별 메뉴 칼로리 합계
        Map<Dish.Type, Integer> totalCaloriesByType = menu.stream()
                .collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));

        // 타입 별 요리들의 칼로리 레벨
        Map<Dish.Type, Set<Exam01.CaloricLevel>> caloricLevelByType = menu.stream()
                .collect(groupingBy(Dish::getType,
                        mapping(dish -> {
                                    if (dish.getCalories() < 400) return Exam01.CaloricLevel.DIET;
                                    else if (dish.getCalories() < 700) return Exam01.CaloricLevel.NORMAL;
                                    else return Exam01.CaloricLevel.FAT;
                                }, toCollection(HashSet::new) //toSet() 도 가능.
                        )
                ));
    }
}
