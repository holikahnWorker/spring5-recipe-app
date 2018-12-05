package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.domain.Recipe;

import java.util.List;

public interface RecipeService {

    List<Recipe> getAllRecipes();
}
