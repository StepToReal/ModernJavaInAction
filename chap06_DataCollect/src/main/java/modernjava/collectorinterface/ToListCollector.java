package modernjava.collectorinterface;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.*;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    //1. 빈 결과로 이루어진 Supplier 반환, init list 생성
    @Override
    public Supplier<List<T>> supplier() {
//        return () -> new ArrayList<>();
        return ArrayList::new;
    }

    //2. n-1 까지 처리된 누적자에 n 번째 항목 처리
    @Override
    public BiConsumer<List<T>, T> accumulator() {
//        return (list, item) -> list.add(item);
        return List::add;
    }

    //3. 스트림의 서로 다른 서브파트를 병렬로 처리할 때 누적자 처리 정의, 병렬 처리에 사용
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    //4. 스트림 탐색을 끝내고 누적자를 최종 결과로 반환 (지금은 누적자 = 최종결과인 상황이라 항등함수 반환)
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity(); //항등함수 반환
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                IDENTITY_FINISH, CONCURRENT
        ));
    }
}
