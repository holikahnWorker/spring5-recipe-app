package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
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

}
