package com.newsfeed.newsfeed.model;

public class ArticleForm {

    private String author;

    private String link;

    private String articleName;

    public ArticleForm() {
    }

    public ArticleForm(String author, String link, String articleName) {
        this.author = author;
        this.link = link;
        this.articleName = articleName;
    }

    //----Getters and setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }
}
