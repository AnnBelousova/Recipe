package com.mysite.recipe.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class InfoController {
    @GetMapping("/")
    public String appRunStr(){
        return "Приложение запущено!";
    }

    @GetMapping("/info")
    public String myInfo(){
        String strInfo = " Анна Белоусова," +
                " Проект: Рецепты," +
                " В проекте будет список рецептов," +
                " Дата создания:01.01.2023";
        return strInfo;
    }
}
