package guru.springframework.spring5recipeapp.commands;

import java.util.HashSet;
import java.util.Set;

public class CategoryCommand {

    private Long id;
    private String description;
    private Set<RecipeCommand> recipes = new HashSet<>();

    public CategoryCommand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<RecipeCommand> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<RecipeCommand> recipes) {
        this.recipes = recipes;
    }
}
