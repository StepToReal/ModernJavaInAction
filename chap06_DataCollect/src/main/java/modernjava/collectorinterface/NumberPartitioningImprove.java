package modernjava.collectorinterface;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

public class NumberPartitioningImprove {
    public static void main(String[] args) {

        int num = 1000000;

        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            partitionPrimes(num);
            long duration = (System.nanoTime() - start) / 1000000;
            System.out.print(duration + ", ");
        }

        System.out.println();

        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            partitionPrimesByCustomCollector(num);
            long duration = (System.nanoTime() - start) / 1000000;
            System.out.print(duration + ", ");
        }

        System.out.println();


        compareGetPrimeSpeed(n -> partitionPrimes(n));
        compareGetPrimeSpeed(n -> partitionPrimesByCustomCollector(n));
    }

    private static void compareGetPrimeSpeed(Consumer<Integer> consumer) {
        long fastest = Long.MAX_VALUE;

        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            consumer.accept(1000000);
            long duration = (System.nanoTime() - start) / 1000000;
            if (duration < fastest) fastest = duration;
        }

        System.out.println(fastest);
    }

    public static Map<Boolean, List<Integer>> partitionPrimesByCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumbersCollector());
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(
                partitioningBy(candidate -> isPrime(candidate))
        );
    }

    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);  //rageClosed 는 endValue 값 포함

//        return IntStream.range(2, candidate).noneMatch(i -> candidate % i == 0);
    }
}
