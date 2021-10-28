package com.stevedevblog.mvp.service;

import com.stevedevblog.mvp.domain.NewPostResponse;
import com.stevedevblog.mvp.domain.PersistedBlogPost;
import com.stevedevblog.mvp.domain.PostCategory;
import com.stevedevblog.mvp.repository.BlogPostRepository;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.*;

public class BlogPostServiceTest {

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
        BlogPostRepository blogPostRepository = mock(BlogPostRepository.class);
        NewPostResponse newPostResponse = mock(NewPostResponse.class);
        when(blogPostRepository.insert(any(PersistedBlogPost.class))).thenReturn(realPost);

        BlogPostService underTest = new BlogPostService(blogPostRepository);

        PersistedBlogPost result = underTest.addPost(newPostResponse);
        verify(blogPostRepository).insert(any(PersistedBlogPost.class));
        assertThat(result.getId(), is(id));
        assertThat(result.getPublishDate(), is(publishDate));
    }

}