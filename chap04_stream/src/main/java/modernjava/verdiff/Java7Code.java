package modernjava.verdiff;

import modernjava.Common;
import modernjava.Dish;

import javax.swing.plaf.ComponentInputMapUIResource;
import java.util.*;

public class Java7Code {
    public static void main(String[] args) {
        List<Dish> menu = Common.getDishes();

        List<Dish> lowCaloricDishes = new ArrayList<>();

        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }

        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });

        List<String> lowCaloricDishesName = new ArrayList<>();

        for (Dish dish : lowCaloricDishes) {
            lowCaloricDishesName.add(dish.getName());
        }
    }
}
