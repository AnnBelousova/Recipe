package com.mysite.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.*;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Recipe {
    private long id;
    @NotNull
    private String recipeName;
    @Positive
    private int preparingTime;
    private List<Ingredient> ingredients;
    private ArrayList<String> steps;
}
