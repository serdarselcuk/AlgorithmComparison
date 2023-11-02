package com.utils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.List;
import java.util.Locale;

public class ComparisonSortPlot {

    private String alg1;
    private String alg2;


    public ComparisonSortPlot(String alg1, String alg2) {
        this.alg1 = alg1;
        this.alg2 = alg2;
    }

    public void graphicalView(List<Double> results1, List<Double> results2, List<String> xLabels) {

        // Create the dataset.
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < results1.size(); i++) {
            dataset.addValue(results1.get(i), alg1.toUpperCase(Locale.ROOT), xLabels.get(i));
            dataset.addValue(results2.get(i), alg2.toUpperCase(Locale.ROOT), xLabels.get(i));
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Line Chart",
                "Category",
                "Time",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        // Get the plot object.
        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        // Set the range axis to display integers.
        plot.getRangeAxis().setStandardTickUnits(NumberAxis.createStandardTickUnits());

        // Create a chart frame.
        ChartFrame frame = new ChartFrame("Line Chart Example", chart);

        // Make the chart frame visible.
        frame.setVisible(true);
    }
}