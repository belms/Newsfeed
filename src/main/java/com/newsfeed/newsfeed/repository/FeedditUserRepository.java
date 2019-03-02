package com.newsfeed.newsfeed.repository;

import com.newsfeed.newsfeed.model.NewsfeedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedditUserRepository extends JpaRepository<NewsfeedUser, Long>, QueryByExampleExecutor<NewsfeedUser> {

    Optional<NewsfeedUser> findByUsername(String username);
}
