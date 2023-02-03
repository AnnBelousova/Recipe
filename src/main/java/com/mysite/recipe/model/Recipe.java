package com.mysite.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
public class Recipe {
    private String recipeName;
    private int preparingTime;
    private List<Ingredient> ingredientList;
    private LinkedList<String> stepsList;
}
