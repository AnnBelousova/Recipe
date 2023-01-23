package com.mysite.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Recipe {
    private long id;
    private String recipeName;
    private int preparingTime;
    private List<Ingredient> ingredients;
    private ArrayList<String> steps;
}
