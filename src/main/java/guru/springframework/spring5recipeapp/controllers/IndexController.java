package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.domain.*;
import guru.springframework.spring5recipeapp.repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import guru.springframework.spring5recipeapp.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeService recipeService;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }

    /*@RequestMapping({"","/","/index","/index.html"})
    public String getIndexPage(){

        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByUom ("Teaspoon");

        System.out.println("Cat Id is : " + categoryOptional.get().getId());
        System.out.println("Uom Id is : " + uomOptional.get().getId());

        return "index";
    }*/

    @RequestMapping({"/allRecipes","","/","/index","/index.html"    })
    public String getRecipes(Model model){
        model.addAttribute("recipesList", this.recipeService.getAllRecipes());
        return "recipes";
    }


}
