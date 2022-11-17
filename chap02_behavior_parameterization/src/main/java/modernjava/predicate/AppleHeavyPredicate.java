package modernjava.predicate;

import modernjava.Apple;

public class AppleHeavyPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() >= 150;
    }
}
