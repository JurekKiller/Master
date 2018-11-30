
package sample.BorderHistorgram;

import java.util.Comparator;
import java.util.List;

public class PeakComparator implements Comparator<Peak> {
    private final List<Float> yValues;

    public PeakComparator(List<Float> yValues) {
        this.yValues = yValues;
    }

    private float getPeakValue(Peak peak) {
        // heuristic: how high (wide on the graph) is the candidate character (prefer higher ones)
        // return peak.getDiff();

        // heuristic: height of the peak
        return yValues.get(peak.getCenter());

        // heuristic: how far from the center is the candidate
        // int peakCenter = (peak.getRight() + (peak.getLeft() )/2;
        // return Math.abs(peakCenter - yValues.size()/2);
    }

    @Override
    public int compare(Peak peak1, Peak peak2) {
        return Double.compare(getPeakValue(peak2), getPeakValue(peak1));
    }
}
