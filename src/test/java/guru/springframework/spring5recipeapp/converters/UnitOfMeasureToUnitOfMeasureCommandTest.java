package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {


    UnitOfMeasureToUnitOfMeasureCommand command;


    private static final String DESCRIPTION = "DESCRIPTION";
    private static final Long ID = new Long(1L);

    @Before
    public void setUp() throws Exception {
        command = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void returnNull(){
        assertNull(command.convert(null));
    }

    @Test
    public void returnNotNull(){
        assertNotNull(command.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() {

        //GIVEN
        UnitOfMeasure source = new UnitOfMeasure();
        source.setId(ID);
        source.setUom(DESCRIPTION);

        //WHEN
        UnitOfMeasureCommand uomc = command.convert(source);

        //THEN
        assertNotNull(uomc);
        assertEquals(ID, uomc.getId());
        assertEquals(DESCRIPTION, uomc.getUom());
    }
}