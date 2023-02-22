package modernjava.partitioning;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class NumberPartitioning {
    public static void main(String[] args) {

    }

    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(
                partitioningBy(candidate -> isPrime(candidate))
        );
    }

    public boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);  //rageClosed 는 endValue 값 포함

//        return IntStream.range(2, candidate).noneMatch(i -> candidate % i == 0);
    }
}
