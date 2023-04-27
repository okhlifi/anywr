package com.anywr.oki;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.anywr.oki.config.JwtGeneratorImpl;
import com.anywr.oki.dao.UserRepository;
import com.anywr.oki.model.User;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SecurityTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	JwtGeneratorImpl jwtService;

	@BeforeAll
	void initDatabase() {
		userRepository.save(new User("user1", "user1", "user1@any.com", "loginUser1", "pass123"));
	}

	@Test
	void testAuthentication() throws Exception {

		String username = "loginUser1";
		String password = "pass123";

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/login").contentType(MediaType.APPLICATION_JSON)
				.content("{\"userName\":\"" + username + "\",\"password\":\"" + password + "\"}"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.message").value("'Login Successful'"));
	}

	@Test
	void testInvalidAuthentication() throws Exception {
		String username = "invalidUser";
		String password = "invalidPassword";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/login").contentType(MediaType.APPLICATION_JSON)
				.content("{\"userName\":\"" + username + "\",\"password\":\"" + password + "\"}"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	void testAuthorization() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/userProfil")).andExpect(status().isUnauthorized());
	}

	@Test
	void testAuthorizationWithToken() throws Exception {
		User user = new User("loginUser1", "pass123");
		String token = jwtService.generateToken(user).get("token");

		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/user/userProfil").header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andExpect(content().string(
						"Current user :loginUser1 [firstName = user1 , lastName = user1, email = user1@any.com]"));

	}
}