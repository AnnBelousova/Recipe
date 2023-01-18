package com.mysite.recipe.controllers;

import com.mysite.recipe.model.Ingredient;
import com.mysite.recipe.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable long id){
        Ingredient ingredient = ingredientService.getIngredient(id);
        if(ingredient == null){
            ResponseEntity.notFound().build();
        }return ResponseEntity.ok(ingredient);
    }

    @GetMapping()
    public ResponseEntity<String> getIngredients(){
        String values = ingredientService.getIngredients();
        return ResponseEntity.ok(values);
    }


    @PostMapping()
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient){
       ingredientService.addIngredient(ingredient);
       return ResponseEntity.ok(ingredient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable long id, @RequestBody Ingredient ingredient) {
        ingredient = ingredientService.editIngredient(id, ingredient);
        if(ingredient == null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable long id){
        if(ingredientService.deleteIngredient(id)){
            ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){
        ingredientService.deleteAll();
        return ResponseEntity.ok().build();
    }

}
