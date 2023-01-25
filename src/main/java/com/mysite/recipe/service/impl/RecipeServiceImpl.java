package com.mysite.recipe.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysite.recipe.model.Ingredient;
import com.mysite.recipe.model.Recipe;
import com.mysite.recipe.service.FileService;
import com.mysite.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private static long recId = 0;
    private HashMap<Long, Recipe> recipes = new HashMap<>();
    final FileService fileService;

    Recipe recipe;

    @PostConstruct
    private void init() {
        readData();
    }

    @Override
    public Recipe getRecipeById(long id) {
        for (Map.Entry<Long, Recipe> recipeEntry : recipes.entrySet()) {
            if (recipeEntry.getKey() == id) {
                recipe = recipeEntry.getValue();
                return recipe;
            }
        }
        return null;
    }

    public Collection<Recipe> getRecipes() {
        return recipes.values();
    }


    @Override
    public long addRecipe(Recipe recipe) {
        recipes.put(recId, recipe);
        saveData();
        return recId++;
    }

    @Override
    public Recipe editRecipe(long id, Recipe recipe) {
        if (recipes.containsKey(id)) {
            recipes.put(id, recipe);
            saveData();
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(long id) {
        if (recipes.containsKey(id)) {
            recipes.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll() {
        recipes = new HashMap<>();
    }

    private void saveData() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            fileService.writeRecipesToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readData() {
        String json = fileService.readRecipesFromFile();
        try {
            recipes = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    //    @Override
//    public String getRecipes() {
//        String value = "";
//        StringUtils.upperCase(value);
//        for(Map.Entry<Long, Recipe> pair : recipes.entrySet()){
//            value += pair.getValue().getRecipeName() + ", " +
//                    pair.getValue().getPreparingTime() + ", "
//                    + pair.getValue().getIngredients() + "\n";
//        }
//        return value;
//    }
}
