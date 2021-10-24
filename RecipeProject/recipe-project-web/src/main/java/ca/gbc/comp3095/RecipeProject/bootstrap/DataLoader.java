package ca.gbc.comp3095.RecipeProject.bootstrap;

import ca.gbc.comp3095.RecipeProject.model.Recipe;
import ca.gbc.comp3095.RecipeProject.model.User;
import ca.gbc.comp3095.RecipeProject.services.RecipeServiceImpl;
import ca.gbc.comp3095.RecipeProject.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final RecipeServiceImpl recipeService;
    private final UserServiceImpl userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public DataLoader(RecipeServiceImpl recipeService, UserServiceImpl userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        Recipe recipe1 = new Recipe("Spaghetti", null, "cook noodles and cover in sauce", 1, 2, 3);
        Recipe recipe2 = new Recipe("Pizza", null, "sauce then cheese then bake", 3, 2, 5);
        Recipe recipe3 = new Recipe("Ice Cream", null, "make it very cold", 4, 2, 4);
        recipeService.save(recipe1);
        recipeService.save(recipe2);
        recipeService.save(recipe3);

        //Test user Account
        User user = new User("test", "test","test@test.com", "Test", "User");
        userService.save(user);

    }
}