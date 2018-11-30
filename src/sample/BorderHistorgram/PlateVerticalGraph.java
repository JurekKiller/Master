
package sample.BorderHistorgram;

import java.util.ArrayList;
import java.util.List;

public class PlateVerticalGraph extends Graph {

    private static final double peakFootConstant = 0.42;

    public List<Peak> findPeak(int count) {
        // lower the peak
        for (int i = 0; i < yValues.size(); i++) {
            yValues.set(i, yValues.get(i) - getMinValue());
        }
        List<Peak> outPeaks = new ArrayList<>();
        for (int c = 0; c < count; c++) {
            float maxValue = 0.0f;
            int maxIndex = 0;
            for (int i = 0; i < yValues.size(); i++) { // left to right
                if (allowedInterval(outPeaks, i)) {
                    if (yValues.get(i) >= maxValue) {
                        maxValue = yValues.get(i);
                        maxIndex = i;
                    }
                }
            }
            // we found the biggest peak
            if (yValues.get(maxIndex) < (0.05 * super.getMaxValue())) {
                break; // 0.4
            }
            int leftIndex = indexOfLeftPeakRel(maxIndex, PlateVerticalGraph.peakFootConstant);
            int rightIndex = indexOfRightPeakRel(maxIndex, PlateVerticalGraph.peakFootConstant);
            outPeaks.add(new Peak(Math.max(0, leftIndex), maxIndex, Math.min(yValues.size() - 1, rightIndex)));
        }
        outPeaks.sort(new PeakComparator(yValues));
        super.peaks = outPeaks;
        return outPeaks;
    }
}
