package com.mysite.recipe.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysite.recipe.exception.ExceptionThrowFilesProcessing;
import com.mysite.recipe.model.Recipe;
import com.mysite.recipe.service.FileService;
import com.mysite.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private static long recId = 0;
    private HashMap<Long, Recipe> recipes = new HashMap<>();
    final FileService fileService;

    Recipe recipe;

    @PostConstruct
    private void init() {
        try{
            if(fileService.isFileExistsRec() == true){
                readDataRec();
            }else
            fileService.createFileRec();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Recipe getRecipeById(long id) {
        if(!recipes.containsKey(id)){
            throw new NotFoundException("Рецепт с таким ID не найден");
        }
        return recipes.get(id);
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

    private void readDataRec() {
        String json = fileService.readRecipesFromFile();
        try {
            recipes = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new ExceptionThrowFilesProcessing("Файл не прочитан");
        }
    }

    @Override
    public Path createListOfRecipes() throws IOException {
        HashMap<Long,Recipe> recipeHashMap = recipes;
        Path path = fileService.createTempFileRec("recipes");
        for (Recipe recipe: recipeHashMap.values()) {
            try(Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)){
                writer.append(recipe.getName() + "\n"
                        + "Время приготовления:" + recipe.getPreparingTime() + "\n" + "Ингредиенты:\n");

                for (int i = 0; i < recipe.getIngredients().size(); i++) {
                    writer.append("•" + recipe.getIngredients().get(i).getName()  + " - "
                            + recipe.getIngredients().get(i).getQuantity() + " "
                            + recipe.getIngredients().get(i).getMeasureUnit());
                }
                writer.append("\n" + "Инструкция:\n");
                for (int i = 0; i < recipe.getSteps().size(); i++) {
                    writer.append(recipe.getSteps().get(i) + "\n");
                }
                    writer.append("\n");
            }
            }
        return path;
    }

}
