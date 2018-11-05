package sample;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.LinkedList;

public class Histogram2 {

    public static void border() {
        // Assume SourceImage is a Bitmap ARGB_8888
        Mat srcRef = new Mat();
        Mat hsvRef = new Mat();
        Mat srcSource = new Mat();
        Mat hsvSource = new Mat();

        /// Convert to HSV
        Imgproc.cvtColor(srcRef, hsvRef, Imgproc.COLOR_BGR2HSV);
        Imgproc.cvtColor(srcSource, hsvSource, Imgproc.COLOR_BGR2HSV);

        /// Using 50 bins for hue and 60 for saturation
        int hBins = 50;
        int sBins = 60;
        MatOfInt histSize = new MatOfInt(hBins, sBins);

        // hue varies from 0 to 179, saturation from 0 to 255
        MatOfFloat ranges = new MatOfFloat(0f, 180f, 0f, 256f);

        // we compute the histogram from the 0-th and 1-st channels
        MatOfInt channels = new MatOfInt(0, 1);


        Mat histRef = new Mat();
        Mat histSource = new Mat();

        ArrayList<Mat> histImages = new ArrayList<Mat>();
        histImages.add(hsvRef);
        Imgproc.calcHist(histImages,
                channels,
                new Mat(),
                histRef,
                histSize,
                ranges,
                false);
        Core.normalize(histRef,
                histRef,
                0,
                1,
                Core.NORM_MINMAX,
                -1,
                new Mat());

        histImages = new ArrayList<Mat>();
        histImages.add(hsvSource);
        Imgproc.calcHist(histImages,
                channels,
                new Mat(),
                histSource,
                histSize,
                ranges,
                false);
        Core.normalize(histSource,
                histSource,
                0,
                1,
                Core.NORM_MINMAX,
                -1,
                new Mat());

        double resp1 = Imgproc.compareHist(histRef, histSource, 0);
        double resp2 = Imgproc.compareHist(histRef, histSource, 1);
        double resp3 = Imgproc.compareHist(histRef, histSource, 2);
        double resp4 = Imgproc.compareHist(histRef, histSource, 3);
    }

    public static void hist2() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Calculate histogram
        Mat srcRef;
        srcRef = Imgcodecs.imread("SWT/swt19042.jpg");

        java.util.List<Mat> matList = new LinkedList<Mat>();
        matList.add(srcRef);
        Mat histogram = new Mat();
        MatOfFloat ranges = new MatOfFloat(0, 256);
        MatOfInt histSize = new MatOfInt(255);
        Imgproc.calcHist(
                matList,
                new MatOfInt(0),
                new Mat(),
                histogram,
                histSize,
                ranges);

// Create space for histogram image
        Mat histImage = Mat.zeros(100, (int) histSize.get(0, 0)[0], CvType.CV_8UC1);
// Normalize histogram
        Core.normalize(histogram, histogram, 1, histImage.rows(), Core.NORM_MINMAX, -1, new Mat());
// Draw lines for histogram points
        for (int i = 0; i < (int) histSize.get(0, 0)[0]; i++) {
            Imgproc.line(
                    histImage,
                    new org.opencv.core.Point(i, histImage.rows()),
                    new org.opencv.core.Point(i, histImage.rows() - Math.round(histogram.get(i, 0)[0])),
                    new Scalar(255, 255, 255),
                    1, 8, 0);
        }
        Imgcodecs.imwrite("dupa.jpg", histImage);
    }

}
