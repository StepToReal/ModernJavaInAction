package modernjava.makestream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MakeStream {
    public static void main(String[] args) {
        //임의의 인수를 받아 스트림 생성
        Stream<String> stream = Stream.of("Modern", "Java", "In", "Action");
        stream.map(String::toUpperCase).forEach(s -> System.out.print(s + " "));
        System.out.println();

        //빈 스트림 생성
        Stream<String> emptyStream = Stream.empty();

        //null 이 될 수 있는 객체로 스트림 생성 (java 9 이상)

        String osName = System.getProperty("os.name");
        System.out.println(osName);

        Stream<String> osNameStream = osName == null ? Stream.empty() : Stream.of(osName);
        Stream<String> osNameStream2 = Stream.ofNullable(osName);

        //null 이 될 수 있는 스트림값을 flatMap 과 사용하는 패턴, emptyStream 은 제외되는듯
        Stream.of("os.name", "user.name", "user.name2")
                .flatMap(key -> Stream.ofNullable(System.getProperty(key)))
                .forEach(System.out::println);

        //배열로 스트림 생성
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();

        //File로 스트림 만들기
        //스트림은 AutoCloseable 이라서 finally 없이 자원 해제 가능하다.
        long uniqueWords = 0;
        try (Stream<String> lines =
                     Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines
                    .flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {
            if (e instanceof NoSuchFileException) {
                System.out.println("Error no search file");
            }
        }

        //무한 스트림 만들기
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(n -> System.out.print(n + ", "));
        System.out.println();

        quiz5_4();

        //iterator 는 이전에 생산된 값을 활용, generate 는 이전 생산값 사용안함.
        //iterator 는 인자를 받아 인자와 같은 타입값을 리턴 (UnaryOperator) generate 는 인자 없고 특정 타입 리턴 (Supplier)
        //Supplier 는 보통 상태를 저장하지 않음. 하지만 상태를 저장하지 못하는건 아님. 단 Supplier 가 상태를 가지면 병렬 코드에서 부작용이 생길 수 있음.
        Stream.generate(Math::random)
                .limit(5)
                .forEach(n -> System.out.print(n + ", "));
        System.out.println();

        //generate 로 피보나치 구현. (실제로는 이런 상태를 갖는 Supplier 는 피해야 함.)
        //람다는 들어온 변수의 상태를 변경하지 못하기 때문에 람다를 사용할 수 없다.
        //익명 클래스를 구현하여 Stream 에 적용하면 해결은 가능하다.
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = current;
                this.current = nextValue;
                return oldPrevious;
            }
        };

        IntStream.generate(fib).limit(10).forEach(n -> System.out.print(n + ", "));
        System.out.println();
    }

    private static void quiz5_4() {

        Stream.iterate(new int[] {0, 1}, n -> new int[]{n[1], n[0] + n[1]})
                .limit(20)
                .forEach(t -> System.out.print("(" + t[0] + ", " + t[1] + "), "));
        System.out.println();

        //java 9 에서는 iterator 에 프레디케이트 지원 -> 조건을 달아 iterator 사용 가능
        IntStream.iterate(0, n -> n < 100, a -> a + 4)
                .forEach(n -> System.out.print(n + ", "));
        System.out.println();
    }
}
