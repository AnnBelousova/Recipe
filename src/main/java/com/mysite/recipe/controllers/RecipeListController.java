package com.mysite.recipe.controllers;

import com.mysite.recipe.model.Recipe;
import com.mysite.recipe.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "Контроллер для работы со списком 'Рецептов'")
public class RecipeListController {

    private final RecipeService recipeService;

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение рецепта по ID",
            description = "Пользователь должен ввести ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт найден",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            )})
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    public ResponseEntity<Recipe> getIngredientById(@PathVariable long id) {
        Recipe recipe = recipeService.getRecipeById(id);
        if (recipe == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @PostMapping()
    @Operation(
            summary = "Добавление нового рецепта",
            description = "Пользователь должен заполнить все поля, кроме поля ID (присвоится автоматически)"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт найден",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            )})
    public ResponseEntity<Long> addRecipe(@Valid @RequestBody Recipe recipe) {
        long id = recipeService.addRecipe(recipe);
        return ResponseEntity.ok(id);
    }

    @GetMapping()
    @Operation(
            summary = "Получение всего списка рецептов",
            description = "Будет выведеен список всех ингридиентов из базы данных"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт найден",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            )})
    public Collection<Recipe> getIngredients() {
        return this.recipeService.getRecipes();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение рецепта",
            description = "Пользователь должен ввести ID рецепта и заполнить/изменить все поля)"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт найден",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))}
            )})
    public ResponseEntity<Recipe> editRecipe(@PathVariable long id, @Valid @RequestBody Recipe recipe) {
        recipe = recipeService.editRecipe(id, recipe);
        if (recipe == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/{id}")
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    @Operation(
            summary = "Удаление рецепта по ID",
            description = "Пользователь должен ввести ID рецепта, который необходимо удалить"
    )
    public ResponseEntity<Void> deleteRecipe(@PathVariable long id) {
        if (recipeService.deleteRecipe(id)) {
            ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    @Operation(
            summary = "Удаление всего списка рецептов",
            description = "После данной операции, все рецепты будут удалены"
    )
    public ResponseEntity<Void> deleteAll() {
        recipeService.deleteAll();
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(NotFoundException notFoundException) {
        return notFoundException.getMessage();
    }

    @GetMapping("/recipesList")
    public ResponseEntity<Object> getRecipesList(){
        try {
            Path path = recipeService.createListOfRecipes();
            if(Files.size(path) == 0){
                return ResponseEntity.noContent().build();
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(Files.size(path))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment", "filename=\"recipesList.json\"")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    //    public ResponseEntity<String> getRecipes(){
    //        String values = recipeService.getRecipes();
    //        return ResponseEntity.ok(values);
    //    }

}
