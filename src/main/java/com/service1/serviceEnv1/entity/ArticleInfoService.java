/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service1.serviceEnv1.entity;

/**
 *
 * @author mafeki
 */
public class ArticleInfoService {

    private String code;
    private int quantite;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public ArticleInfoService() {
    }

    public ArticleInfoService(String code, int quantite) {
        this.code = code;
        this.quantite = quantite;
    }
}
