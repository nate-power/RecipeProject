package ca.gbc.comp3095.RecipeProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipeProjectApplication {

	// connect to http://localhost:8049/

	// FORGOT PASSWORD FUNCTIONALITY NOTE:
		// to test forgot password email token functionality, we have created a Gmail account if you do not want to use
		// your personal email in account registration
			// email - nullrecipes@gmail.com
			// password - nullrecipes2021
		// there is also a test user we created in DataLoader.java (username: email-test), you can click Forgot Password
		// on login screen, enter "nullrecipes@gmail.com" and go to gmail.com to log into nullrecipes@gmail.com
		// to get the password recovery link + token.
	public static void main(String[] args) {
		SpringApplication.run(RecipeProjectApplication.class, args);
	}

}
