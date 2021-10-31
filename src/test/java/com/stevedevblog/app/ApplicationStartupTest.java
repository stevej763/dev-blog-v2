package com.stevedevblog.app;

import com.stevedevblog.app.controllers.HomeController;
import com.stevedevblog.app.controllers.admin.NewPostController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ApplicationStartupTest {

	@Autowired
	private HomeController homeController;
	@Autowired
	private NewPostController newPostController;


	@Test
	void shouldLoadControllersIntoApplicationContextOnStartup() {
		assertThat(this.homeController).isNotNull();
		assertThat(this.newPostController).isNotNull();
	}

}
