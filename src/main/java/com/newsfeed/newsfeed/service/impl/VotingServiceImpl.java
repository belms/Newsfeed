package com.newsfeed.newsfeed.service.impl;

import com.newsfeed.newsfeed.model.Article;
import com.newsfeed.newsfeed.model.NewsfeedUser;
import com.newsfeed.newsfeed.model.Vote;
import com.newsfeed.newsfeed.model.Voting;
import com.newsfeed.newsfeed.repository.ArticleRepository;
import com.newsfeed.newsfeed.repository.VotingRepository;
import com.newsfeed.newsfeed.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VotingServiceImpl implements VotingService {

    private final ArticleRepository articleRepository;

    private final VotingRepository votingRepository;

    @Autowired
    public VotingServiceImpl(ArticleRepository articleRepository, VotingRepository votingRepository) {
        this.articleRepository = articleRepository;
        this.votingRepository = votingRepository;
    }

    @Override
    public String castVote(NewsfeedUser newsfeedUser, Article article, Vote vote) {
        String msg;
        Optional<Voting> givenVoting = votingRepository.getByNewsfeedUserVotedAndArticle(newsfeedUser, article);
        givenVoting.ifPresent(article::removeVoting);
        Voting newVoting = new Voting(newsfeedUser, vote, article);

        if (vote.getVoteCharacter() == '+') {
            msg = "Your vote has been successfully received.";
        } else msg = "Your vote has been successfully revoked.";

        article.addVoting(newVoting);

        articleRepository.save(article);
        return msg;
    }

    @Override
    public Optional<Voting> getByFeedditUserVotedAndArticle(NewsfeedUser newsfeedUser, Article article) {
        return votingRepository.getByNewsfeedUserVotedAndArticle(newsfeedUser, article);
    }

    @Override
    public Voting getByFeedditUserVoted(NewsfeedUser newsfeedUser) {
        return votingRepository.getByNewsfeedUserVoted(newsfeedUser);
    }

    @Override
    public void save(Voting voting) {
        votingRepository.save(voting);
    }

    @Override
    public void delete(Long voting) {
        votingRepository.delete(voting);
    }

}
