package com.newsfeed.newsfeed.security.service;

import com.newsfeed.newsfeed.model.NewsfeedUser;
import com.newsfeed.newsfeed.service.FeedditUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DbUserDetailsService implements UserDetailsService {

    private FeedditUserService feedditUserService;

    @Autowired
    public DbUserDetailsService(FeedditUserService feedditUserService) {
        this.feedditUserService = feedditUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<NewsfeedUser> user = feedditUserService.findByUsername(username);
        return user.orElseThrow(() -> new UsernameNotFoundException(String.format("NewsfeedUser not found %s", username)));
    }
}
