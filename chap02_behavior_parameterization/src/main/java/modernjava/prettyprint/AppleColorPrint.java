package modernjava.prettyprint;

import modernjava.Apple;

public class AppleColorPrint implements ApplePrint {

    @Override
    public String print(Apple apple) {
        return apple.getColor().toString() + "~~";
    }
}
