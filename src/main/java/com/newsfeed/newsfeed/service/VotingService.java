package com.newsfeed.newsfeed.service;

import com.newsfeed.newsfeed.model.Article;
import com.newsfeed.newsfeed.model.NewsfeedUser;
import com.newsfeed.newsfeed.model.Vote;
import com.newsfeed.newsfeed.model.Voting;

import java.util.Optional;

public interface VotingService {

    String castVote(NewsfeedUser newsfeedUser, Article article, Vote vote);

    Optional<Voting> getByFeedditUserVotedAndArticle(NewsfeedUser newsfeedUser, Article article);

    Voting getByFeedditUserVoted(NewsfeedUser newsfeedUser);

    void save(Voting voting);

    void delete(Long voting);

}
