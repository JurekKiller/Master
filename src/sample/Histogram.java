//package sample;
//
//public class Histogram {
//    private final double[] freq;   // freq[i] = # occurences of value i
//    private double max;            // max frequency of any value
//
//    // Create a new histogram.
//    public Histogram(int n) {
//        freq = new double[n];
//    }
//
//    // Add one occurrence of the value i.
//    public void addDataPoint(int i) {
//        freq[i]++;
//        if (freq[i] > max) max = freq[i];
//    }
//
//    // draw (and scale) the histogram.
//    public void draw() {
//        StdDraw.setYscale(-1, max + 1);  // to leave a little border
//        StdStats.plotBars(freq);
//    }
//
//    // See Program 2.2.6.
//    public static void main(String[] args) {
//        int n = Integer.parseInt(args[0]);       // number of coins
//        int trials = Integer.parseInt(args[1]);  // number of trials
//
//        // create the histogram
//        Histogram histogram = new Histogram(n+1);
//        for (int t = 0; t < trials; t++) {
//            histogram.addDataPoint(Bernoulli.binomial(n));
//        }
//
//        // display using standard draw
//        StdDraw.setCanvasSize(500, 100);
//        histogram.draw();
//    }
//}
