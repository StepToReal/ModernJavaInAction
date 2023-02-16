package modernjava.grouping;

import modernjava.Common;
import modernjava.Dish;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Exam01 {
    public enum CaloricLevel {DIET, NORMAL, FAT}

    public static void main(String[] args) {
        List<Dish> menu = Common.getDishes();

        Map<Dish.Type, List<Dish>> dishesByType =
                menu.stream().collect(groupingBy(Dish::getType));

        System.out.println(dishesByType);

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel =
                menu.stream().collect(
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })
                );

        System.out.println(dishesByCaloricLevel);

        // 500 칼로리 넘는 음식만 필터해서 그룹화
        // 1. grouping 전 filter로 거르면 조건에 맞지 않는 fish 항목은 키가 없게 된다.
        Map<Dish.Type, List<Dish>> caloricDishesByType = menu.stream()
                .filter(dish -> dish.getCalories() > 500)
                .collect(groupingBy(Dish::getType));

        // 2. collect 안에 filtering 기능을 넣어 위 문제를 해결할 수 있다.
        Map<Dish.Type, List<Dish>> caloricDishesByType2 = menu.stream()
                .collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));

        System.out.println(caloricDishesByType2);

        // 그룹의 각 요리를 관련 이름 목록으로 변환
        Map<Dish.Type, List<String>> dishNamesByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));

        System.out.println(dishNamesByType);

        // 요리별로 Tag 정보를 가진 map 이 있을 때 flatmap 사용하여 tag 추출
        Map<String, List<String>> dishTags = getDishTages();
        Map<Dish.Type, Set<String>> dishNamesByType2 = menu.stream()
                .collect(groupingBy(
                        Dish::getType,
                        flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())
                ));

        System.out.println(dishNamesByType2);
    }

    private static Map<String, List<String>> getDishTages() {
        Map<String, List<String>> result = new HashMap<>();
        result.put("pork", Arrays.asList("greasy", "salty"));
        result.put("beef", Arrays.asList("salty", "roasted"));
        result.put("chicken", Arrays.asList("fried", "crisp"));
        result.put("french fries", Arrays.asList("greasy", "fried"));
        result.put("rice", Arrays.asList("light", "natural"));
        result.put("season fruit", Arrays.asList("fresh", "natural"));
        result.put("pizza", Arrays.asList("tasty", "salty"));
        result.put("prawns", Arrays.asList("tasty", "roasted"));
        result.put("salmon", Arrays.asList("delicious", "fresh"));

        return result;
    }
}
