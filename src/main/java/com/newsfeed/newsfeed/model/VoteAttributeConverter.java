package com.newsfeed.newsfeed.model;

import javax.persistence.AttributeConverter;

public class VoteAttributeConverter implements AttributeConverter<Vote, Character> {

    @Override
    public Character convertToDatabaseColumn(Vote attribute) {
        return attribute.getVoteCharacter();
    }

    @Override
    public Vote convertToEntityAttribute(Character dbData) {
        return Vote.fromVoteCharacter(dbData);
    }
}
