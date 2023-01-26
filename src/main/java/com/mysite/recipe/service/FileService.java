package com.mysite.recipe.service;

import java.io.File;

public interface FileService {

    boolean writeIngredientsToFile(String json);

    boolean writeRecipesToFile(String json);

    String readIngredientsFromFile();

    String readRecipesFromFile();

    boolean isFileExistsRec();

    void createFileRec();

    boolean isFileExistsIngr();

    void createFileIngr();
}
