package com.mysite.recipe.service.impl;

import com.mysite.recipe.model.Recipe;
import com.mysite.recipe.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private long id = 0;
    private long ingId = 0;
    private Map<Long, Recipe> recipes = new HashMap<>();

    Recipe recipe;
    @Override
    public Recipe getRecipeById(long id) {
        for(Map.Entry<Long, Recipe> recipeEntry:recipes.entrySet()){
            if(recipeEntry.getKey() == id){
                recipe = recipeEntry.getValue();
                return recipe;
            }
        }return null;
    }

    @Override
    public String getRecipes() {
        String value = "";
        for(Map.Entry<Long, Recipe> pair : recipes.entrySet()){
            value += pair.getValue().getRecipeName() + ", " +
                    pair.getValue().getPreparingTime() + ", "
                    + pair.getValue().getIngredients() + "\n";
        }
        return value;
    }


    @Override
    public long addRecipe(Recipe recipe) {
        recipes.put(id, recipe);
        return id++;
    }

    @Override
    public Recipe editRecipe(long id, Recipe recipe){
        if(recipes.containsKey(id)){
            recipes.put(id,recipe);
            return recipe;
        }return null;
    }

    @Override
    public  boolean deleteRecipe(long id){
        if(recipes.containsKey(id) && !recipe.equals(null)){
            recipes.remove(id);
            return true;
        }return false;
    }

    @Override
    public void deleteAll(){
        recipes =  new HashMap<>();
    }
}
