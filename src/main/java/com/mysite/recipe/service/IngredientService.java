package com.mysite.recipe.service;

import com.mysite.recipe.model.Ingredient;

public interface IngredientService {
    Ingredient getIngredient(long id);

    String getIngredients();

    long addIngredient(Ingredient ingredient);

    Ingredient editIngredient(long id, Ingredient newIngredient);

    boolean deleteIngredient(long id);

    void deleteAll();
}
