package com.mysite.recipe.service;

import com.mysite.recipe.model.Recipe;

import java.util.Map;

public interface RecipeService {
    Recipe getRecipe(int id);
    void addRecipe(Recipe recipe);
}
