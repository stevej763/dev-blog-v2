package com.stevedevblog.app.controllers;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldDisplayIndexPage() throws Exception {
        //Note: include `andDo(print())` after the get() to print out the html for debugging
        this.mockMvc.perform(get(HomeController.PATH))
                .andExpect(status().isOk())
                .andExpect(content().string(hasTitle()))
                .andExpect(content().string(hasNavbar()))
                .andExpect(content().string(hasPageContentDiv()))
                .andExpect(content().string(hasFooter()));
    }

    private Matcher<String> hasFooter() {
        return containsString("footer-container");
    }

    private Matcher<String> hasPageContentDiv() {
        return containsString("homepage-container");
    }

    private Matcher<String> hasNavbar() {
        return containsString("navbar");
    }

    private Matcher<String> hasIntroText() {
        return containsString(
                "Welcome to SteveDevBlog.com! My name is Steve and I am a Software Engineer. Here I write about personal projects I am working on and tutorials to help people like me.\n" +
                        "                Right now this website is my main project, take a look at some of my Build Log posts to follow along."
        );
    }

    private Matcher<String> hasTitle() {
        return containsString("Steve's Dev Blog - Home");
    }
}