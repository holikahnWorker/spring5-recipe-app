package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.converters.IngredientCommandToIngredient;
import guru.springframework.spring5recipeapp.converters.IngredientToIngredientCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private RecipeRepository recipeRepository;
    private IngredientToIngredientCommand ingredientToIngredientCommand;
    private IngredientCommandToIngredient ingredientCommandToIngredient;
    private UnitOfMeasureRepository uomRepository;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, UnitOfMeasureRepository uomRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.uomRepository = uomRepository;
    }

    @Override
    public IngredientCommand getIngredientByItsIdAndByIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> optionalRecipe = this.recipeRepository.findById(recipeId);

        if(!optionalRecipe.isPresent()){
            log.error("No recipe with ID : " + recipeId);
        }

        Recipe recipe = optionalRecipe.get();

        Optional<IngredientCommand> ingredientCommand = recipe.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(ingredientId))
                                                                                .map(ingredient -> this.ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if(!ingredientCommand.isPresent()){
            log.error("No ingrediet with ID : " + ingredientId);
        }

        return ingredientCommand.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand){

        Optional<Recipe> recipeOptional = this.recipeRepository.findById(ingredientCommand.getRecipeId());

        if(!recipeOptional.isPresent()){
            log.error("Can't find Recipe Id : " + ingredientCommand.getRecipeId());
            return  new IngredientCommand();
        }
        else{
            Recipe recipe = recipeOptional.get();
            Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream().filter(ing -> ing.getId().equals(ingredientCommand.getId())).findFirst();
            if(ingredientOptional.isPresent()){
                Ingredient ingredient = ingredientOptional.get();
                ingredient.setDescription(ingredientCommand.getDescription());
                ingredient.setAmount(ingredientCommand.getAmount());
                ingredient.setUom(this.uomRepository.findById(ingredientCommand.getUom().getId()).get());
                this.recipeRepository.save(recipe);
            }
            else{
                Ingredient newIngredient = this.ingredientCommandToIngredient.convert(ingredientCommand);
                recipe.addIngredient(newIngredient);
                newIngredient.setRecipe(recipe);
                this.recipeRepository.save(recipe);
                Ingredient savedIngredient = recipe.getIngredients().stream().filter(ingredient -> ingredient.getDescription().equals(ingredientCommand.getDescription())).findFirst().get();
                return this.ingredientToIngredientCommand.convert(savedIngredient);
            }
        }

        return this.ingredientToIngredientCommand.convert(recipeOptional.get().getIngredients().stream().filter(ing -> ing.getId().equals(ingredientCommand.getId())).findFirst().get());


    }

    @Override
    public void deleteByid(Long ingredientId, Long recipeId) {

        log.debug("Ingredient to delete : Recipe " + recipeId + ", Ingredient " + ingredientId);

        Optional<Recipe> optionalRecipe = this.recipeRepository.findById(recipeId);

        if(optionalRecipe.isPresent()){
            Recipe recipe = optionalRecipe.get();
            Optional<Ingredient> optionalIngredient = recipe.getIngredients().stream().filter(ingre -> ingre.getId().equals(ingredientId)).findFirst();
            if(optionalIngredient.isPresent()){
                Ingredient ingredient = optionalIngredient.get();
                optionalIngredient.get().setRecipe(null);
                recipe.getIngredients().remove(ingredient);
                this.recipeRepository.save(recipe);
                log.debug("Ingredient deleted !");
            }

        }

    }

}
