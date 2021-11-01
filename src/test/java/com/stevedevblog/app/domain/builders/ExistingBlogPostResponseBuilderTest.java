package com.stevedevblog.app.domain.builders;

import com.stevedevblog.app.domain.ExistingBlogPostResponse;
import com.stevedevblog.app.domain.PersistedBlogPost;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

class ExistingBlogPostResponseBuilderTest {

    @Test
    public void buildShouldConvertAPersistedBlogPostIntoAnExistingBlogPostResponse() {
        PersistedBlogPost persistedBlogPost = new PersistedBlogPost("123", null, null, null, null, LocalDateTime.now(), null);
        ExistingBlogPostResponse result = ExistingBlogPostResponseBuilder.build(persistedBlogPost);
        assertThat(result.getId(), Is.is("123"));
    }

    @Test
    public void shouldConvertDateFieldToAPrettyString() {
        LocalDateTime staticDate = LocalDateTime.of( 2021, 1, 1, 0, 0, 0 );
        PersistedBlogPost persistedBlogPost = new PersistedBlogPost("123", null, null, null, null, staticDate, null);
        ExistingBlogPostResponse result = ExistingBlogPostResponseBuilder.build(persistedBlogPost);
        assertThat(result.getId(), Is.is("123"));
        assertThat(result.getPrettyDate(), Is.is("Jan 01 2021"));
    }

}