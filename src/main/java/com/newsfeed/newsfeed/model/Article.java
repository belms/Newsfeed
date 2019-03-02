package com.newsfeed.newsfeed.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ARTICLE", nullable = false)
    private Long articleId;

    @Column(name = "HEADLINE", nullable = false)
    private String articleName;

    @Column(name = "LINK", nullable = false)
    private String link;

    @Column(name = "AUTHOR", nullable = false)
    private String author;

    @Column(name = "VOTES", nullable = false)
    private int votes;

    @ManyToOne
    @JoinColumn(name = "BY_USER", nullable = false)
    private NewsfeedUser newsfeedUser;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Voting> votingList;

    public Article(Long id, String articleName, String link, String author, int votes, NewsfeedUser newsfeedUser) {
        this.articleId = id;
        this.articleName = articleName;
        this.link = link;
        this.author = author;
        this.votes = votes;
        this.newsfeedUser = newsfeedUser;
    }

    public Article(String articleName, String link, String author, int votes, NewsfeedUser newsfeedUser) {
        this.articleName = articleName;
        this.link = link;
        this.author = author;
        this.votes = votes;
        this.newsfeedUser = newsfeedUser;
    }

    // Default constructor
    public Article() {
    }

    //----- Getters & setters ----------------------------------
    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public NewsfeedUser getNewsfeedUser() {
        return newsfeedUser;
    }

    public void setNewsfeedUser(NewsfeedUser newsfeedUser) {
        this.newsfeedUser = newsfeedUser;
    }

    public List<Voting> getVotingList() {
        return votingList;
    }

    public void setVotingList(List<Voting> votingList) {
        this.votingList = votingList;
    }

    public void addVoting(Voting voting) {
        this.votingList.add(voting);

        if (voting.getVote() == Vote.POSITIVE) {
            this.votes++;
        } else {
            this.votes--;
        }
    }

    public void removeVoting(Voting voting) {
        this.votingList.remove(voting);

        if (voting.getVote() == Vote.POSITIVE) {
            this.votes--;
        } else {
            this.votes++;
        }
    }
}
