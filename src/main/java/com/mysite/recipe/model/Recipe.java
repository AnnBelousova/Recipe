package com.mysite.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Recipe {
    @NotNull(message = "Это поле обязательное")
    private String recipeName;
    @Positive
    private int preparingTime;
    @NotEmpty
    private List<Ingredient> ingredients;
    @NotEmpty
    private ArrayList<String> steps;
}
