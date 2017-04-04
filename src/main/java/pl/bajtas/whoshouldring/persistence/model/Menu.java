package pl.bajtas.whoshouldring.persistence.model;

/**
 * Created by Bajtas on 04.04.2017.
 */
public class Menu {
    private String name;

    public Menu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
