package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.converters.RecipeCommandToRecipe;
import guru.springframework.spring5recipeapp.converters.RecipeToRecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        log.debug("You're in RecipeServiceImpl");
        return (List) this.recipeRepository.findAll();
    }


    @Override
    public Recipe getById(Long id) throws Exception{

        Optional<Recipe> recipe = this.recipeRepository.findById(id);

        if(!recipe.isPresent()){
            throw new RuntimeException("Recipe not found");
        }
        return recipe.get();
    }

    @Transactional
    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand){
        Recipe recipe = this.recipeCommandToRecipe.convert(recipeCommand);
        this.recipeRepository.save(recipe);
        log.debug("Saved Recipe : " + recipe.getId());
        return this.recipeToRecipeCommand.convert(recipe);
    };

    @Override
    public RecipeCommand getRecipeCommandbyRecipeId(Long id) throws Exception {
        Recipe recipe = getById(id);
        return this.recipeToRecipeCommand.convert(recipe);
    }

}
