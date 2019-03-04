/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service1.serviceEnv1.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.service1.serviceEnv1.entity.ArticleInfoService;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author mafeki
 */
@Service
public class ArticleInfoServiceClient {

    @Value("${urlService}")
    private String urlService;
    @Value("${portService}")
    private String port;

    private static final Logger LOG = Logger.getLogger(ArticleService.class.getName());

    private final RestTemplate restTemplate;

    @Autowired
    public ArticleInfoServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getArticleWithQuantiteFallBack",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
            }
    )
    public Optional<ArticleInfoService> getArticleWithQuantite(String id) {
        ResponseEntity<ArticleInfoService> articleResponse
                = restTemplate.getForEntity("http://" + urlService + ":" + port, ArticleInfoService.class);
        if (articleResponse.getStatusCode() == HttpStatus.OK) {
            return Optional.ofNullable(articleResponse.getBody());
        } else {
            return Optional.empty();
        }

    }

    @SuppressWarnings("unused")
    Optional<ArticleInfoService> getArticleWithQuantiteFallBack(String id) {
        LOG.log(Level.INFO, "Reournig default Article for product {0}", id);
        ArticleInfoService response = new ArticleInfoService();
        response.setCode(id);
        response.setQuantite(0);
        return Optional.ofNullable(response);
    }

}
