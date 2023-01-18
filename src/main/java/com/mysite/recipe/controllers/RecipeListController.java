package com.mysite.recipe.controllers;

import com.mysite.recipe.model.Ingredient;
import com.mysite.recipe.model.Recipe;
import com.mysite.recipe.service.IngredientService;
import com.mysite.recipe.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeListController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public RecipeListController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getIngredientById(@PathVariable long id){
        Recipe recipe = recipeService.getRecipeById(id);
        if(recipe == null){
            ResponseEntity.notFound().build();
        }return ResponseEntity.ok(recipe);
    }

    @PostMapping()
    public ResponseEntity<Long> addRecipe(@RequestBody Recipe recipe){
        long id = recipeService.addRecipe(recipe);
        return ResponseEntity.ok(id);
    }

    @GetMapping()
    public ResponseEntity<String> getRecipes(){
        String values = recipeService.getRecipes();
        return ResponseEntity.ok(values);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable long id, @RequestBody Recipe recipe) {
        recipe = recipeService.editRecipe(id, recipe);
        if(recipe == null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable long id){
        if(recipeService.deleteRecipe(id)){
            ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){
        recipeService.deleteAll();
        return ResponseEntity.ok().build();
    }

}
