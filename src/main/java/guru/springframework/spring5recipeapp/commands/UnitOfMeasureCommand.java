package guru.springframework.spring5recipeapp.commands;

public class UnitOfMeasureCommand {

    private Long id;
    private String uom;

    public UnitOfMeasureCommand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }
}
