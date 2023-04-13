package modernjava.spliterator;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Exam01 {
    public static void main(String[] args) {
        String sentence = "Nel    mezzo del  cammin di nostra vita " +
                "mi ritrovai in una   selva oscura " +
                "ch   la dritta via era   smarrita  ";

        int count1 = getWordCountReduce(sentence);
        System.out.println(count1);

        int count2 = getParallelWordCount(sentence);
        System.out.println(count2);

        String temp = "12345";
        Stream<Character> stream = IntStream.range(0, temp.length()).mapToObj(temp::charAt);

        TestCounter testCounter = stream.reduce(new TestCounter(0), TestCounter::accumulate, TestCounter::combine);
        System.out.println(testCounter.num);

        int count3 = getSpliterator(sentence);
        System.out.println(count3);
    }

    private static int getSpliterator(String sentence) {
        Spliterator<Character> spliterator = new WordCounterSpliterator(sentence);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);

        WordCounter wordCounter = stream
                .reduce(new WordCounter(0, true)
                        , WordCounter::accumulate
                        , WordCounter::combine);

        return wordCounter.getCounter();
    }

    private static int getParallelWordCount(String sentence) {
        Stream<Character> stream = IntStream.range(0, sentence.length()).mapToObj(sentence::charAt);

        WordCounter wordCounter = stream.parallel()
                .reduce(new WordCounter(0, true)
                        , WordCounter::accumulate
                        , WordCounter::combine);
        return wordCounter.getCounter();
    }

    private static int getWordCountReduce(String sentence) {
        Stream<Character> stream = IntStream.range(0, sentence.length()).mapToObj(sentence::charAt);

        WordCounter wordCounter = stream
                .reduce(new WordCounter(0, true)
                        , WordCounter::accumulate
                        , WordCounter::combine);
        return wordCounter.getCounter();
    }
}
