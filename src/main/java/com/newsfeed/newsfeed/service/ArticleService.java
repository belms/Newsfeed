package com.newsfeed.newsfeed.service;

import com.newsfeed.newsfeed.model.Article;
import com.newsfeed.newsfeed.model.NewsfeedUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService {

    Article getByArticleId(Long id);

    Page<Article> findAll(Pageable pageable);

    Article save(Article article);

    void delete(Long id);

    Page<Article> getByNewsfeedUser(NewsfeedUser newsfeedUser, Pageable pr);

    Page<Article> getByVotesIsGreaterThanEqual(int vote, Pageable pg);

    Page<Article> getByArticleNameContainingIgnoreCase(String title, Pageable pg);

}
