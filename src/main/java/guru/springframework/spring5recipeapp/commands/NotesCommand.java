package guru.springframework.spring5recipeapp.commands;

public class NotesCommand {

    private Long id;
    private RecipeCommand recipe;
    private String RecipeNotes;

    public NotesCommand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RecipeCommand getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeCommand recipe) {
        this.recipe = recipe;
    }

    public String getRecipeNotes() {
        return RecipeNotes;
    }

    public void setRecipeNotes(String recipeNotes) {
        RecipeNotes = recipeNotes;
    }
}
