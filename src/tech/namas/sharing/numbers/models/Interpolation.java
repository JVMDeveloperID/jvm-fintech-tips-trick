package tech.namas.sharing.numbers.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Interpolation {

    /**
     * Number of data points
     */
    private int dataSize;

    /**
     * The data points
     */
    private Point[] data;

    /**
     * Divided Difference table.
     * This is basically a cache function for faster search data.
     * We want to build the polynomial function with dynamic programming
     *   hence the cache / memoization here.
     *
     * Check the math at:
     *
     * https://en.wikipedia.org/wiki/Divided_differences
     */
    private float[][] dividedDifference;

    public Interpolation(int maxData) {
        this.data = new Point[maxData];
        this.dividedDifference = new float[data.length][data.length];
    }

    public void addPoint(Point point) throws Exception {
        if (this.dataSize >= data.length) {
            throw new Exception("Cannot add more data");
        }

        data[dataSize] = point;
        dividedDifference[dataSize][0] = point.getY();

        dataSize++;

        for (int order = 1; order < dataSize; ++order) {
            int bottom = dataSize - order - 1;
            float numerator = dividedDifference[bottom + 1][order - 1] -
                              dividedDifference[bottom][order - 1];
            float denominator = data[bottom + order].getX() -
                                data[bottom].getX();

            dividedDifference[bottom][order] = numerator / denominator;
        }
    }

    public float interpolate(float x) {
        if (dataSize < 2) {
            return Float.NaN;
        }

        float y = dividedDifference[0][0];
        float xFactor = 1;

        for (int order = 1; order < dataSize; ++order) {
            xFactor = xFactor * (x - data[order - 1].getX());
            y = y + (xFactor * dividedDifference[0][order]);
        }

        return y;
    }
}
