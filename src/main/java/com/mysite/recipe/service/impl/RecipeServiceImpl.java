package com.mysite.recipe.service.impl;

import com.mysite.recipe.model.Recipe;
import com.mysite.recipe.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private int idRecipe = 0;
    private Map<Integer, Recipe> recipes = new HashMap<>();
    Recipe recipe;

    @Override
    public Recipe getRecipe(int id) {
        for(Map.Entry<Integer, Recipe> entry: recipes.entrySet()){
            if(recipes.containsKey(id)){
                System.out.println(entry.getValue());
            }else {
                System.out.printf("Рецепт с номером %d отсутствует", id);
            }
        }return recipe;
    }

    @Override
    public void addRecipe(Recipe recipe) {
       recipes.put(idRecipe++,recipe);
    }
}
