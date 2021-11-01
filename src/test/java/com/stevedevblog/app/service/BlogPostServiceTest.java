package com.stevedevblog.app.service;

import com.stevedevblog.app.domain.*;
import com.stevedevblog.app.repository.BlogPostRepository;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class BlogPostServiceTest {

    private final BlogPostRepository blogPostRepository = mock(BlogPostRepository.class);
    private final NewPostRequest newPostRequest = mock(NewPostRequest.class);
    private final LocalDateTime date2018 = LocalDateTime.of( 2018, 1, 1, 0, 0, 0 );
    private final LocalDateTime date2019 = LocalDateTime.of( 2019, 1, 1, 0, 0, 0 );
    private final LocalDateTime date2020 = LocalDateTime.of( 2020, 1, 1, 0, 0, 0 );
    private final LocalDateTime date2021 = LocalDateTime.of( 2021, 1, 1, 0, 0, 0 );
    private final String id = "1";

    private final PersistedBlogPost persistedBlogPost = new PersistedBlogPost("1", "title1", "description", "", "", LocalDateTime.now(), PostCategory.BUILD_LOG);
    private final EditPostRequest editedPost = new EditPostRequest(id, "title1", "description", "", "");

    private final List<PersistedBlogPost> persistedBlogPosts = List.of(
            new PersistedBlogPost("2", "title2", "description", "", "middle post", date2020, PostCategory.BUILD_LOG),
            new PersistedBlogPost("1", "title1", "description", "", "older post", date2019, PostCategory.BUILD_LOG),
            new PersistedBlogPost("3", "title3", "description", "", "newest post", date2021, PostCategory.BUILD_LOG),
            new PersistedBlogPost("4", "title4", "description", "", "oldest post", date2018, PostCategory.BUILD_LOG)
    );
    private final List<ExistingBlogPostResponse> existingBlogPostResponses = List.of(
            new ExistingBlogPostResponse("3", "post2021", "description", "", "", "2021", PostCategory.TESTING),
            new ExistingBlogPostResponse("2", "post2020", "description", "", "", "2020", PostCategory.DESIGN_PATTERNS),
            new ExistingBlogPostResponse("1", "post2019", "description", "", "", "2019", PostCategory.BUILD_LOG),
            new ExistingBlogPostResponse("4", "post2018", "description", "", "", "2018", PostCategory.BUILD_LOG)
    );

    private final BlogPostService underTest = new BlogPostService(blogPostRepository);


    @Test
    public void shouldRequestRepositoryToAddNewPostToDatabase() {
//  Not sure how to really test this. My concrete PersistedBlogPost is created in a private method within this unit
//  I have no way of knowing what the ID of the new object is going to be, so best I can do is verify an object gets passed
//  onto the repo. There is probably a good way around this, but for now it isn't the main focus. If I had a factory or a builder
//  mocked, I could stub an object as a response which I supposed is one way to do it.
        when(blogPostRepository.insert(any(PersistedBlogPost.class))).thenReturn(persistedBlogPost);

        underTest.addPost(newPostRequest);

        verify(blogPostRepository).insert(any(PersistedBlogPost.class));
    }
    
    @Test
    public void getPostsShouldReturnAnEmptyListFromDbWhenThereAreNoPosts() {
        when(blogPostRepository.findAll()).thenReturn(Collections.emptyList());
        List<ExistingBlogPostResponse> result = underTest.getPosts();
        assertThat(result, is(Collections.emptyList()));
    }

    @Test
    public void getPostsShouldReturnAnListOfExistingBlogPostResponsesFromDbSortedByDateDescending() {
        when(blogPostRepository.findAll()).thenReturn(persistedBlogPosts);
        List<ExistingBlogPostResponse> result = underTest.getPosts();
        assertThat(result, is(existingBlogPostResponses));
    }

    @Test
    public void twoPostsFromTheSameDayShouldBeOrderedCorrectly() {
        List<PersistedBlogPost> twoPostsFromSameDay = List.of(
                new PersistedBlogPost("2", "title2", "description", "", "middle post", LocalDateTime.now(), PostCategory.BUILD_LOG),
                new PersistedBlogPost("1", "title1", "description", "", "older post", LocalDateTime.now().minusHours(1), PostCategory.BUILD_LOG)
        );
        when(blogPostRepository.findAll()).thenReturn(twoPostsFromSameDay);

        List<ExistingBlogPostResponse> expectedResponse = List.of(
                new ExistingBlogPostResponse("2", "now", "description", "", "first post", "2021", PostCategory.BUILD_LOG),
                new ExistingBlogPostResponse("1", "an hour ago", "description", "", "last post", "2021", PostCategory.BUILD_LOG)
        );

        List<ExistingBlogPostResponse> result = underTest.getPosts();
        assertThat(result, is(expectedResponse));
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
                new PersistedBlogPost("1", "title1", "description", "", "", date2021, PostCategory.BUILD_LOG)
        );
        when(blogPostRepository.findAll()).thenReturn(persistedPosts);
        List<ExistingBlogPostResponse> result = underTest.getLatestPosts();
        assertThat(result.size(), is(1));
    }

    @Test
    public void  getLatestPostsShouldReturnAListOfExistingPostsSortedByDateDescending() {
        List<ExistingBlogPostResponse> expectedResponse = List.of(
                new ExistingBlogPostResponse("3", "title1", "description", "", "first post", "2021", PostCategory.BUILD_LOG),
                new ExistingBlogPostResponse("2", "title1", "description", "", "middle post", "2020", PostCategory.BUILD_LOG),
                new ExistingBlogPostResponse("1", "title1", "description", "", "last post", "2019", PostCategory.BUILD_LOG)
        );
        when(blogPostRepository.findAll()).thenReturn(persistedBlogPosts);
        List<ExistingBlogPostResponse> result = underTest.getLatestPosts();
        assertThat(result, is(expectedResponse));
    }

    @Test
    public void  getLatestPostsShouldReturnAnListOfThreeExistingBlogPostResponsesFromDbWhenThereExactlyThreePosts() {
        List<PersistedBlogPost> persistedPosts = List.of(
                new PersistedBlogPost("1", "title1", "description", "", "", date2019, PostCategory.BUILD_LOG),
                new PersistedBlogPost("2", "title2", "description", "", "", date2020, PostCategory.DESIGN_PATTERNS),
                new PersistedBlogPost("3", "title3", "description", "", "", date2021, PostCategory.TESTING)
        );
        when(blogPostRepository.findAll()).thenReturn(persistedPosts);
        List<ExistingBlogPostResponse> result = underTest.getLatestPosts();
        assertThat(result.size(), is(3));
    }

    @Test
    public void  getLatestPostsShouldReturnAnListOfThreeExistingBlogPostResponsesFromDbWhenThereAreMoreThanThreePosts() {
        when(blogPostRepository.findAll()).thenReturn(persistedBlogPosts);
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
}
