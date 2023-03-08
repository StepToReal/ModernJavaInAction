package modernjava.parallelstream;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})
public class ParallelStreamBenchmark {
    private static final long N = 10_000_000L;

//    @Benchmark
//    public long sequentialSum() { // benchmark scope : 60.799
//        return Stream.iterate(1L, i -> i + 1).limit(N).reduce(0L, Long::sum);
//    }

//    @Benchmark
//    public long iterativeSum() { // benchmark scope : 2.744
//        long result = 0;
//
//        for (long i = 1L; i <= N; i++) {
//            result += i;
//        }
//
//        return result;
//    }
//
//    @Benchmark
//    public long parallelSum() { // benchmark scope : 84.640
//        return Stream.iterate(1L, i -> i + 1).limit(N).parallel().reduce(0L, Long::sum);
//    }

    @Benchmark
    public long rangedSum() { // benchmark scope : 8.459
        return LongStream.rangeClosed(1, N).reduce(0L, Long::sum);
    }

    @Benchmark
    public long parallelRangedSum() { // benchmark scope : 1.088
        return LongStream.rangeClosed(1, N).parallel().reduce(0L, Long::sum);
    }

    @State(Scope.Thread)
    public static class ParallelState {

        @TearDown(Level.Invocation)
        public void doTearDown() {
            System.gc();
        }
    }
}
