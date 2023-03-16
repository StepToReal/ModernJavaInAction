package modernjava.parallelstream;

import java.util.stream.Stream;

public class Exam01 {
    public static void main(String[] args) {
        long n = 10000000;

        System.out.println(Runtime.getRuntime().availableProcessors());

        long start = System.currentTimeMillis();
        long sumN01 = Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
        System.out.println("sumN01 none parallel : " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        long sumN02 = Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
        System.out.println("sumN02 none parallel : " + (System.currentTimeMillis() - start));
    }
}
