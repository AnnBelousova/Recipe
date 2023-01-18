package com.mysite.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {
    private long id;
    private String ingredientName;
    private int quantity;
    private String measureUnit;
}
