package modernjava.mapping;

import modernjava.Common;
import modernjava.Dish;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.*;

public class MappingExam {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        System.out.println(arr);
        System.out.println(Arrays.toString(arr));
        List<Dish> menu = Common.getDishes();

        examMapping(menu);
        examFlatMapping();
        quiz5_2_1();
        quiz5_2_2();
        quiz5_2_3();
    }

    private static void quiz5_2_3() {
        List<Integer> num1 = Arrays.asList(1, 2, 3);
        List<Integer> num2 = Arrays.asList(3, 4);

        List<int[]> pairs = num1.stream()
                .flatMap(i -> num2.stream()
                        .map(j -> new int[] {i, j}))
                .filter(i -> (i[0] + i[1]) % 3 ==0)
                .collect(toList());

        System.out.println();
        pairs.forEach(i -> System.out.print(Arrays.toString(i)));

        //아래 방법이 더 좋을 듯 하다. 더 적은 반복을 가질 수 있다. (책의 정답) 이중 포문 돌리듯이 생각하면 될듯..
        List<int[]> pairs2 = num1.stream()
                .flatMap(i -> num2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[] {i, j}))
                .collect(toList());

        System.out.println();
        pairs.forEach(i -> System.out.print(Arrays.toString(i)));
    }

    private static void quiz5_2_2() {
        List<Integer> num1 = Arrays.asList(1, 2, 3);
        List<Integer> num2 = Arrays.asList(3, 4);

        List<int[]> pairs = num1.stream()
                .flatMap(i -> num2.stream()
                        .map(j -> new int[] {i, j}))
                .collect(toList());

        pairs.forEach(i -> System.out.print(Arrays.toString(i)));
    }

    private static void quiz5_2_1() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> powerNumbers = numbers.stream()
                .map(i -> i * i)
                .collect(toList());

        System.out.println(powerNumbers);
    }

    private static void examFlatMapping() {
        List<String> words = Arrays.asList("Hello", "World");

        // 아래는 map(s -> s.split("")) 이 Stream(String[]) 을 반환하기 때문에 실패한다.
//        List<String> chars = words.stream()
//                .map(s -> s.split(""))
//                .distinct()
//                .collect(toList());

        // 아래도 Stream<Stream<String>> 이 만들어 지면서 실패
//        List<String> chars = words.stream()
//                .map(s -> s.split(""))
//                .map(Arrays::stream)
//                .distinct()
//                .collect(toList());

        List<String> uniqueChars = words.stream()
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
    }

    private static void examMapping(List<Dish> menu) {
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(toList());

        List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());

        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
    }
}
