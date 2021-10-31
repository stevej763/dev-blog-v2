package com.stevedevblog.app.service;

import com.stevedevblog.app.domain.*;
import com.stevedevblog.app.domain.builders.ExistingBlogPostResponseBuilder;
import com.stevedevblog.app.domain.builders.PersistedBlogPostBuilder;
import com.stevedevblog.app.repository.BlogPostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BlogPostService {

    Logger LOGGER = LoggerFactory.getLogger(BlogPostService.class);

    private static final int MAX_NUMBER_OF_LATEST_POSTS = 3;

    private final BlogPostRepository blogPostRepository;

    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public PersistedBlogPost addPost(NewPostResponse newPostResponse) {
        PersistedBlogPost persistedBlogPost = convertToPersistedObject(newPostResponse);
        try {
            LOGGER.info("adding post with id: {}", persistedBlogPost.getId());
            return blogPostRepository.insert(persistedBlogPost);
        } catch (Exception e) {
            return null;
        }
    }

    public List<ExistingBlogPostResponse> getPosts() {
        List<PersistedBlogPost> persistedBlogPosts = blogPostRepository.findAll();
        return persistedBlogPosts.stream().map(ExistingBlogPostResponseBuilder::build).collect(Collectors.toList());
    }

    public List<ExistingBlogPostResponse> getLatestPosts() {
        List<ExistingBlogPostResponse> posts = getPosts();
        if(posts.size() < MAX_NUMBER_OF_LATEST_POSTS) {
            return posts;
        }
        return posts.subList(0, MAX_NUMBER_OF_LATEST_POSTS);

    }

    public PersistedBlogPost updatePost(EditPostResponse editPostResponse) {
        Optional<PersistedBlogPost> optionalOriginalPost = blogPostRepository.findById(editPostResponse.getId());
        if(optionalOriginalPost.isPresent()) {
            PersistedBlogPost originalPost = optionalOriginalPost.get();
            PersistedBlogPost postUpdate = new PersistedBlogPost(
                    editPostResponse.getId(),
                    editPostResponse.getTitle(),
                    editPostResponse.getDescription(),
                    editPostResponse.getHeaderImageUrl(),
                    editPostResponse.getPostContent(),
                    originalPost.getPublishDate(),
                    originalPost.getCategory()
            );
            PersistedBlogPost persistedBlogPost = blogPostRepository.save(postUpdate);
            LOGGER.info("updating post id={} post={}", postUpdate.getId(), persistedBlogPost);
            return persistedBlogPost;
        }
        LOGGER.error("Error when updating post with id: {}", editPostResponse.getId());
        return null;

    }

    public ExistingBlogPostResponse getPostById(String id) {
        LOGGER.info("finding post with id: {}", id);
        Optional<PersistedBlogPost> result = blogPostRepository.findById(id);
        if(result.isPresent()) {
            PersistedBlogPost originalPost = result.get();
            ExistingBlogPostResponse existingPost = ExistingBlogPostResponseBuilder.build(originalPost);
            LOGGER.info("found post with id: {}", existingPost.getId());
            return existingPost;
        }
        LOGGER.error("Error when finding post with id: {}", id);
        return null;
    }

    private PersistedBlogPost convertToPersistedObject(NewPostResponse newPostResponse) {
        return new PersistedBlogPostBuilder().build(
                newPostResponse.getTitle(),
                newPostResponse.getDescription(),
                newPostResponse.getHeaderImageUrl(),
                newPostResponse.getPostContent(),
                newPostResponse.getCategory());
    }

}
