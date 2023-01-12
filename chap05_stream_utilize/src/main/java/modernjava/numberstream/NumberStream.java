package modernjava.numberstream;

import modernjava.Common;
import modernjava.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumberStream {
    public static void main(String[] args) {
        List<Dish> menu = Common.getDishes();

        //mapToInt 는 특화된 IntStream 을 반환 IntStream 에는 sum, min, max, average 등 유틸 메서드가 지원된다.
        //sum 은 값이 없을때 기본값 0을 반환한다.
        int calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        //특화 스트림을 일반 스트림으로 변경
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();

        //sum 은 기본값을 반환하지만 min max 등에는 기본값을 둘 수가 없다. 대안으로 OptionalInt 같은 기본형 특화 스트림을 제공한다.
        OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();

        //IntStream, LongStream 에는 range, rangeClosed 범위 메서드가 제공된다.
        //range 는 시작 종료값 미포함, rangeClosed 는 시작 종료값 포함.
        IntStream evenNumbers = IntStream.rangeClosed(1, 100);

        //IntStream 활용
        getPythagoras();
    }

    private static void getPythagoras() {
        int tempA = 5;
        List<Integer> bList = new ArrayList<>();

        for (int i = 1; i < 101; i++) {
            bList.add(i);
        }

        Stream<int[]> cStream = bList.stream().filter(b -> Math.sqrt(tempA * tempA + b * b) % 1 == 0)
                .map(b -> new int[]{tempA, b, (int) Math.sqrt(tempA * tempA + b * b)});

        //IntStream 을 이용하여 bList 없이 처리 가능
        Stream<int[]> cStream2 = IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(tempA * tempA + b * b) % 1 == 0)
                .boxed()
                .map(b -> new int[]{tempA, b, (int) Math.sqrt(tempA * tempA + b * b)});

        Stream<int[]> cStream3 = IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(tempA * tempA + b * b) % 1 == 0)
                .mapToObj(b -> new int[]{tempA, b, (int) Math.sqrt(tempA * tempA + b * b)});

        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));

        pythagoreanTriples.limit(5).forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        //pythagoreanTriples 는 Math.sqrt를 두번 계산한다. 아래와 같이 개선 가능하다.
        Stream<double[]> pythagoreanTriples2 = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(t -> t[2] % 1 == 0));
    }
}
