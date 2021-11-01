package com.stevedevblog.app.controllers.admin;

import com.stevedevblog.app.security.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static com.stevedevblog.app.security.UserRole.ADMIN;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class NewPostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldDisplayNewPostPageForAuthenticatedUsed() throws Exception {
        //Note: include `andDo(print())` after the get() to print out the html for debugging
        RequestBuilder requestBuilder = formLogin().user("test@tester.de").password("test");
        this.mockMvc.perform(get("/new-post"))
                .andExpect(content().string(containsString("Add a new post")))
                .andExpect(content().string(containsString("navbar")))
                .andExpect(content().string(containsString("footer-container")));
    }

    @Test
    public void shouldRedirectGuestUsersToLoginPage() throws Exception {
        this.mockMvc.perform(get("/new-post"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
}