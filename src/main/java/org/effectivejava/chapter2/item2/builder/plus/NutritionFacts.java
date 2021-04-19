package org.effectivejava.chapter2.item2.builder.plus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NutritionFacts {
    private int servingSize;
    private int servings;
    private int calories;
    private int fat;
    private int sodium;
    private int carbohydrate;

    private NutritionFacts() {
    }

    public static void main(String[] args) throws Exception {
        NutritionFacts instance = new EntityBuilder<>(NutritionFacts.class)
            .setValue("servingSize", 240)
            .setValue("servings", 8)
            .setValue("calories", 100)
            .setValue("sodium", 35)
            .setValue("carbohydrate", 27).build();
    }
}
