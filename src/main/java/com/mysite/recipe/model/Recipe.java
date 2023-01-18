package com.mysite.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
public class Recipe {
    //private long id;
    private String recipeName;
    private int preparingTime;
    private List<Ingredient> ingredients;
    private ArrayList<String> steps;
}
