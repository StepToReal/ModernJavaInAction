package modernjava;

public class Orange extends Fruit{

    public Orange(Integer weight) {
        super(weight);
    }

    @Override
    public String toString() {
        return "This is Orange. weight = " + weight;
    }
}
