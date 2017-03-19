package pl.bajtas.whoshouldring.storage.queue;

/**
 * Created by Bajtas on 19.03.2017.
 */
public class Category {
    private String label;
    private String color;
    private String value;
    private String tooltext;
    private Category category;

    public Category(String label, String color, String tooltext) {
        this.label = label;
        this.color = color;
        this.value = "100";
        this.tooltext = tooltext;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getLastCategory() {
        Category lastCategory = null;
        if (category != null)
            lastCategory = category.getLastCategory();
        if (category == null)
            lastCategory = this;
        return lastCategory;
    }

    public void setLastCategory(Category category) {
        Category lastCategory = getLastCategory();

        lastCategory.setCategory(category);
    }
}
