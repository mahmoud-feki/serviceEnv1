/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service1.serviceEnv1.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.service1.serviceEnv1.entity.Article;
import com.service1.serviceEnv1.entity.ArticleInfoService;
import com.service1.serviceEnv1.repository.ArticleRepository;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author mafeki
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleInfoServiceClient articleInfoServiceClient;
    private final RestTemplate restTemplate;

    private static final Logger LOG = Logger.getLogger(ArticleService.class.getName());

    public ArticleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Article> getListeArticle() {
        return articleRepository.findAll();
    }

    public Optional<Article> findByQuantite(String code) {
        Optional<Article> article = articleRepository.findByCode(code);
        if (article.isPresent()) {
            Optional<ArticleInfoService> responseEntity = articleInfoServiceClient.getArticleWithQuantite(code);
            if (responseEntity.isPresent()) {
                Integer quantity = responseEntity.get().getQuantite();
                article.get().setQuantite(quantity);
            } else {
                LOG.log(Level.WARNING, "unable");
            }
        }
        return article;
    }

}
