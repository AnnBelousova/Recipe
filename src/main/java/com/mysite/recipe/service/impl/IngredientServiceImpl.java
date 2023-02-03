package com.mysite.recipe.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysite.recipe.model.Ingredient;
import com.mysite.recipe.model.Recipe;
import com.mysite.recipe.service.FileService;
import com.mysite.recipe.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private static long ingrId = 0;
    private static HashMap<Long, Ingredient> ingredients = new HashMap<>();
    final FileService fileService;

    @PostConstruct
    private void init() {
        if (fileService.isFileExistsIngr() == true) {
            System.out.println(fileService.isFileExistsIngr());
            readData();
        } else {
            fileService.createFileIngr();
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
        ingredients.put(ingrId, ingredient);
        saveData();
        return ingrId++;
    }

    @Override
    public Ingredient editIngredient(long id, Ingredient ingredient) {
        if (!ingredients.containsKey(id)) {
            throw new NotFoundException("Ингридиент не найден");
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

    private void readData() {
        String json = fileService.readIngredientsFromFile();
        try {
            ingredients = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
//    @Override
//    public String getIngredients() {
//        String value = "";
//        StringUtils.upperCase(value);
//        for(Map.Entry<Long, Ingredient> pair : ingredients.entrySet()){
//            value += pair.getValue().getIngredientName() + ", " +
//                    pair.getValue().getQuantity() + ", "
//                    + pair.getValue().getMeasureUnit() + "\n";
//        }
//        return value;
//    }
}
