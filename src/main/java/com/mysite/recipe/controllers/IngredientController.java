package com.mysite.recipe.controllers;

import com.mysite.recipe.model.Ingredient;
import com.mysite.recipe.service.IngredientService;
//import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Ингридиеты", description = "Контроллер для работы со списком 'Ингридиентов'")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

//    public IngredientController(IngredientService ingredientService) {
//        this.ingredientService = ingredientService;
//    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение ингридиента по ID",
            description = "Пользователь должен ввести ID"
    )
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable long id){
        Ingredient ingredient = ingredientService.getIngredient(id);
        if(ingredient == null){
            ResponseEntity.notFound().build();
        }return ResponseEntity.ok(ingredient);
    }

    @GetMapping()
    @Operation(
            summary = "Получение всего списка ингридиента",
            description = "Будет выведеен список всех ингридиентов из базы данных"
    )
    public ResponseEntity<String> getIngredients(){
        String values = ingredientService.getIngredients();
        return ResponseEntity.ok(values);
    }

    @PostMapping()
    @Operation(
            summary = "Добавлеение ногого ингридиента",
            description = "Пользователь должен заполнить все поля, кроме поля ID (присвоится автоматически)"
    )
    public ResponseEntity<Ingredient> addIngredient( @RequestBody Ingredient ingredient){
       ingredientService.addIngredient(ingredient);
       return ResponseEntity.ok(ingredient);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение ингридиента",
            description = "Пользователь должен ввести ID ингридиента и заполнить/изменить все поля)"
    )
    public ResponseEntity<Ingredient> editIngredient(@PathVariable long id, @RequestBody Ingredient ingredient) {
        ingredient = ingredientService.editIngredient(id, ingredient);
        if(ingredient == null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление ингридиента по ID",
            description = "Пользователь должен ввести ID ингридиента, который необходимо удалить"
    )
    public ResponseEntity<Void> deleteIngredient(@PathVariable long id){
        if(ingredientService.deleteIngredient(id)){
            ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    @Operation(
            summary = "Удаление всего списка ингридиентов",
            description = "После данной операции, все ингридиенты будут удалены"
    )
    public ResponseEntity<Void> deleteAll(){
        ingredientService.deleteAll();
        return ResponseEntity.ok().build();
    }

}
