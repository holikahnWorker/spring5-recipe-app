package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImage(Long recipeId, MultipartFile file) throws Exception {

        try{
            Recipe recipe = this.recipeRepository.findById(recipeId).get();
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int b = 0;

            for(Byte item : file.getBytes()){
                byteObjects[b++] = item;
            }

            recipe.setImage(byteObjects);

            this.recipeRepository.save(recipe);

            log.info("Image saved !!");

        }catch (Exception e){
            log.error("Error while persisting Image !");
            e.printStackTrace();
        }

    }
}
