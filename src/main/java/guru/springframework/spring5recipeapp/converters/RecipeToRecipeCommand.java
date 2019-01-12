package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final NotesToNotesCommand notesToCommand;
    private final IngredientToIngredientCommand ingredientToCommand;
    private final CategoryToCategorycommand categoryToCommand;

    public RecipeToRecipeCommand(NotesToNotesCommand notesToCommand, IngredientToIngredientCommand ingredientToCommand, CategoryToCategorycommand categoryToCommand) {
        this.notesToCommand = notesToCommand;
        this.ingredientToCommand = ingredientToCommand;
        this.categoryToCommand = categoryToCommand;
    }

    @Synchronized
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if(recipe==null){
            return null;
        }
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(recipe.getId());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setPrepTime(recipe.getPrepTime());
        recipeCommand.setDifficulty(recipe.getDifficulty());
        recipeCommand.setServing(recipe.getServing());
        recipeCommand.setDirections(recipe.getDirections());
        recipeCommand.setImage(recipe.getImage());
        recipeCommand.setUrl(recipe.getUrl());
        recipeCommand.setSource(recipe.getSource());
        recipeCommand.setNotes(this.notesToCommand.convert(recipe.getNotes()));

        if(recipe.getIngredients() != null && recipe.getIngredients().size() > 0){
            recipe.getIngredients().forEach(ingredient -> recipeCommand.getIngredients().add(this.ingredientToCommand.convert(ingredient)));
        }

        if(recipe.getCategories() != null && recipe.getCategories().size() > 0){
            recipe.getCategories().forEach(category -> recipeCommand.getCategories().add(this.categoryToCommand.convert(category)));
        }

        return recipeCommand;

    }
}
