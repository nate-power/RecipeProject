package ca.gbc.comp3095.RecipeProject.bootstrap;

import ca.gbc.comp3095.RecipeProject.enumerations.RecipeCategories;
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
        Recipe recipe1 = new Recipe("Spaghetti", 2L, "cook noodles and cover in sauce cook noodles and cover in sauce " +
                "cook noodles and cover in saucecook noodles and cover in sauce", LocalDate.now().minusDays(64), RecipeCategories.BREAKFAST,
                1, 2, 3, "1 cup sauce^2 cups cheese",
                "Make the dough^Put toppings on^Bake until it's ready");
        Recipe recipe2 = new Recipe("Pizza", 1L, "sauce then cheese then bake", LocalDate.now().minusDays(43), RecipeCategories.LUNCH, 3, 2, 5, "", "");
        Recipe recipe3 = new Recipe("Ice Cream", 3L, "make it very cold", LocalDate.now().minusDays(28),RecipeCategories.DINNER, 4, 2, 4, "", "");
        recipeService.save(recipe1);
        recipeService.save(recipe2);
        recipeService.save(recipe3);

        //Test user Account
        User user1 = new User("bob-ruthers", "test","bob_ruthers@test.com", "Bob", "Ruthers");
        userService.save(user1);

        User user2 = new User("mollyringwald123", "test","molly_ringwald@test.com", "Molly", "Ringwald");
        userService.save(user2);

        User user3 = new User("ana-is-cool", "test","ana_de_armas@test.com", "Ana de", "Armas");
        userService.save(user3);



    }
}