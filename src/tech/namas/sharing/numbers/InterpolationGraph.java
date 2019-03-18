package tech.namas.sharing.numbers;

import com.xeiam.xchart.*;
import tech.namas.sharing.numbers.models.Interpolation;
import tech.namas.sharing.numbers.models.Point;

import java.util.ArrayList;
import java.util.List;

public class InterpolationGraph {

    private static final int MAX_DATA = 10;

    private static List<Chart> charts = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Interpolation f = new Interpolation(MAX_DATA);
        final float compared = 1.4f;
        final float[] values = new float[] {
            1.12f, 1.55f, 1.25f, 1.92f, 1.33f, 1.75f, 6.0f, 7.0f, 10.0f
        };

        addExpectedChart();

        f.addPoint(new Point(values[0], (float) Math.sin(values[0])));
        f.addPoint(new Point(values[1], (float) Math.sin(values[1])));
        addChart(2, values, f);
        printEstimate(2, f, compared);

        f.addPoint(new Point(values[2], (float) Math.sin(values[2])));
        addChart(3, values, f);
        printEstimate(3, f, compared);

        f.addPoint(new Point(values[3], (float) Math.sin(values[3])));
        addChart(4, values, f);
        printEstimate(4, f, compared);

        f.addPoint(new Point(values[4], (float) Math.sin(values[4])));
        addChart(5, values, f);
        printEstimate(5, f, compared);

        f.addPoint(new Point(values[5], (float) Math.sin(values[5])));
        f.addPoint(new Point(values[6], (float) Math.sin(values[6])));
        f.addPoint(new Point(values[7], (float) Math.sin(values[7])));
        f.addPoint(new Point(values[8], (float) Math.sin(values[8])));
        addChart(8, values, f);
        printEstimate(8, f, compared);

        new SwingWrapper(charts).displayChartMatrix();
    }

    private static void addExpectedChart() {
        Chart chart = new ChartBuilder()
                .xAxisTitle("X")
                .yAxisTitle("Y")
                .height(400)
                .width(600)
                .build();
        chart.getStyleManager().setXAxisMin(-2);
        chart.getStyleManager().setXAxisMax(12);
        chart.getStyleManager().setYAxisMin(-2);
        chart.getStyleManager().setYAxisMax(2);

        double[] result = new double[1000];
        for (int i = 0; i < 1000; i++) {
            float x = (float) i * 0.01f;
            float y = (float) Math.sin(x);
            result[i] = y;
        }

        Series series = chart.addSeries("Expected result", createXSeries(), result);
        series.setMarker(SeriesMarker.NONE);

        charts.add(chart);
    }

    private static void addChart(int current, float[] marked, Interpolation f) {
        Chart chart = new ChartBuilder()
                .xAxisTitle("X")
                .yAxisTitle("Y")
                .height(400)
                .width(600)
                .build();
        chart.getStyleManager().setXAxisMin(-2);
        chart.getStyleManager().setXAxisMax(12);
        chart.getStyleManager().setYAxisMin(-2);
        chart.getStyleManager().setYAxisMax(2);

        Series series = chart.addSeries(current + " data points", createXSeries(), createYSeries(f));
        series.setMarker(SeriesMarker.NONE);
        charts.add(chart);
    }

    private static double[] createXSeries() {
        double[] result = new double[1000];
        for (int i = 0; i < 1000; i++) {
            result[i] = (float) i * 0.01f;
        }

        return result;
    }

    private static double[] createYSeries(Interpolation f) {
        double[] result = new double[1000];
        for (int i = 0; i < 1000; i++) {
            float x = (float) i * 0.01f;
            float y = f.interpolate(x);
            result[i] = y;
        }

        return result;
    }

    private static void printEstimate(int current, Interpolation f, float compared) {
        float estimated = f.interpolate(compared);
        float javaResult = (float) Math.sin(compared);
        float errorPerct = Math.abs(100 * (estimated - javaResult) / javaResult);

        System.out.println(current + " data points:");
        System.out.println("Estimate sin(" + compared + ") = " + estimated);
        System.out.println("Math.sin(" + compared + ")     = " + javaResult);
        System.out.println("          % error = " + errorPerct + "\n");
    }
}
