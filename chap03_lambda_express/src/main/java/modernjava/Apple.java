package modernjava;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apple extends Fruit {
    private Type type;
    private String country;

    public Apple(int weight) {
        super(weight);
    }

    public Apple(Type t, int weight) {
        this(weight);
        type = t;
    }

    @Override
    public String toString() {
        return "This is Apple. weight = " + weight;
    }
}
