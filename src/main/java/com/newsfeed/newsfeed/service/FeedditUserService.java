package com.newsfeed.newsfeed.service;

import com.newsfeed.newsfeed.model.NewsfeedUser;

import java.util.Optional;

public interface FeedditUserService {

    Optional<NewsfeedUser> findByUsername(String username);
}
