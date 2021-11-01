package ca.gbc.comp3095.RecipeProject.bootstrap;

import ca.gbc.comp3095.RecipeProject.enumerations.RecipeCategories;
import ca.gbc.comp3095.RecipeProject.model.Recipe;
import ca.gbc.comp3095.RecipeProject.model.RecipeDate;
import ca.gbc.comp3095.RecipeProject.model.User;
import ca.gbc.comp3095.RecipeProject.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final RecipeService recipeService;
    private final UserService userService;
    private final RecipeDateService recipeDateService;

    public DataLoader(RecipeService recipeService, UserService userService, RecipeDateService recipeDateService) {
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
        Recipe recipe1 = new Recipe(
                "Spaghetti with Meat Sauce",
                user3,
                "Easy weeknight-friendly spaghetti and meat sauce that's made completely from scratch. It's made using one-pot, is ultra flavorful, and can be made in under 45 minutes. The sauce can be made up to 3 days ahead of time and stored in the fridge.",
                LocalDate.now().minusDays(64),
                RecipeCategories.DINNER,
                10,
                35,
                6,
                "1 pound lean ground meat (or other ground meat)\n3 tbsp olive oil\n1 cup chopped onion\n3 minced garlic cloves\n2 tbsp tomato paste\n1/2 tsp dried oregano\n1 pinch crushed red pepper flakes\n1 cup water\n28 oz canned crushed tomatoes\n1 handful of fresh basil leaves\n12 oz dried spaghetti\n1/2 cup shredded parmesan cheese",
                "Heat the oil in a large pot over medium-high heat (we use a Dutch oven). Add the meat and cook until browned, about 8 minutes. As the meat cooks, use a wooden spoon to break it up into smaller crumbles.\nAdd the onions and cook, stirring every once and a while, until softened, about 5 minutes.\nStir in the garlic, tomato paste, oregano, and red pepper flakes and cook, stirring continuously for about 1 minute.\nPour in the water and use a wooden spoon to scrape up any bits of meat or onion stuck to the bottom of the pot. Stir in the tomatoes, 3/4 teaspoon of salt, and a generous pinch of black pepper. Bring the sauce to a low simmer. Cook, uncovered, at a low simmer for 25 minutes.\nAbout 15 minutes before the sauce finishes cooking, bring a large pot of salted water to the boil, and then cook pasta according to package directions, but check for doneness a minute or two before the suggested cooking time.\nTake the sauce off of the heat, and then stir in the basil. Toss in the cooked pasta, and then leave for a minute so that the pasta absorbs some of the sauce. Toss again, and then serve with parmesan sprinkled on top.",
                java.util.Optional.of(Files.readString(Path.of("RecipeProject/recipe-project-web/src/main/resources/" +
                        "static/img/data_loader_imgs/spaghetti-image.txt"), StandardCharsets.US_ASCII)));

        Recipe recipe2 = new Recipe(
                "Homemade Margherita Pizza",
                user3,
                "Making homemade pizza dough can sound like a lot of work, but it’s so worth the bragging rights. The dough itself requires few ingredients and just a little bit of rising and rest time. After you try this recipe, you may never order delivery again!",
                LocalDate.now().minusDays(43),
                RecipeCategories.VEGETARIAN,
                120,
                15,
                16,
                "1.5 cups warm water\n1 tsp sugar\n2 tsp active dry yeast\n7 cups all-purpose flour, plus more for dusting\n6 tbsp extra virgin olive oil, plus more for greasing\n1.5 tbsp kosher salt\n.25 cup semolina flour\n28 oz canned whole tomatoes\nfresh mozzarella\nfresh basil leaves",
                "'Bloom' the yeast by sprinkling the sugar and yeast in the warm water. Let sit for 10 minutes, until bubbles form on the surface.\nIn a large bowl, combine the flour and salt. Make a well in the middle and add the olive oil and bloomed yeast mixture. Using a spoon, mix until a shaggy dough begins to form.\nOnce the flour is mostly hydrated, turn the dough out onto a clean work surface and knead for 10-15 minutes. The dough should be soft, smooth, and bouncy. Form the dough into a taut round.\nGrease a clean, large bowl with olive oil and place the dough inside, turning to coat with the oil. Cover with plastic wrap. Let rise for at least an hour, or up to 24 hours.\nPunch down the dough and turn it out onto a lightly floured work surface. Knead for another minute or so, then cut into 4 equal portions and shape into rounds.\nLightly flour the dough, then cover with a kitchen towel and let rest for another 30 minutes to an hour while you prepare the sauce and any other ingredients.\nPreheat the oven as high as your oven will allow, between 450-500˚F (230-260˚C). Place a pizza stone, heavy baking sheet (turn upside down so the surface is flat), or cast iron skillet in the oven.\nMeanwhile, make the tomato sauce: Add the salt to the can of tomatoes and puree with an immersion blender, or transfer to a blender or food processor, and puree until smooth.\nOnce the dough has rested, take a portion and start by poking the surface with your fingertips, until bubbles form and do not deflate.\nThen, stretch and press the dough into a thin round. Make it thinner than you think it should be, as it will slightly shrink and puff up during baking.\nSprinkle semolina onto an upside down baking sheet and place the stretched crust onto it. Add the sauce, mozzarella and basil.\nSlide the pizza onto the preheated pizza stone or pan. Bake for 15 minutes, or until the crust and cheese are golden brown.\n",
                java.util.Optional.of(Files.readString(Path.of("RecipeProject/recipe-project-web/src/main/resources/" +
                        "static/img/data_loader_imgs/pizza-image.txt"), StandardCharsets.US_ASCII)));

        Recipe recipe3 = new Recipe(
                "Vanilla Ice Cream",
                user3,
                "For this recipe you will need an ice cream maker, but its worth it!",
                LocalDate.now().minusDays(28),
                RecipeCategories.DESSERTS,
                150,
                25,
                14,
                "1.5 cups whole milk\n1 cup granulated sugar\n1 pinch kosher salt\n3 cups heavy cream\n" +
                        "1.5 tbsp vanilla extract",
                "In a medium bowl, whisk  together milk, sugar, and salt until the sugar is dissolved.\n" +
                        "Stir in the heavy cream and vanilla.\nCover and refrigerate for at least 2 hours.\n" +
                        "Start the ice cream machine and slowly pour in the mixture. Let mix until thickened, around 25 minutes.\n" +
                        "Ice cream can be enjoyed immediately. Store in an airtight container in the freezer.",
                java.util.Optional.of(Files.readString(Path.of("RecipeProject/recipe-project-web/src/main/resources/" +
                        "static/img/data_loader_imgs/ice-cream-image.txt"), StandardCharsets.US_ASCII)));
        Recipe recipe4 = new Recipe(
                "Miso Glazed Salmon",
                user1,
                "Miso glazed foods are drawing more and more popularity around the globe. Glazing with miso adds an interesting and tasty flavor to many dishes. Turn your ordinary salmon dish into a sophisticated meal with this fab recipe.",
                LocalDate.now().minusDays(1),
                RecipeCategories.DINNER,
                15,
                12,
                2,
                "2 salmon fillets\n1 tsp salt\n3 tbsp miso\n3 tbsp sugar\n1 tbsp mirin\n1 tbsp sake\nSesame seeds and chopped green shallots to garnish",
                "Sprinkle salt over the salmon fillets evenly and cover with cling wrap to leave for 15 minutes.\nWipe excess moisture off the salmon with kitchen paper towel.\nMix the miso, sugar, mirin, and sake all together and pour them in a ziploc bag.\nPlace two salmon fillets in the bag. Zip the bag and coat the salmon with miso marinade from outside of the bag with your hand.\nLeave the marinade for 7-8 hours overnight in refrigerator.\nRemove the excess miso marinade with kitchen paper towel carefully.\nPlace the salmon on a baking sheet lined oven tray.\nGrill/Broil the salmon for about 6 minutes under 410°F(210°C) on the bottom row in the oven.\nTurn the salmon over once and cook another 6 minutes in the oven.\nTurn it back and glaze with miso marinade with a brush and put the tray back to the oven for one minute.\nServe the glazed salmon on top of the soba noodles or bed of rice\nGarnish with roasted sesame seeds and finely chopped green shallots.",
                java.util.Optional.of(Files.readString(Path.of("RecipeProject/recipe-project-web/src/main/resources/" +
                        "static/img/data_loader_imgs/miso-salmon-image.txt"), StandardCharsets.US_ASCII))
        );
        Recipe recipe5 = new Recipe(
                "Old Fashioned Pancakes",
                user2,
                "These are the pancakes that my grandmother made for me when I was a kid, and now they are the pancakes that I make for my grandchildren. They're simply too good to keep as a family secret, so enjoy!",
                LocalDate.now().minusDays(15),
                RecipeCategories.BREAKFAST,
                5,
                15,
                8,
                "1.5 cups all purpose flour\n3.5 tsp baking powder\n1 tsp salt\n1 tbsp white sugar\n1.25 cups milk\n1 egg\n3 tbsp melted butter",
                "In a large bowl, sift together the flour, baking powder, salt and sugar.\nMake a well in the center and pour in the milk, egg and melted butter; mix until smooth.\nHeat a lightly oiled griddle or frying pan over medium-high heat. Pour or scoop the batter onto the griddle, using approximately 1/4 cup for each pancake. Brown on both sides.\nThat's it! Serve with lots of syrup and love.",
                java.util.Optional.of(Files.readString(Path.of("RecipeProject/recipe-project-web/src/main/resources/" +
                        "static/img/data_loader_imgs/pancakes-image.txt"), StandardCharsets.US_ASCII))
        );
        Recipe recipe6 = new Recipe(
                "Mediterranean Pasta Salad",
                user1,
                "You'll love this easy pasta salad recipe! It's a great one to make ahead - if you're bringing it to a picnic, prep it up to a day in advance.",
                LocalDate.now().minusDays(10),
                RecipeCategories.SALADS,
                12,
                10,
                6,
                "3 cups uncooked fusilli pasta\n2 heaping cups halved cherry tomatoes\n1 1/2 cups cooked chickpeas, drained and rinsed\n2 cups arugula\n1 cup persian cucumbers, sliced into thin half moons\n1 cup crumbled feta\n1 cup torn basil leaves\n1/2 cup minced parsley\n1/2 cup chopped mint\n1/4 cup toasted pine nuts\n1/4 cup olive oil\n3 tbsp lemon juice\n1 tsp dijon mustard\n3 minced garlic cloves\n1 tsp herbes de Provence\n1/4 tsp red pepper flakes\n3/4 tsp sea salt",
                "Bring a large pot of salted water to a boil. Prepare the pasta according to the package directions, or until slightly past al dente.\nMeanwhile, make the dressing. In a small bowl, whisk together the olive oil, lemon juice, mustard, garlic, herbes de Provence, red pepper flakes, and salt.\nDrain the pasta, toss it with a little olive oil (so that it doesn’t stick together) and let it cool to room temp.\nTransfer to a large bowl with the tomatoes, chickpeas, arugula, cucumbers, feta cheese, basil, parsley, mint, and pine nuts. Pour the dressing and toss to coat.\nSeason to taste with more lemon, salt, pepper, and/or a drizzle of olive oil, if desired, and serve.",
                java.util.Optional.of(Files.readString(Path.of("RecipeProject/recipe-project-web/src/main/resources/" +
                        "static/img/data_loader_imgs/pasta-salad-image.txt"), StandardCharsets.US_ASCII))
        );
        Recipe recipe7 = new Recipe(
                "Cornbread Muffins",
                user1,
                "Made with cornmeal and studded with fresh corn kernels, these not-too-sweet tender muffins are just as good alongside barbecue as they are slathered with butter at the breakfast table.",
                LocalDate.now().minusDays(40),
                RecipeCategories.BAKERY,
                20,
                20,
                12,
                "1 1/2 cups all-purpose flour\n1 1/4 cups cornmeal\n1/4 cup plus 2 tbsp sugar\n2 1/2 tsp baking powder\n3/4 tsp baking soda\n2 1/4 tsp kosher salt\n1 1/2 tsp freshly ground black pepper\n2 cups fresh corn kernels\n2 large eggs\n1 large egg yolk\n3/4 cup sour cream\n2/3 cup milk\n1/2 cup melted unsalted butter, cooled\nsea salt",
                "Preheat oven to 400°. Generously coat a standard 12-cup muffin pan with nonstick spray\nWhisk flour, cornmeal, sugar, baking powder, baking soda, kosher salt, and pepper in a large bowl. Stir in 1½ cups corn.\nLightly whisk eggs and egg yolk in a medium bowl, then whisk in sour cream, milk, and butter.\nCreate a well in the center of dry ingredients. Pour egg mixture into well and stir with a wooden spoon until batter is just combined.\nDivide batter among prepared muffin cups. Top with remaining ½ cup corn, then sprinkle with sea salt.\nBake muffins, rotating pan halfway through, until tops are golden brown and a tester inserted into the center comes out clean, 18–20 minutes. Let cool slightly in pan. Transfer muffins to a wire rack and eat while warm or let cool completely.",
                java.util.Optional.of(Files.readString(Path.of("RecipeProject/recipe-project-web/src/main/resources/" +
                        "static/img/data_loader_imgs/corn-muffin-image.txt"), StandardCharsets.US_ASCII))
        );
        Recipe recipe8 = new Recipe(
                "Green Curry Lentil Soup",
                user2,
                "This soup packs in all the ingredients that would make your doctor happy (Lentils! Sweet potatoes! Leafy greens!) but is bolstered by fiery Thai green curry paste to keep things interesting. Store-bought Thai curry pastes can vary widely in flavor and intensity from brand to brand (we tested this recipe with Maesri), so there are also lots of aromatics like onion, ginger, and garlic to ensure there’s plenty of flavor to balance the earthy lentils.",
                LocalDate.now(),
                RecipeCategories.SOUPS,
                20,
                30,
                4,
                "2 tbsp virgin coconut oil\n1 small onion chopped\n2 finely chopped garlic cloves\n1 inch piece of ginger, finely chopped\n1/2 tsp kosher salt\n1/4 cup Thai green curry paste\n1 medium sweet potato, peeled and cut into 1/2 inch cubes\n3/4 cup brown or green lentils\n4 cups chicken or vegetable broth\n13.5 oz unsweetened coconut milk\n4 cups baby spinach leaves\n1/2 tsp fish sauce\nSmall handful cilantro leaves and lime wedges for serving",
                "Heat oil in a large saucepan over medium. Add onion, garlic, and ginger; season with salt. Cook, stirring often, until onion is translucent and starts to soften, about 3 minutes. Add curry paste and cook, stirring and scraping bottom of pan constantly, until paste is fragrant and slightly darkened and mixture starts to stick to pan, about 3 minutes.\nStir sweet potato and lentils into onion mixture, then add broth and 1 tsp. Diamond Crystal or ½ tsp. Morton kosher salt and bring to a boil. Reduce heat to a simmer and cook soup, stirring occasionally, until sweet potatoes are cooked through and lentils are tender but not mushy, 20–25 minutes.\nAdd coconut milk to soup; return to a simmer. Add spinach and fish sauce and cook just until spinach is wilted, about 30 seconds. Taste soup and season with more salt and/or fish sauce if needed.\nLadle soup into bowls and top with cilantro. Serve with lime wedges.",
                java.util.Optional.of(Files.readString(Path.of("RecipeProject/recipe-project-web/src/main/resources/" +
                        "static/img/data_loader_imgs/lentil-soup-image.txt"), StandardCharsets.US_ASCII))
        );
        Recipe recipe9 = new Recipe(
                "Yuzu Kosho Deviled Eggs",
                user3,
                "This Japanese spin on a classic appetizer will impress all of your party guests and have them asking you where you got the recipe. Go ahead and take the credit - I won't tell if you don't.",
                LocalDate.now().minusDays(3),
                RecipeCategories.APPETIZERS,
                30,
                10,
                18,
                "2 tbsp Champagne vinegar or white wine vinegar\n1 tsp sugar\n2 tsp finely chopped shallot\n3 small radishes, very thinly sliced\n18 large eggs\nKosher salt\n1 tbsp hot mustard powder\n1 large egg yolk\n2 tbsp fresh lime juice\n1 tbsp or more green yuzu kosho\n1 cup grapeseed oil or vegetable oil\n2 tsp finely chopped cornichon\n2 tbsp finely chopped tender herbs of your choosing, such as parsley, chives, shiso, or chervil",
                "Mix vinegar and sugar in a small bowl. Add shallot and let sit until softened, 5–10 minutes; drain.\nMeanwhile, place radishes in another small bowl and pour in very cold water to cover (radishes should curl).\nPlace eggs in a medium pot and pour in water to cover by 3\". Add a large pinch of salt and bring to a boil over high heat. Immediately cover, remove from heat, and let sit 8 minutes.\nRun 1 egg under cold water; crack and peel. Yolk should be bright yellow and texture more chalky than fudgy. Let remaining eggs sit longer in water if needed, checking in 1-minute increments until done. Drain and rinse eggs under cold running water. Transfer to a large bowl of ice water and let sit, stirring every minute or so, until mostly cool, about 5 minutes. Carefully peel eggs, then trim a very small amount of white from top and bottom of each egg so they can stand on an end. Slice in half crosswise. Separate yolks from whites, transferring yolks to a small bowl. Place whites on a large plate.\nMix mustard powder and 2 tsp. cold water in another small bowl to make a paste. Scrape into a food processor; add raw egg yolk, lime juice, and yuzu kosho; pulse until smooth. Let sit 1 minute.\nWith motor running, pour in ½ cup oil, starting with a few drops at a time, then increasing to a very thin stream. Process until incorporated, then add 1 tsp. cold water. Stop processor and scrape down sides, then, with motor running, add remaining ½ cup oil in a very slow stream. Process until incorporated. Season mayonnaise with salt or up to 1 Tbsp. more yuzu kosho. Add cooked yolks to mayonnaise and pulse, scraping down sides as needed, until a very thick paste forms. Add shallot, cornichon, and 2 Tbsp. herbs to egg mixture and pulse to combine. Taste and season with more salt if needed.\nTransfer egg mixture to a pastry bag fitted with pastry tip of choice, or a resealable plastic bag (snip one corner off to pipe), and chill.\nWhen you’re ready to serve, arrange egg whites cut side down on paper towels and let drain. Turn over and pipe in a generous amount of filling. Drain radishes, pat dry, and place a slice on top of each egg, then sprinkle with more finely chopped herbs.",
                java.util.Optional.of(Files.readString(Path.of("RecipeProject/recipe-project-web/src/main/resources/" +
                        "static/img/data_loader_imgs/deviled-eggs-image.txt"), StandardCharsets.US_ASCII))
        );
        recipeService.save(recipe1);
        recipeService.save(recipe2);
        recipeService.save(recipe3);
        recipeService.save(recipe4);
        recipeService.save(recipe5);
        recipeService.save(recipe6);
        recipeService.save(recipe7);
        recipeService.save(recipe8);
        recipeService.save(recipe9);

        //Recipe Dates
        RecipeDate recipeDate1 = new RecipeDate(LocalDate.now().plusDays(3), recipe1, user3);
        recipeDateService.save(recipeDate1);
        RecipeDate recipeDate2 = new RecipeDate(LocalDate.now(), recipe2, user3);
        recipeDateService.save(recipeDate2);
        RecipeDate recipeDate3 = new RecipeDate(LocalDate.now().plusDays(10), recipe3, user3);
        recipeDateService.save(recipeDate3);

    }
}