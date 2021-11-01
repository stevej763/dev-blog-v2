package com.stevedevblog.app.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class AboutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldDisplayAboutPageForAnyUser() throws Exception {
        //Note: include `andDo(print())` after the get() to print out the html for debugging
        this.mockMvc.perform(get(AboutController.PATH))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("about-me-container")))
                .andExpect(content().string(containsString("about-list")));

    }

}