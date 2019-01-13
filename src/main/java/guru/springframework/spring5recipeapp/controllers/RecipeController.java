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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
            throw nfe;
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
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));
            return "recipeForm";
        }

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

}
