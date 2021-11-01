package com.stevedevblog.app.service;

import com.stevedevblog.app.domain.EditPostRequest;
import com.stevedevblog.app.domain.ExistingBlogPostResponse;
import com.stevedevblog.app.domain.NewPostRequest;
import com.stevedevblog.app.domain.PersistedBlogPost;
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

    final Logger LOGGER = LoggerFactory.getLogger(BlogPostService.class);

    private static final int NUMBER_OF_LATEST_POSTS_TO_SHOW = 3;

    private final BlogPostRepository blogPostRepository;

    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public PersistedBlogPost addPost(NewPostRequest newPostRequest) {
        PersistedBlogPost persistedBlogPost = convertToPersistedObject(newPostRequest);
        try {
            LOGGER.info("adding post with id: {}", persistedBlogPost.getId());
            return blogPostRepository.insert(persistedBlogPost);
        } catch (Exception e) {
            return null;
        }
    }

    public List<ExistingBlogPostResponse> getPosts() {
        List<PersistedBlogPost> persistedBlogPosts = blogPostRepository.findAll();
        return persistedBlogPosts.stream()
                .sorted((o1, o2) -> (
                        o2.getPublishDate().compareTo(o1.getPublishDate())))
                .map(ExistingBlogPostResponseBuilder::build)
                .collect(Collectors.toList());
    }

    public List<ExistingBlogPostResponse> getLatestPosts() {
        List<ExistingBlogPostResponse> posts = getPosts();
        if(posts.size() < NUMBER_OF_LATEST_POSTS_TO_SHOW) {
            return posts;
        }
        return posts.subList(0, NUMBER_OF_LATEST_POSTS_TO_SHOW);

    }

    public PersistedBlogPost updatePost(EditPostRequest editPostRequest) {
        Optional<PersistedBlogPost> optionalOriginalPost = blogPostRepository.findById(editPostRequest.getId());
        if(optionalOriginalPost.isPresent()) {
            PersistedBlogPost originalPost = optionalOriginalPost.get();
            PersistedBlogPost postUpdate = new PersistedBlogPost(
                    editPostRequest.getId(),
                    editPostRequest.getTitle(),
                    editPostRequest.getDescription(),
                    editPostRequest.getHeaderImageUrl(),
                    editPostRequest.getPostContent(),
                    originalPost.getPublishDate(),
                    originalPost.getCategory()
            );
            PersistedBlogPost persistedBlogPost = blogPostRepository.save(postUpdate);
            LOGGER.info("updating post id={} post={}", postUpdate.getId(), persistedBlogPost);
            return persistedBlogPost;
        }
        LOGGER.error("Error when updating post with id: {}", editPostRequest.getId());
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

    private PersistedBlogPost convertToPersistedObject(NewPostRequest newPostRequest) {
        return new PersistedBlogPostBuilder().build(
                newPostRequest.getTitle(),
                newPostRequest.getDescription(),
                newPostRequest.getHeaderImageUrl(),
                newPostRequest.getPostContent(),
                newPostRequest.getCategory());
    }

}
