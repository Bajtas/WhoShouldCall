package pl.bajtas.whoshouldring.storage.queue;

/**
 * Created by Bajtas on 19.03.2017.
 */
public class ChartWrapper {
    private Chart chart;
    private Category[] category;

    public ChartWrapper() {
        chart = new Chart();
    }

    public Chart getChart() {
        return chart;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }

    public Category[] getCategory() {
        return category;
    }

    public void setCategory(Category[] category) {
        this.category = category;
    }
}
