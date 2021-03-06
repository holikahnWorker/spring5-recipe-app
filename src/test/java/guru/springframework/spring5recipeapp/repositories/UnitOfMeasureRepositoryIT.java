package guru.springframework.spring5recipeapp.repositories;

import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5recipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
/*@SpringBootTest*/
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    /*
    @Autowired
    RecipeService recipeService;
    */

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByUom() {

        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByUom("Teaspoon");

        assertEquals("Teaspoon", uom.get().getUom());

    }

    @Test
    public void findByUomCup() {

        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByUom("Cup");

        assertEquals("Cup", uom.get().getUom());

    }
}