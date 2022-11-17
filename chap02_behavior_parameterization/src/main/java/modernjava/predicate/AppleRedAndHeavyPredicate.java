package modernjava.predicate;

import modernjava.Apple;
import modernjava.Color;


public class AppleRedAndHeavyPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return Color.RED.equals(apple.getColor()) &&
                apple.getWeight() >= 150;
    }
}
