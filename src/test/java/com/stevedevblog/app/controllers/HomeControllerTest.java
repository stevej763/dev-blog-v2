package com.stevedevblog.app.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void shouldDisplayIndexPage() throws Exception {
//        //Note: include `andDo(print())` after the get() to print out the html for debugging
//        this.mockMvc.perform(get(HomeController.PATH))
//                .andExpect(status().isOk())
//                .andExpect(content().string(hasTitle()))
//                .andExpect(content().string(hasIntroText()))
//                .andExpect(content().string(hasNavbar()))
//                .andExpect(content().string(hasPageContentDiv()))
//                .andExpect(content().string(hasFooter()));
//    }
//
//    private Matcher<String> hasFooter() {
//        return containsString("footer-container");
//    }
//
//    private Matcher<String> hasPageContentDiv() {
//        return containsString("homepage-container");
//    }
//
//    private Matcher<String> hasNavbar() {
//        return containsString("navbar");
//    }
//
//    private Matcher<String> hasIntroText() {
//        return containsString(
//                "Welcome to my website. This is some template text until " +
//                        "I sort out the actual content for the welcome message."
//        );
//    }
//
//    private Matcher<String> hasTitle() {
//        return containsString("Steve's Dev Blog - Home");
//    }
}