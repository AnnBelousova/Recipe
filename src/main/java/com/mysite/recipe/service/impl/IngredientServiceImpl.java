package com.mysite.recipe.service.impl;

import com.mysite.recipe.model.Ingredient;
import com.mysite.recipe.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private long id = 0;
    private Map<Long, Ingredient> ingredients  = new HashMap<>();

    @Override
    public Ingredient getIngredient(long id) {
        Ingredient ingredient;
        for(Map.Entry<Long, Ingredient> ingredientEntry:ingredients.entrySet()){
                if(ingredientEntry.getKey() == id) {
                    ingredient = ingredientEntry.getValue();
                    return ingredient;
                }
            }return null;
    }

    @Override
    public String getIngredients() {
        String value = "";
        for(Map.Entry<Long, Ingredient> pair : ingredients.entrySet()){
            value += pair.getValue().getIngredientName() + ", " +
                    pair.getValue().getQuantity() + ", "
                    + pair.getValue().getMeasureUnit() + "\n";
        }
        return value;
    }

    @Override
    public long addIngredient(Ingredient ingredient) {
        ingredients.put(id, ingredient);
        return id++;
    }

    @Override
    public Ingredient editIngredient(long id, Ingredient ingredient){
            if(ingredients.containsKey(id)){
                ingredients.put(id,ingredient);
            return ingredient;
        }return null;
    }

    @Override
    public  boolean deleteIngredient(long id){
            if(ingredients.containsKey(id) && !ingredients.equals(null)){
                ingredients.remove(id);
            return true;
        }return false;
    }

    @Override
    public void deleteAll(){
        ingredients =  new HashMap<>();
    }
}
