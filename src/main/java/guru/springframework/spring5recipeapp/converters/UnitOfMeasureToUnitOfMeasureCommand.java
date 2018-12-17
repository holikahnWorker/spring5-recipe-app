package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand>{

    @Synchronized
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        if(source == null){
            return null;
        }
        UnitOfMeasureCommand uomc = new UnitOfMeasureCommand();
        uomc.setId(source.getId());
        uomc.setUom(source.getUom());
        return uomc;
    }
}