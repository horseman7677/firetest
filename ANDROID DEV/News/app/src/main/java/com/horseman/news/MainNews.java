package com.horseman.news;

import java.util.ArrayList;

public class MainNews {
    private String status;
    private String totalResult;
    private ArrayList<ModalClass> articles;

    public MainNews(String status, String totalResult, ArrayList<ModalClass> articles) {
        this.status = status;
        this.totalResult = totalResult;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(String totalResult) {
        this.totalResult = totalResult;
    }

    public ArrayList<ModalClass> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<ModalClass> articles) {
        this.articles = articles;
    }
}
