package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

   private final UnitOfMeasureRepository uomRepository;
   private final UnitOfMeasureToUnitOfMeasureCommand uomToCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository uomRepository, UnitOfMeasureToUnitOfMeasureCommand uomToCommand) {
        this.uomRepository = uomRepository;
        this.uomToCommand = uomToCommand;
    }

    /********************HAS TO BE TESTED BEFORE ANYTHING*********************/
    @Override
    public List<UnitOfMeasureCommand> getUomList() {
        List<UnitOfMeasure> list = (List) this.uomRepository.findAll();
        List<UnitOfMeasureCommand> listCommand= new ArrayList<>();
        list.stream().map(element -> this.uomToCommand.convert(element)).forEach(element -> listCommand.add(element));
        return listCommand;
    }
}
