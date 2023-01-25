package com.mysite.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Ingredient {
    @NotNull(message = "Это поле обязательное")
    private String ingredientName;
    @Positive
    private int quantity;
    @NotNull
    private String measureUnit;
}
