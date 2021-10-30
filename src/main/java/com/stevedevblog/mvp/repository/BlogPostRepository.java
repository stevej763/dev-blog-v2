package com.stevedevblog.mvp.repository;

import com.stevedevblog.mvp.domain.PersistedBlogPost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface BlogPostRepository extends MongoRepository<PersistedBlogPost, String> {

    public List<PersistedBlogPost> findFirst3ById();
    
}