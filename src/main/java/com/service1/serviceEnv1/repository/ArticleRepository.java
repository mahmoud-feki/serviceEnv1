package com.service1.serviceEnv1.repository;

import com.service1.serviceEnv1.entity.Article;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mafeki
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {

    Optional<Article> findByCode(String code);
}
