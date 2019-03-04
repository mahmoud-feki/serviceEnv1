package com.service1.serviceEnv1.rest;

import com.service1.serviceEnv1.entity.Article;
import com.service1.serviceEnv1.service.ArticleService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mafeki
 */
@RestController
@RequestMapping("api/")
public class Controller {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/listeArticle")
    public List<Article> getListeArticle() {
        return articleService.getListeArticle();
    }

    @GetMapping("/listeArticleWithQuantite")
    public Optional<Article> getListeArticleWithQuantite(String code) {
        return articleService.findByQuantite(code);
    }

}
