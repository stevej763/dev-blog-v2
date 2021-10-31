package com.stevedevblog.app.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class NewPostResponseControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void shouldDisplayNewPostPage() throws Exception {
//        //Note: include `andDo(print())` after the get() to print out the html for debugging
//        this.mockMvc.perform(get("/new-post"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Add a new post")))
//                .andExpect(content().string(containsString("navbar")))
//                .andExpect(content().string(containsString("footer-container")));
//    }
}