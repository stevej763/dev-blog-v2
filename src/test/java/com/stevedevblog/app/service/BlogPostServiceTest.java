package com.stevedevblog.app.service;

import com.stevedevblog.app.domain.*;
import com.stevedevblog.app.domain.builders.PersistedBlogPostBuilder;
import com.stevedevblog.app.repository.BlogPostRepository;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class BlogPostServiceTest {

    private final BlogPostRepository blogPostRepository = mock(BlogPostRepository.class);
    private final BlogPostService underTest = new BlogPostService(blogPostRepository);
    private final NewPostResponse newPostResponse = mock(NewPostResponse.class);
    private final PersistedBlogPost persistedBlogPost = new PersistedBlogPost("1", "title1", "description", "", "", new Date(), PostCategory.BUILD_LOG);
    private final String id = "1";
    private final EditPostResponse editedPost = new EditPostResponse(id, "title1", "description", "", "", new Date(), PostCategory.BUILD_LOG);

    @Test
    public void shouldRequestRepositoryToAddNewPostToDatabase() {
//  Not sure how to really test this. My concrete PersistedBlogPost is created in a private method within this unit
//  I have no way of knowing what the ID of the new object is going to be, so best I can do is verify an object gets passed
//  onto the repo. There is probably a good way around this, but for now it isn't the main focus. If I had a factory or a builder
//  mocked, I could stub an object as a response which I supposed is one way to do it.

        String id = UUID.randomUUID().toString();
        Date publishDate = new Date();
        PersistedBlogPost realPost = new PersistedBlogPost(
                id,
                "title",
                "description",
                "headerImageUrl",
                "postContent",
                publishDate,
                PostCategory.BUILD_LOG);
        when(blogPostRepository.insert(any(PersistedBlogPost.class))).thenReturn(realPost);

        underTest.addPost(newPostResponse);
        verify(blogPostRepository).insert(any(PersistedBlogPost.class));
    }
    
    @Test
    public void getPostsShouldReturnAnEmptyListFromDbWhenThereAreNoPosts() {
        when(blogPostRepository.findAll()).thenReturn(Collections.emptyList());
        List<ExistingBlogPostResponse> result = underTest.getPosts();
        assertThat(result, is(Collections.emptyList()));
    }

    @Test
    public void getPostsShouldReturnAnListOfExistingBlogPostResponsesFromDbWhenThereArePosts() {
        List<PersistedBlogPost> persistedPosts = getPersistedPosts();
        List<ExistingBlogPostResponse> response = getExistingBlogPostResponses();
        when(blogPostRepository.findAll()).thenReturn(persistedPosts);
        List<ExistingBlogPostResponse> result = underTest.getPosts();
        assertThat(result, is(response));
    }

    @Test
    public void getLatestPostsShouldReturnAnEmptyListFromDbWhenThereAreNoPosts() {
        when(blogPostRepository.findAll()).thenReturn(Collections.emptyList());
        List<ExistingBlogPostResponse> result = underTest.getLatestPosts();
        assertThat(result, is(Collections.emptyList()));
    }

    @Test
    public void  getLatestPostsShouldReturnAllExistingBlogPostResponsesFromDbWhenThereAreLessThanThreePosts() {
        List<PersistedBlogPost> persistedPosts = List.of(
                new PersistedBlogPost("1", "title1", "description", "", "", new Date(), PostCategory.BUILD_LOG)
        );
        when(blogPostRepository.findAll()).thenReturn(persistedPosts);
        List<ExistingBlogPostResponse> result = underTest.getLatestPosts();
        assertThat(result.size(), is(1));
    }

    @Test
    public void  getLatestPostsShouldReturnAnListOfThreeExistingBlogPostResponsesFromDbWhenThereExactlyThreePosts() {
        List<PersistedBlogPost> persistedPosts = List.of(
                new PersistedBlogPost("1", "title1", "description", "", "", new Date(), PostCategory.BUILD_LOG),
                new PersistedBlogPost("2", "title2", "description", "", "", new Date(), PostCategory.DESIGN_PATTERNS),
                new PersistedBlogPost("3", "title3", "description", "", "", new Date(), PostCategory.TESTING)
        );
        when(blogPostRepository.findAll()).thenReturn(persistedPosts);
        List<ExistingBlogPostResponse> result = underTest.getLatestPosts();
        assertThat(result.size(), is(3));
    }

    @Test
    public void  getLatestPostsShouldReturnAnListOfThreeExistingBlogPostResponsesFromDbWhenThereAreMoreThanThreePosts() {
        List<PersistedBlogPost> persistedPosts = getPersistedPosts();
        when(blogPostRepository.findAll()).thenReturn(persistedPosts);
        List<ExistingBlogPostResponse> result = underTest.getLatestPosts();
        assertThat(result.size(), is(3));
    }

    @Test
    public void getPostByIdReturnsExistingBlogPostResponse() {
        when(blogPostRepository.findById(id)).thenReturn(Optional.of(persistedBlogPost));
        ExistingBlogPostResponse result = underTest.getPostById(id);
        assertThat(result.getId(), is(id));
    }

    @Test
    public void getPostByIdShouldReturnNullWhenResultIsEmpty() {
        when(blogPostRepository.findById(id)).thenReturn(Optional.empty());
        ExistingBlogPostResponse result = underTest.getPostById(id);
        assertThat(result, IsNull.nullValue());
    }

    @Test
    public void updatePostShouldUpdateOriginalPostInDatabase() {
        when(blogPostRepository.findById(id)).thenReturn(Optional.of(persistedBlogPost));
        when(blogPostRepository.save(persistedBlogPost)).thenReturn(persistedBlogPost);
        underTest.updatePost(editedPost);
        verify(blogPostRepository).save(persistedBlogPost);
    }

    @Test
    public void updatePostShouldReturnNullWhenTheIdDoesNotMatchAnyPosts() {
        when(blogPostRepository.findById(id)).thenReturn(Optional.empty());
        PersistedBlogPost result = underTest.updatePost(editedPost);
        assertThat(result, IsNull.nullValue());
    }


    private List<PersistedBlogPost> getPersistedPosts() {
        return List.of(
                new PersistedBlogPost("1", "title1", "description", "", "", new Date(), PostCategory.BUILD_LOG),
                new PersistedBlogPost("2", "title2", "description", "", "", new Date(), PostCategory.DESIGN_PATTERNS),
                new PersistedBlogPost("3", "title3", "description", "", "", new Date(), PostCategory.TESTING),
                new PersistedBlogPost("4", "title4", "description", "", "", new Date(), PostCategory.BUILD_LOG)
        );
    }

    private List<ExistingBlogPostResponse> getExistingBlogPostResponses() {
        return List.of(
                new ExistingBlogPostResponse("1", "title1", "description", "", "", "Oct 28th 2021", PostCategory.BUILD_LOG),
                new ExistingBlogPostResponse("2", "title2", "description", "", "", "Oct 29th 2021", PostCategory.DESIGN_PATTERNS),
                new ExistingBlogPostResponse("3", "title3", "description", "", "", "Oct 30th 2021", PostCategory.TESTING),
                new ExistingBlogPostResponse("4", "title4", "description", "", "", "Oct 31st 2021", PostCategory.BUILD_LOG)
        );
    }

}