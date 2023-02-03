package com.mysite.recipe.service;

import com.mysite.recipe.model.Recipe;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

public interface RecipeService {

    Recipe getRecipeById(long id);

    Collection<Recipe> getRecipes();

    long addRecipe(Recipe recipe);

    Recipe editRecipe(long id, Recipe recipe);

    boolean deleteRecipe(long id);

    void deleteAll();

    Path createListOfRecipes() throws IOException;
}
