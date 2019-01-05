package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.services.IngredientService;
import guru.springframework.spring5recipeapp.services.RecipeService;
import guru.springframework.spring5recipeapp.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {

    private RecipeService recipeService;
    private IngredientService ingredientService;
    private UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping
    @RequestMapping({"/recipe/{id}/ingredients"})
    public String getRecipesIngredients(Model model, @PathVariable String id){
        model.addAttribute("ingredients", this.recipeService.getIngredientsByRecipeId(new Long(id)));
        model.addAttribute("recipeId" , id);
        return "ingredient/list";
    }

    @GetMapping
    @RequestMapping({"/recipe/{recipeId}/ingredient/{ingredientId}"})
    public String getIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
        model.addAttribute("ingredient", this.ingredientService.getIngredientByItsIdAndByIngredientId(new Long(recipeId), new Long(ingredientId)));
        return "ingredient/element";
    }

    @GetMapping
    @RequestMapping({"/recipe/{recipeId}/ingredient/{ingredientId}/update"})
    public String getIngredientAndUomForUpdate(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        model.addAttribute("ingredient", this.ingredientService.getIngredientByItsIdAndByIngredientId(new Long(recipeId), new Long(ingredientId)));
        model.addAttribute("allUom", this.unitOfMeasureService.getUomList());
        return "ingredient/form";
    }

    @GetMapping
    @RequestMapping({"/recipe/{recipeId}/ingredient/new"})
    public String newIngredient(@PathVariable String recipeId, Model model){
        IngredientCommand command = new IngredientCommand();
        command.setRecipeId(new Long(recipeId));
        command.setUom(new UnitOfMeasureCommand());
        model.addAttribute("ingredient", command);
        model.addAttribute("allUom", this.unitOfMeasureService.getUomList());
        return "ingredient/form";
    }

    @PostMapping
    @RequestMapping({"ingredientx"})
    public String saveIngredient(@ModelAttribute IngredientCommand ingredientCommand){
        IngredientCommand savedIngredient = this.ingredientService.saveIngredientCommand(ingredientCommand);
        return "redirect:/recipe/" + savedIngredient.getRecipeId() + "/ingredient/" + savedIngredient.getId();
    }

    @GetMapping
    @RequestMapping({"/ingredient/delete/{recipeId}/{ingredientId}"})
    public String deleteIngredient(@PathVariable String recipeId,@PathVariable String ingredientId){
        this.ingredientService.deleteByid(new Long(ingredientId), new Long(recipeId));
        return "redirect:/recipe/{recipeId}/ingredients";
    }


}
