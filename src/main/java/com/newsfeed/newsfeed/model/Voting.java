package com.newsfeed.newsfeed.model;

import javax.persistence.*;

@Entity
public class Voting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VOTING_ID", nullable = false)
    private Long votingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_voted", nullable = false)
    private NewsfeedUser newsfeedUserVoted;

    @Column(name = "VOTE")
    @Convert(converter = VoteAttributeConverter.class)
    private Vote vote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    public Voting(NewsfeedUser newsfeedUserVoted, Vote vote, Article article) {
        this.newsfeedUserVoted = newsfeedUserVoted;
        this.vote = vote;
        this.article = article;
    }

    public Voting() {
    }

    public Long getVotingId() {
        return votingId;
    }

    public void setVotingId(Long votingId) {
        this.votingId = votingId;
    }

    public NewsfeedUser getNewsfeedUserVoted() {
        return newsfeedUserVoted;
    }

    public void setNewsfeedUserVoted(NewsfeedUser newsfeedUserVoted) {
        this.newsfeedUserVoted = newsfeedUserVoted;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
