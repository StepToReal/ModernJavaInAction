package modernjava.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionExam {
    public static void main(String[] args) {
        List<Integer> lenList = map(Arrays.asList("123", "1", "12"), (String s) -> s.length());

        System.out.println(lenList);
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();

        for (T t : list) {
            result.add(f.apply(t));
        }

        return result;
    }
}
