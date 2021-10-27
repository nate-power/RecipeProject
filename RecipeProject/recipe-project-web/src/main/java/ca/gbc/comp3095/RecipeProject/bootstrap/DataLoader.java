package ca.gbc.comp3095.RecipeProject.bootstrap;

import ca.gbc.comp3095.RecipeProject.enumerations.RecipeCategories;
import ca.gbc.comp3095.RecipeProject.model.Recipe;
import ca.gbc.comp3095.RecipeProject.model.RecipeDate;
import ca.gbc.comp3095.RecipeProject.model.User;
import ca.gbc.comp3095.RecipeProject.services.RecipeDateServiceImpl;
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
    private final RecipeDateServiceImpl recipeDateService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public DataLoader(RecipeServiceImpl recipeService, UserServiceImpl userService, RecipeDateServiceImpl recipeDateService) {
        this.recipeService = recipeService;
        this.userService = userService;
        this.recipeDateService = recipeDateService;
    }

    @Override
    public void run(String... args) throws Exception {

        //Test user Accounts
        User user1 = new User("bob-ruthers", "test", "bob_ruthers@test.com", "Bob", "Ruthers");
        userService.save(user1);

        User user2 = new User("mollyringwald123", "test", "molly_ringwald@test.com", "Molly", "Ringwald");
        userService.save(user2);

        User user3 = new User("ana-is-cool", "test", "ana_de_armas@test.com", "Ana de", "Armas");
        userService.save(user3);

        //Recipes
        Recipe recipe1 = new Recipe("Spaghetti", user2, "cook noodles and cover in sauce cook noodles and cover in sauce " +
                "cook noodles and cover in saucecook noodles and cover in saucecook noodles and cover in sauce cook noodles and cover in sauce" +
                "cook noodles and cover in sauce cook noodles and cover in sauce" +
                "cook noodles and cover in sauce cook noodles and cover in sauce" +
                "cook noodles and cover in sauce cook noodles and cover in sauce" +
                "cook noodles and cover in sauce cook noodles and cover in sauce" +
                "cook noodles and cover in sauce cook noodles and cover in sauce" +
                "cook noodles and cover in sauce cook noodles and cover in sauce" +
                "cook noodles and cover in sauce cook noodles and cover in sauce" +
                "cook noodles and cover in sauce cook noodles and cover in sauce", LocalDate.now().minusDays(64), RecipeCategories.BREAKFAST,
                1, 2, 3, "1 cup sauce\n2 cups cheese",
                "Make the dough\nPut toppings on\nBake until it's ready");
        Recipe recipe2 = new Recipe("Pizza", user1, "sauce then cheese then bake", LocalDate.now().minusDays(43), RecipeCategories.LUNCH, 3, 2, 5, "sauce\ncheese", "sauce me up baby");
        Recipe recipe3 = new Recipe("Ice Cream", user3, "make it very cold", LocalDate.now().minusDays(28),RecipeCategories.DINNER, 4, 2, 4, "ice\ncream", "ice the cream plz");
        recipeService.save(recipe1);
        recipeService.save(recipe2);
        recipeService.save(recipe3);

        //Recipe Dates
        RecipeDate recipeDate1 = new RecipeDate(LocalDate.now().plusDays(3), recipe1, user3);
        recipeDateService.save(recipeDate1);
        RecipeDate recipeDate2 = new RecipeDate(LocalDate.now(), recipe2, user3);
        recipeDateService.save(recipeDate2);
        RecipeDate recipeDate3 = new RecipeDate(LocalDate.now().plusDays(10), recipe3, user3);
        recipeDateService.save(recipeDate3);

    }
}