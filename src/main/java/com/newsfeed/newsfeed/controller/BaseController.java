package com.newsfeed.newsfeed.controller;

import com.newsfeed.newsfeed.model.NewsfeedUser;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController {

    protected NewsfeedUser getAuthanticatedUser() {
        return (NewsfeedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
