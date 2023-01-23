package com.mysite.recipe.controllers;

import com.mysite.recipe.model.Recipe;
import com.mysite.recipe.service.IngredientService;
import com.mysite.recipe.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "Контроллер для работы со списком 'Рецептов'")
public class RecipeListController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение рецепта по ID",
            description = "Пользователь должен ввести ID"
    )
    public ResponseEntity<Recipe> getIngredientById(@PathVariable long id){
        Recipe recipe = recipeService.getRecipeById(id);
        if(recipe == null){
            ResponseEntity.notFound().build();
        }return ResponseEntity.ok(recipe);
    }

    @PostMapping()
    @Operation(
            summary = "Добавлеение ногого рецепта",
            description = "Пользователь должен заполнить все поля, кроме поля ID (присвоится автоматически)"
    )
    public ResponseEntity<Long> addRecipe(@RequestBody Recipe recipe){
        long id = recipeService.addRecipe(recipe);
        return ResponseEntity.ok(id);
    }

    @GetMapping()
    @Operation(
            summary = "Получение всего списка рецептов",
            description = "Будет выведеен список всех ингридиентов из базы данных"
    )
    public ResponseEntity<String> getRecipes(){
        String values = recipeService.getRecipes();
        return ResponseEntity.ok(values);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение рецепта",
            description = "Пользователь должен ввести ID рецепта и заполнить/изменить все поля)"
    )
    public ResponseEntity<Recipe> editRecipe(@PathVariable long id, @RequestBody Recipe recipe) {
        recipe = recipeService.editRecipe(id, recipe);
        if(recipe == null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление рецепта по ID",
            description = "Пользователь должен ввести ID рецепта, который необходимо удалить"
    )
    public ResponseEntity<Void> deleteRecipe(@PathVariable long id){
        if(recipeService.deleteRecipe(id)){
            ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    @Operation(
            summary = "Удаление всего списка рецептов",
            description = "После данной операции, все рецепты будут удалены"
    )
    public ResponseEntity<Void> deleteAll(){
        recipeService.deleteAll();
        return ResponseEntity.ok().build();
    }

}
