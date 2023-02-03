package com.mysite.recipe.controllers;

import com.mysite.recipe.model.Recipe;
import com.mysite.recipe.service.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe")
    public Recipe getRecipe(@RequestParam int id){
        return recipeService.getRecipe(id);
    }

    @GetMapping("/recipe/add")
    public void addRecipe(@RequestParam Recipe recipe){
        recipeService.addRecipe(recipe);
    }
}
