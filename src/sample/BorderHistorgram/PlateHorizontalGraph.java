
package sample.BorderHistorgram;

import java.util.ArrayList;
import java.util.List;

public class PlateHorizontalGraph extends Graph {

    private static final int horizontalDetectionType = 1;

    public float derivation(int index1, int index2) {
        return yValues.get(index1) - yValues.get(index2);
    }

    public List<Peak> findPeak() {
        if (PlateHorizontalGraph.horizontalDetectionType == 1) {
            return findPeakEdgedetection();
        }
        return findPeakDerivative();
    }

    public List<Peak> findPeakDerivative() {
        int a = 2;
        int b = yValues.size() - 1 - 2;
        float maxVal = getMaxValue();
        while ((-derivation(a, a + 4) < (maxVal * 0.2)) && (a < (yValues.size() - 2 - 2 - 4))) {
            a++;
        }
        while ((derivation(b - 4, b) < (maxVal * 0.2)) && (b > (a + 2))) {
            b--;
        }
        List<Peak> outPeaks = new ArrayList<>();
        outPeaks.add(new Peak(a, b));
        super.peaks = outPeaks;
        return outPeaks;
    }

    public List<Peak> findPeakEdgedetection() {

        float average = getAverageValue();
        int c = 0;
        int a = 0;
        int b = yValues.size() - 1;
        while (yValues.get(a) < average) {
            a++;
        }

        while ((yValues.get(b) < average) || checkCondition(a, b)) {
            boolean bb = yValues.get(b) < average;
            b--;
        }
        List<Peak> outPeaks = new ArrayList<>();
//        a = Math.max(a - 5, 0);
//        b = Math.min(b + 5, yValues.size());
//        if(checkCondition(a, b))
//            {  outPeaks.add(new Peak(a,b)); }
        outPeaks.add(new Peak(a, b));
        super.peaks = outPeaks;
        return outPeaks;
    }

    private boolean checkCondition(int a, int b) {
        boolean result = false;
        for (int i = b; i > a; i--) {
            if (yValues.get(i) < (getAverageValue() / 100)) {
                result = true;
            }
        }
        return result;
    }
}
