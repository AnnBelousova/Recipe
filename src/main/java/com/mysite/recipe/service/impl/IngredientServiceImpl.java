package com.mysite.recipe.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysite.recipe.exception.ExceptionRecipeIngredientExisting;
import com.mysite.recipe.exception.ExceptionThrowFilesProcessing;
import com.mysite.recipe.model.Ingredient;
import com.mysite.recipe.service.FileService;
import com.mysite.recipe.service.IngredientService;
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

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private static long ingrId = 0;
    private static HashMap<Long, Ingredient> ingredients = new HashMap<>();
    final FileService fileService;

    @PostConstruct
    private void init() {
        try{
            if(fileService.isFileExistsIngr() == true){
                readDataIngr();
            }else
            fileService.createFileIngr();
        }catch (Exception e)
        {
           e.printStackTrace();
        }
    }

    @Override
    public Ingredient getIngredient(long id) {
        if(!ingredients.containsKey(id)){
            throw new NotFoundException("Ингредиент с таким ID не найден");
        }
        return ingredients.get(id);
    }

    public Collection<Ingredient> getIngredients() {
        return ingredients.values();
    }

    @Override
    public long addIngredient(Ingredient ingredient) {
        if(ingredients.containsValue(ingredient)){
            throw new ExceptionRecipeIngredientExisting();
        }
        ingredients.put(ingrId, ingredient);
        saveData();
        return ingrId++;
    }

    @Override
    public Ingredient editIngredient(long id, Ingredient ingredient) {
        if (!ingredients.containsKey(id)) {
            throw new NotFoundException("Ингредиент не найден");
        }
        ingredients.put(id, ingredient);
        saveData();
        return ingredient;
    }

    @Override
    public boolean deleteIngredient(long id) {
        if (ingredients.containsKey(id)) {
            ingredients.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll() {
        ingredients = new HashMap<>();
    }

    private void saveData() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            fileService.writeIngredientsToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void readDataIngr() {
        String json = fileService.readIngredientsFromFile();
        try {
            ingredients = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new ExceptionThrowFilesProcessing("Файл не прочитан");
        }
    }

    @Override
    public Path createListOfIngredients() throws IOException {
        HashMap<Long, Ingredient> ingredientHashMap = new HashMap<>();
        Path path = fileService.createTempFileIngr("ingredients");
        {
            for (Ingredient ingredient: ingredientHashMap.values()) {
                Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND);
                writer.append(ingredient.getName() + ingredient.getMeasureUnit() + ingredient.getQuantity());
                writer.append("\n");
            }
        }return path;
    }
}
