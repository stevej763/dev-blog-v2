package com.stevedevblog.mvp.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class NewPostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldDisplayNewPostPage() throws Exception {
        //Note: include `andDo(print())` after the get() to print out the html for debugging
        this.mockMvc.perform(get("/new-post"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Add a new post")))
                .andExpect(content().string(containsString("navbar")))
                .andExpect(content().string(containsString("footer-container")));
    }
}