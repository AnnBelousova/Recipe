package com.mysite.recipe.service;

public interface FileService {

    boolean writeIngredientsToFile(String json);

    boolean writeRecipesToFile(String json);

    String readIngredientsFromFile();

    String readRecipesFromFile();
}
