package com.mysite.recipe.controllers;

import com.mysite.recipe.service.FileService;
import com.mysite.recipe.service.RecipeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Tag(name = "Файлы", description = "Контроллер для работы с файлами скачать/загрузить")
@RestController
@RequestMapping("/files")
public class FilesController {
    private final FileService fileService;
    private final RecipeService recipeService;

    public FilesController(FileService fileService, RecipeService recipeService) {
        this.fileService = fileService;
        this.recipeService = recipeService;
    }

    @GetMapping("/export-ingr")
    public ResponseEntity<InputStreamResource> downloadDataFileIngr() throws FileNotFoundException {
        File file = fileService.getDataFileIngr();
        if(file.exists()){
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"IngredientsLog.json\"")
                    .body(resource);
        }else {
            return ResponseEntity.noContent().build();
        }
    }
    @GetMapping(value = "/export-rec", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<InputStreamResource> downloadDataFileRec() throws FileNotFoundException {
        File file = fileService.getDataFileRec();
        if(file.exists()){
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"RecipesLog.json\"")
                    .body(resource);
        }else {
            return ResponseEntity.noContent().build();
        }
    }


    @PostMapping(value = "/import-rec", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadDataFileRec(@RequestParam MultipartFile file){
        fileService.deleteRecipesDataFromFile();
        File dataFile = fileService.getDataFileRec();
        fileService.createOutputStream(dataFile,file);
    }

    @PostMapping(value = "/import-ingr", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadDataFileIngr(@RequestParam MultipartFile file){
        fileService.deleteIngredientsDataFromFile();
        File dataFile = fileService.getDataFileIngr();
        fileService.createOutputStream(dataFile,file);
    }
}
