package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;

import java.util.List;
import java.util.Set;

public interface RecipeService {

    List<Recipe> getAllRecipes();

    Recipe getById(Long id) throws Exception;

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    RecipeCommand getRecipeCommandbyRecipeId(Long id) throws Exception;

    void deleteByID(Long id);

    Set<IngredientCommand> getIngredientsByRecipeId(Long id);
}
