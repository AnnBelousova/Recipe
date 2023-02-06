package com.mysite.recipe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionRecipeIngredientExisting extends RuntimeException{

    public ExceptionRecipeIngredientExisting(){
        super("Список уже содержит такой компонент");
    }
    public ExceptionRecipeIngredientExisting(String message){
        super(message);
    }
}
