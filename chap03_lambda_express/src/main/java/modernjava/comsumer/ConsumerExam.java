package modernjava.comsumer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerExam {
    public static void main(String[] args) {
        Consume consume = new Consume();

        setName(consume, (c) -> c.setName("name"));
        System.out.println(consume.getName());

        setName(consume, (c) -> c.setName("cs"));
        System.out.println(consume.getName());

        forEach(Arrays.asList(1, 2, 3), (c) -> System.out.print(c + ", "));

        setName("1234", (s) -> s = s + "aaa"); // 되긴 되나 의미가 없는 코드.. 뭐 객체 field 값을 바꾸는것은 적용 되겠으나 좋은 방법은 아닌듯.
    }

    public static <T> T setName(T t, Consumer<T> c) {
        c.accept(t);

        return t;
    }

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }
}
