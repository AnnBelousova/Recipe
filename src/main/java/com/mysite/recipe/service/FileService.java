package com.mysite.recipe.service;

import java.io.File;
import java.nio.file.Path;

public interface FileService {

    boolean writeIngredientsToFile(String json);

    boolean deleteIngredientsDataFromFile();

    boolean writeRecipesToFile(String json);

    Path createTempFileIngr(String suffix);

    Path createTempFileRec(String suffix);

    String readIngredientsFromFile();

    String readRecipesFromFile();

    boolean deleteRecipesDataFromFile();

    boolean isFileExistsRec();

    void createFileRec();

    boolean isFileExistsIngr();

    void createFileIngr();

    File getDataFileRec();

    File getDataFileIngr();
}
