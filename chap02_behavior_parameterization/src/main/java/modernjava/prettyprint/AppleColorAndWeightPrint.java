package modernjava.prettyprint;

import modernjava.Apple;

public class AppleColorAndWeightPrint implements ApplePrint{
    @Override
    public String print(Apple apple) {
        return String.format("{ color: %s, weight: %d }", apple.getColor().toString(), apple.getWeight());
    }
}
