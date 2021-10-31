package com.stevedevblog.app.repository;

import com.stevedevblog.app.domain.PersistedBlogPost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BlogPostRepository extends MongoRepository<PersistedBlogPost, String> {

    public List<PersistedBlogPost> findFirst3ById();
    
}