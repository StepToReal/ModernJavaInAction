package modernjava.actualpractice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class ActualPractice {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Barian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리
        List<Transaction> no1 = transactions
                .stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparingInt(Transaction::getValue))
                .collect(toList());

        System.out.println("---no1 list print---");
        printList(no1);
        System.out.println();

        //2. 거래자가 근무하는 모든 도시를 중복없이 나열
        List<String> cities = transactions
                .stream()
                .map(Transaction::getTrader) //함수 표현식을 쓰면 2중으로 객체 탐색이 안됨 람다식으로는 가능 t -> t.getTrader().getCity()
                .map(Trader::getCity)
                .distinct()
                .collect(toList());

        System.out.println("---no2 list print---");
        printList(cities);
        System.out.println();

        //3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순 정렬
        List<Trader> traders = transactions
                .stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(t -> t.getCity().equals("Cambridge"))
                .sorted(comparing(Trader::getName))
                .collect(toList());

        System.out.println("---no3 list print---");
        printList(traders);
        System.out.println();

        //4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환
        List<String> traderNames = transactions
                .stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                .collect(toList());

        System.out.println("---no4 list print---");
        printList(traderNames);
        System.out.println();

        //5. 밀라노에 거래자가 있는가?
        boolean isMilanTraderExist = transactions
                .stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));

        System.out.println("---no5 list print---");
        System.out.println(isMilanTraderExist);
        System.out.println();

        //6. 케임브리지에 거주하는 거래자의 모든 트랜젝션 값을 출력
        System.out.println("---no6 list print---");
        transactions
                .stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
        System.out.println();

        //7. 전체 트랜젝션 중 최대값은
        int max = transactions
                .stream()
                .map(Transaction::getValue)
                .reduce(Integer::max).orElse(0);

        System.out.println("---no7 list print---");
        System.out.println(max);
        System.out.println();

        //8. 전체 트랜젝션 중 최소값
        int min = transactions
                .stream()
                .map(Transaction::getValue)
                .reduce(Integer::min).orElse(0);

        min = transactions
                .stream()
                .map(Transaction::getValue)
                .min(Integer::compare).orElse(0);

        System.out.println("---no8 list print---");
        System.out.println(min);
        System.out.println();
    }

    private static <T> void printList(List<T> list) {
        for (T t : list) {
            System.out.println(t.toString());
        }
    }
}
