package com.newsfeed.newsfeed.service.impl;

import com.newsfeed.newsfeed.model.NewsfeedUser;
import com.newsfeed.newsfeed.repository.FeedditUserRepository;
import com.newsfeed.newsfeed.service.FeedditUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedditUserServiceImpl implements FeedditUserService {

    @Autowired
    private FeedditUserRepository feedditUserRepository;

    @Override
    public Optional<NewsfeedUser> findByUsername(String username) {
        return feedditUserRepository.findByUsername(username);
    }


}
