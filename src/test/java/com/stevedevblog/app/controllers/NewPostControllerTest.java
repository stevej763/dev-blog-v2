package com.stevedevblog.app.controllers;

import com.stevedevblog.app.security.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static com.stevedevblog.app.security.UserRole.ADMIN;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
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