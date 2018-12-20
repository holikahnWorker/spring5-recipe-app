package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;


public class RecipeServiceImplTest {

    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, null, null);
    }

    @Test
    public void getAllRecipes() {
        Set<Recipe> recipes = new HashSet<>(this.recipeService.getAllRecipes());

        assertEquals(recipes.size(), 0L);


    }
}