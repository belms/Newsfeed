package com.newsfeed.newsfeed.service.impl;

import com.newsfeed.newsfeed.model.Article;
import com.newsfeed.newsfeed.model.NewsfeedUser;
import com.newsfeed.newsfeed.repository.ArticleRepository;
import com.newsfeed.newsfeed.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article getByArticleId(Long id) {
        return articleRepository.getByArticleId(id);
    }

    @Override
    public Page<Article> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void delete(Long id) {
        articleRepository.delete(id);
    }


    @Override
    public Page<Article> getByNewsfeedUser(NewsfeedUser newsfeedUser, Pageable pr) {
        return articleRepository.getByNewsfeedUser(newsfeedUser, pr);
    }

    @Override
    public Page<Article> getByVotesIsGreaterThanEqual(int vote, Pageable pg) {
        return articleRepository.getByVotesIsGreaterThanEqual(vote, pg);
    }

    @Override
    public Page<Article> getByArticleNameContainingIgnoreCase(String title, Pageable pg) {
        return articleRepository.getByArticleNameContainingIgnoreCase(title, pg);
    }


}
