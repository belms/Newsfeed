package com.newsfeed.newsfeed.model;

import java.util.Arrays;

public enum Vote {
    POSITIVE('+'), NEGATIVE('-');

    private final Character voteCharacter;

    Vote(Character voteCharacter) {
        this.voteCharacter = voteCharacter;
    }

    public static Vote fromVoteCharacter(Character voteCharacter) {
        return Arrays.stream(Vote.values()).filter(vote -> vote.getVoteCharacter().equals(voteCharacter)).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public Character getVoteCharacter() {
        return voteCharacter;
    }
}
