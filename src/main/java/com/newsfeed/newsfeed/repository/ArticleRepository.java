package com.newsfeed.newsfeed.repository;

import com.newsfeed.newsfeed.model.Article;
import com.newsfeed.newsfeed.model.NewsfeedUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, QueryByExampleExecutor<Article> {

    Article getByArticleId(Long id);

    Page<Article> getByNewsfeedUser(NewsfeedUser newsfeedUser, Pageable pr);

    Page<Article> getByVotesIsGreaterThanEqual(int vote, Pageable pg);

    Page<Article> getByArticleNameContainingIgnoreCase(String title, Pageable pg);

}
