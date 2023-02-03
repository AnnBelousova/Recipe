package com.mysite.recipe.service.impl;

import com.mysite.recipe.model.Ingredient;
import com.mysite.recipe.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class IngredientServiceImpl implements IngredientService {
    private int idIngredient = 0;
    private Map<Integer, Ingredient> ingredients  = new HashMap<>();
    Ingredient ingredient;
    @Override
    public Ingredient getIngredient(int id) {
        for(Map.Entry<Integer, Ingredient> entry: ingredients.entrySet()){
            if(ingredients.containsKey(id)){
                System.out.println(entry.getValue());
            }else
                System.out.printf("Ингридиент с номером %d отсутствует", id);
        }
        return ingredient;
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        ingredients.put(idIngredient++, ingredient);
    }
}
