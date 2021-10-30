package com.stevedevblog.mvp.service;

import com.stevedevblog.mvp.domain.EditPostResponse;
import com.stevedevblog.mvp.domain.NewPostResponse;
import com.stevedevblog.mvp.domain.PersistedBlogPost;
import com.stevedevblog.mvp.domain.PersistedBlogPostBuilder;
import com.stevedevblog.mvp.repository.BlogPostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class BlogPostService {

    Logger LOGGER = LoggerFactory.getLogger(BlogPostService.class);

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

    private PersistedBlogPost convertToPersistedObject(NewPostResponse newPostResponse) {
        return new PersistedBlogPostBuilder().build(
            newPostResponse.getTitle(),
            newPostResponse.getDescription(),
            newPostResponse.getHeaderImageUrl(),
            newPostResponse.getPostContent(),
            newPostResponse.getCategory());
    }

    public List<PersistedBlogPost> getPosts() {
        List<PersistedBlogPost> posts = blogPostRepository.findAll();
        Collections.reverse(posts);
        return posts;
    }

    public List<PersistedBlogPost> getLatestPosts(int numberOfPosts) {
        List<PersistedBlogPost> posts = getPosts();
        return posts.subList(0, numberOfPosts);
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
            LOGGER.info("updating post with id: {}", postUpdate.getId());
            return blogPostRepository.save(postUpdate);
        }
        LOGGER.error("Error when updating post with id: {}", editPostResponse.getId());
        return null;

    }

    public Optional<PersistedBlogPost> getPostById(String id) {
        LOGGER.info("finding post with id: {}", id);
        return blogPostRepository.findById(id);
    }

}
