package com.mysite.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Ingredient {
    private long id;
    @NotNull
    private String ingredientName;
    @Positive
    private int quantity;
    @NotNull
    private String measureUnit;
}
