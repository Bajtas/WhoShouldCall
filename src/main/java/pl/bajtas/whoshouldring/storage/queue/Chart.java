package pl.bajtas.whoshouldring.storage.queue;

/**
 * Created by Bajtas on 19.03.2017.
 */
public class Chart {
    private String caption;
    private String subcaption;
    private String showPlotBorder = "1";
    private String piefillalpha = "60";
    private String pieborderthickness = "2";
    private String hoverfillcolor = "#C3D312";
    private String piebordercolor = "#FFFFFF";
    private String numberprefix = "$";
    private String plottooltext = "$label";
    private String bgColor = "#18BC9C";
    private String bgAlpha = "100";
    private String theme = "fint";
    private String baseFontSize = "15";

    public String getShowPlotBorder() {
        return showPlotBorder;
    }

    public String getPiefillalpha() {
        return piefillalpha;
    }

    public String getPieborderthickness() {
        return pieborderthickness;
    }

    public String getHoverfillcolor() {
        return hoverfillcolor;
    }

    public String getPiebordercolor() {
        return piebordercolor;
    }

    public String getNumberprefix() {
        return numberprefix;
    }

    public String getPlottooltext() {
        return plottooltext;
    }

    public String getBgColor() {
        return bgColor;
    }

    public String getBgAlpha() {
        return bgAlpha;
    }

    public String getTheme() {
        return theme;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getSubcaption() {
        return subcaption;
    }

    public void setSubcaption(String subcaption) {
        this.subcaption = subcaption;
    }

    public String getBaseFontSize() {
        return baseFontSize;
    }

    public void setBaseFontSize(String baseFontSize) {
        this.baseFontSize = baseFontSize;
    }
}
