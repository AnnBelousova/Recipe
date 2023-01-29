package com.mysite.recipe.service;

import com.mysite.recipe.model.Ingredient;
import io.swagger.v3.oas.models.security.SecurityScheme;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;

public interface IngredientService {
    Ingredient getIngredient(long id);

    Collection<Ingredient> getIngredients();

    long addIngredient(Ingredient ingredient);

    Ingredient editIngredient(long id, Ingredient newIngredient);

    boolean deleteIngredient(long id);

    void deleteAll();

    Path createListOfIngredients() throws IOException;
}
