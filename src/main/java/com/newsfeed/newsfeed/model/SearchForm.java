package com.newsfeed.newsfeed.model;

public class SearchForm {
    private String articleName;

    public SearchForm() {
    }

    public SearchForm(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }
}
