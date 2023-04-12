package modernjava.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class Exam01 {
    public static void main(String[] args) {
        int max = 520_000_000;
        long[] numbers = LongStream.rangeClosed(1, max).toArray();

        long startTime = System.currentTimeMillis();
        long sum = addNumbers(max);
        System.out.println("recursive sum : " + sum + ", time : " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        sum = new ForkJoinPool().invoke(task);
        System.out.println("fork join sum : " + sum + ", time : " + (System.currentTimeMillis() - startTime));
    }

    public static long addNumbers(int n) {
        long sum = 0;

        for (int i = 1; i <= n; i++) {
            sum += i;
        }

        return sum;
    }
}
