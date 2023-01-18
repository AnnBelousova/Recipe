package com.mysite.recipe.service;

import com.mysite.recipe.model.Ingredient;
import com.mysite.recipe.model.Recipe;

public interface RecipeService {

    Recipe getRecipeById(long id);

    String getRecipes();

    long addRecipe(Recipe recipe);

    Recipe editRecipe(long id, Recipe recipe);

    boolean deleteRecipe(long id);

    void deleteAll();
}