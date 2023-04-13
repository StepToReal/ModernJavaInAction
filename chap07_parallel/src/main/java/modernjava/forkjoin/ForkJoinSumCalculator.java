package modernjava.forkjoin;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;

        if (length <= THRESHOLD) {
            return computeSequentially();
        }

        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork(); //left task 비동기 실행

        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long rightResult = rightTask.compute(); //right task 동기 실행
        Long leftResult = leftTask.join();

        return leftResult + rightResult;
    }

    private Long computeSequentially() {
        long sum = 0;

        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
