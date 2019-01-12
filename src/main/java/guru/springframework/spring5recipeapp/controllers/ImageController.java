package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.services.ImageService;
import guru.springframework.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Slf4j
@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping({"/recipe/{recipeId}/image/upload"})
    public String uploadImage(@PathVariable String recipeId, Model model) throws Exception{
        model.addAttribute("recipe", this.recipeService.getRecipeCommandbyRecipeId(new Long(recipeId)));
        return "imageUploadForm";
    }

    @PostMapping({"/recipe/{recipeId}/image/save"})
    public String saveImage(@PathVariable String recipeId, @RequestParam("imageFile") MultipartFile file) throws Exception{

        this.imageService.saveImage(Long.valueOf(recipeId), file);

        return "redirect:/recipe/show/" + recipeId;
    }

    @GetMapping({"/recipe/{recipeId}/image/get"})
    public void getRecipeImageFromDB(@PathVariable String recipeId, HttpServletResponse response) throws Exception{

        RecipeCommand recipeCommand = this.recipeService.getRecipeCommandbyRecipeId(Long.valueOf(recipeId));

        if(recipeCommand.getImage() != null){
            byte[] byteArray = new byte[recipeCommand.getImage().length];

            int b = 0;

            for(Byte wrappedByte : recipeCommand.getImage()){
                byteArray[b++] = wrappedByte;
            }

            response.setContentType("image/png");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }


    }
}
