package guru.springframework.spring5recipeapp.bootstrap;

import guru.springframework.spring5recipeapp.domain.*;
import guru.springframework.spring5recipeapp.repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.apache.catalina.startup.Catalina;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class RecipeBootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeRepository recipeRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        UnitOfMeasure uomTeaspoon = unitOfMeasureRepository.findByUom("Teaspoon").get();
        UnitOfMeasure uomTablepoon = unitOfMeasureRepository.findByUom("Tablepoon").get();
        UnitOfMeasure uomCup = unitOfMeasureRepository.findByUom("Cup").get();
        UnitOfMeasure uomPinch = unitOfMeasureRepository.findByUom("Pinch").get();
        UnitOfMeasure uomOunce = unitOfMeasureRepository.findByUom("Ounce").get();
        UnitOfMeasure uomEach = unitOfMeasureRepository.findByUom("Each").get();
        UnitOfMeasure uomDash = unitOfMeasureRepository.findByUom("Dash").get();
        UnitOfMeasure uomPint = unitOfMeasureRepository.findByUom("Pint").get();

        Category americanCategory = categoryRepository.findByDescription("American").get();
        Category mexicanCategory = categoryRepository.findByDescription("Mexican").get();
        Category italianCategory = categoryRepository.findByDescription("Italian").get();

        Recipe recipeOne = new Recipe();
        recipeOne.setCookTime(9);
        recipeOne.setPrepTime(10);
        recipeOne.setDifficulty(Difficulty.HARD);
        recipeOne.setDescription("Perfect Guacamole");
        recipeOne.setUrl("Bnine.com");
        recipeOne.setServing(1);
        recipeOne.setDirections("\n" +
                "    1-Cook manicotti in boiling water until done. Drain, and rinse with cold water.\n" +
                "    2-Preheat oven to 350 degrees F (175 degrees C).\n" +
                "    3-In a large bowl, combine ricotta, mozzarella, and 1/2 cup Parmesan, eggs, parsley, and salt and pepper. Mix well.\n" +
                "    4-Pour 1/2 cup sauce into an 11x17 inch baking dish. Fill each manicotti shell with 3 tablespoons cheese mixture, and arrange over sauce . Pour remaining sauce over top, and sprinkle with remaining Parmesan cheese.\n" +
                "    5-Bake 45 minutes, or until bubbly.\n");
        recipeOne.getIngredients().add(new Ingredient("Oil", new BigDecimal(1), uomCup, recipeOne));
        recipeOne.getIngredients().add(new Ingredient("Pepper", new BigDecimal(3), uomTeaspoon, recipeOne));
        recipeOne.getIngredients().add(new Ingredient("Macaroni", new BigDecimal(2), uomCup, recipeOne));
        Notes notesOne = new Notes();
        notesOne.setRecipe(recipeOne);
        notesOne.setRecipeNotes("You have to eat this dish");
        recipeOne.setNotes(notesOne);

        Recipe recipeTwo = new Recipe();
        recipeTwo.setCookTime(9);
        recipeTwo.setPrepTime(10);
        recipeTwo.setDifficulty(Difficulty.MODERATE);
        recipeTwo.setDescription("Spicy Grilled Chicken");
        recipeTwo.setUrl("Tasty.com");
        recipeTwo.setServing(2);
        recipeTwo.setDirections("\n" +
                "    1-Cook manicotti in boiling water until done. Drain, and rinse with cold water.\n" +
                "    2-Preheat oven to 350 degrees F (175 degrees C).\n" +
                "    3-In a large bowl, combine ricotta, mozzarella, and 1/2 cup Parmesan, eggs, parsley, and salt and pepper. Mix well.\n" +
                "    4-Pour 1/2 cup sauce into an 11x17 inch baking dish. Fill each manicotti shell with 3 tablespoons cheese mixture, and arrange over sauce . Pour remaining sauce over top, and sprinkle with remaining Parmesan cheese.\n" +
                "    5-Bake 45 minutes, or until bubbly.\n");
        recipeTwo.getIngredients().add(new Ingredient("Bread", new BigDecimal(1), uomOunce, recipeTwo));
        recipeTwo.getIngredients().add(new Ingredient("Scotch", new BigDecimal(4), uomCup, recipeTwo));
        recipeTwo.getIngredients().add(new Ingredient("Chili", new BigDecimal(6), uomTablepoon, recipeTwo));
        Notes notesTwo = new Notes();
        notesTwo.setRecipe(recipeTwo);
        notesTwo.setRecipeNotes("You will be drunk");
        recipeTwo.setNotes(notesTwo);


        recipeOne.addCategory(americanCategory);
        recipeOne.addCategory(italianCategory);

        recipeTwo.addCategory(mexicanCategory);


        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipeOne);
        recipes.add(recipeTwo);
        recipeRepository.saveAll(recipes);
    }
}
