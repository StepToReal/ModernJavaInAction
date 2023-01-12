package modernjava.makestream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
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
            e.printStackTrace();
        }


    }
}
