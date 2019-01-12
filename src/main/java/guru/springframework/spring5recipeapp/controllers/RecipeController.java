package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.exceptions.InvalidRecipeIdException;
import guru.springframework.spring5recipeapp.exceptions.NotFoundException;
import guru.springframework.spring5recipeapp.repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import guru.springframework.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class RecipeController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeService recipeService;

    public RecipeController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }

    @GetMapping({"/recipe/show/{id}"})
    public String getRecipeById(Model model, @PathVariable String id) throws Exception{
        try{
            model.addAttribute("recipe", this.recipeService.getById(new Long(id)));
        }
        catch (NumberFormatException nfe){
            throw new InvalidRecipeIdException("Invalid recipe Id : " + id);
        }

        return "recipeView";
    }

    @GetMapping({"/recipe/new"})
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipeForm";
    }

    @GetMapping({"/recipe/update/{id}"})
    public String updateRecipe(Model model, @PathVariable String id) throws Exception{
        RecipeCommand command = new RecipeCommand();
        command = this.recipeService.getRecipeCommandbyRecipeId(new Long(id));
        model.addAttribute("recipe", command);
        return "recipeForm";
    }

    @GetMapping({"/recipe/delete/{id}"})
    public String deleteRecipe(@PathVariable String id){
        this.recipeService.deleteByID(new Long(id));
        log.debug("Deleted Recipe : "+id);
        return "redirect:/allRecipes";
    }

    @PostMapping({"recipex"})
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand savedRecipeCommand = this.recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/show/" + savedRecipeCommand.getId();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException(Exception exception){
        log.error("Handling not found Exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404Error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRecipeIdException.class)
    public ModelAndView handleInvalidRecipeIdException(Exception exception){
        log.error("handling Invalid RecipeId exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404Error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
