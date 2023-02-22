package modernjava.collectorinterface;

import modernjava.Common;
import modernjava.Dish;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.*;

public class Exam01 {
    public static void main(String[] args) {
        List<Dish> menu = Common.getDishes();

        // as-is
        List<Dish> dishes = menu.stream().collect(toList());

        // to-be
        List<Dish> dishes2 = menu.stream().collect(new ToListCollector<>());

        // IDENTITY_FINISH 연산에서는 Collector 구현하지 않고 커스텀 가능
        List<Dish> dishes3 = menu.stream().collect(
                ArrayList::new,
                List::add,
                List::addAll
        );
    }
}
