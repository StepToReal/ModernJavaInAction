package modernjava;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Apple {
    private Color color;
    private int weight;

    @Override
    public String toString() {
        return String.format("{ Color: %s, weight: %d }", color.toString(), weight);
    }
}
