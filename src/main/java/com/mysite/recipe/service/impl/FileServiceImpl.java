package com.mysite.recipe.service.impl;

import com.mysite.recipe.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileService {
    @Value("${pathToDataFile}")
    private String dataFilePath;
    @Value("${nameOfDataIngr}")
    private String dataFileNameIngr;

    @Value("${nameOfDataRec}")
    private String dataFileNameRec;

    @Override
    public boolean writeIngredientsToFile(String json) {
        Path path = Path.of(dataFilePath, dataFileNameIngr);
        try {
            deleteIngredientsDataFromFile();
            Files.writeString(path, json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public String readIngredientsFromFile() {
        Path path = Path.of(dataFilePath, dataFileNameIngr);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private boolean deleteIngredientsDataFromFile() {
        Path path = Path.of(dataFilePath, dataFileNameIngr);
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean writeRecipesToFile(String json) {
        Path path = Path.of(dataFilePath, dataFileNameRec);
        try {
            deleteRecipesDataFromFile();
            Files.writeString(path, json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public String readRecipesFromFile() {
        Path path = Path.of(dataFilePath, dataFileNameRec);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private boolean deleteRecipesDataFromFile() {
        Path path = Path.of(dataFilePath, dataFileNameRec);
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean isFileExistsRec() {
        Path path = Path.of(dataFilePath ,dataFileNameRec);
        File file = new File(path.toUri());
        return file.exists() && !file.isDirectory();
    }

    @Override
    public void createFileRec() {
        Path path = Path.of(dataFilePath, dataFileNameRec);
        try {
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isFileExistsIngr() {
        Path path = Path.of(dataFilePath ,dataFileNameIngr);
        File file = new File(path.toUri());
        return file.exists() && !file.isDirectory();
    }

    @Override
    public void createFileIngr() {
        Path path = Path.of(dataFilePath, dataFileNameIngr);
        try {
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
