package modernjava.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Map.*;

public class CollectionFactory {
    public static void main(String[] args) {
        listFactoryTest();
        mapFactoryTest();
    }

    private static void mapFactoryTest() {
        Map<String, Integer> simpleMap = of("Raphael", 30, "Olivia", 25, "Thibaut", 26);
        Map<String, Integer> simpleMap2 = ofEntries(
                entry("Raphael", 30),
                entry("Olivia", 25),
                entry("Thibaut", 26)
        ); //Map.of 로 만드는 객체 변경 불가능

        try {
            simpleMap.put("Oh", 35);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("mapFactory Error " + e.getMessage());
        }

        System.out.println(simpleMap);
        System.out.println("mapFactoryTest Done");
    }

    private static void listFactoryTest() {
        List<String> friends = Arrays.asList("Raphael, Olivia, Thibaut"); //요소 갱신가능, 추가 삭제 불가능
        friends = List.of("Raphael, Olivia, Thibaut"); //java9 부터 가능, 요소 갱신도 불가능

        try {
            friends.set(0, "temp");
        } catch (Exception e) {
            e.printStackTrace();
        }

        friends = new ArrayList<>(List.of("Raphael, Olivia, Thibaut"));
        friends.add("test");

        System.out.println("listFactoryTest Done");
    }
}
