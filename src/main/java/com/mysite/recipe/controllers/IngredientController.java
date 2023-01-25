package com.mysite.recipe.controllers;

import com.mysite.recipe.model.Ingredient;
import com.mysite.recipe.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.StringReader;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Tag(name = "Ингредиенты", description = "Контроллер для работы со списком 'Ингредиенты'")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение ингредиента по ID",
            description = "Пользователь должен ввести ID"
    )
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингридиент найден",
                    content = {@Content (mediaType = "application/json",
                            schema = @Schema(implementation = Ingredient.class))}
            )})
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable long id){
        Ingredient ingredient = ingredientService.getIngredient(id);
        if(ingredient == null){
            ResponseEntity.notFound().build();
        }return ResponseEntity.ok(ingredient);
    }

    @GetMapping()
    @Operation(
            summary = "Получение всего списка ингредиентов",
            description = "Будет выведен список всех ингредиентов из базы данных"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингридиент найден",
                    content = {@Content (mediaType = "application/json",
                            schema = @Schema(implementation = Ingredient.class))}
            )})
    public Collection<Ingredient> getIngredients(){
        return this.ingredientService.getIngredients();
    }

    @PostMapping()
    @Operation(
            summary = "Добавлеение нового ингредиента",
            description = "Пользователь должен заполнить все поля, кроме поля ID (присвоится автоматически)"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингридиент найден",
                    content = {@Content (mediaType = "application/json",
                            schema = @Schema(implementation = Ingredient.class))}
            )})
    public ResponseEntity<Ingredient> addIngredient(@Valid @RequestBody Ingredient ingredient){
        ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(ingredient);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение ингредиента",
            description = "Пользователь должен ввести ID Ингредиента и заполнить/изменить все поля)"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингридиент найден",
                    content = {@Content (mediaType = "application/json",
                            schema = @Schema(implementation = Ingredient.class))}
            )})
    public ResponseEntity<Ingredient> editIngredient(@PathVariable long id,@Valid @RequestBody Ingredient ingredient) {
        ingredient = ingredientService.editIngredient(id, ingredient);
        if(ingredient == null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление ингредиента по ID",
            description = "Пользователь должен ввести ID Ингредиента, который необходимо удалить"
    )
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    public ResponseEntity<Void> deleteIngredient(@PathVariable long id){
        if(ingredientService.deleteIngredient(id)){
            ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    @Operation(
            summary = "Удаление всего списка Ингредиентов",
            description = "После данной операции, все Ингредиенты будут удалены"
    )
    public ResponseEntity<Void> deleteAll(){
        ingredientService.deleteAll();
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationException(MethodArgumentNotValidException ex){
                Map<String,String> errors = new HashMap<>();
                ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
    return errors;
    }

    //    public ResponseEntity<String> getIngredients(){
//        String values = ingredientService.getIngredients();
//        return ResponseEntity.ok(values);
//    }
}