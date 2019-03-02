package com.newsfeed.newsfeed.repository;

import com.newsfeed.newsfeed.model.Article;
import com.newsfeed.newsfeed.model.NewsfeedUser;
import com.newsfeed.newsfeed.model.Voting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface VotingRepository extends JpaRepository<Voting, Long>, QueryByExampleExecutor<Voting> {

    Optional<Voting> getByNewsfeedUserVotedAndArticle(NewsfeedUser newsfeedUser, Article article);

    Voting getByNewsfeedUserVoted(NewsfeedUser newsfeedUser);
}
