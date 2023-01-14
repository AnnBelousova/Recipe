package com.mysite.recipe.controllers;

import com.mysite.recipe.model.Ingredient;
import com.mysite.recipe.model.Recipe;
import com.mysite.recipe.service.IngredientService;
import com.mysite.recipe.service.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredient")
    public Ingredient getRecipe(@RequestParam int id){
        return ingredientService.getIngredient(id);
    }

    @GetMapping("/ingredient/add")
    public void addRecipe(@RequestParam Ingredient ingredient){
        ingredientService.addIngredient(ingredient);
    }
}
