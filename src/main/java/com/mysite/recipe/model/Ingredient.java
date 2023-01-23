package com.mysite.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Ingredient {
    private long id;
    private String ingredientName;
    private int quantity;
    private String measureUnit;
}
