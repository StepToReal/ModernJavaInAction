package modernjava.spliterator;

public class TestCounter {
    int num;

    public TestCounter(int num) {
        this.num = num;
    }

    public TestCounter(Character c) {
        num = c;
    }

    public TestCounter accumulate(Character character) {
        return new TestCounter(character);
    }

    public TestCounter combine(TestCounter t) {
        return new TestCounter(num + t.num);
    }

    public int getNum() {
        return num;
    }
}
