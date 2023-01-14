package com.mysite.recipe.service;

import com.mysite.recipe.model.Ingredient;
import com.mysite.recipe.model.Recipe;

import java.util.Map;

public interface IngredientService {
    Ingredient getIngredient(int id);
    void addIngredient(Ingredient ingredient);
}
