package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Thresholding {

    public static Mat ConvertThresholding() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = BottomHat.ConversionBlackHat();
        LicenceClassifier licenceClassifier = new LicenceClassifier();
        Mat srcImage = licenceClassifier.rectangleDetection();
        Mat dstImage = new Mat();

        Imgproc.threshold(srcImage, dstImage, 0, 255, Imgproc.THRESH_OTSU | Imgproc.THRESH_BINARY);
        //Imgproc.GaussianBlur(src,dst, new Size(kernelSize, kernelSize), radius);
        // Imgproc.imwrite("Adaptivemean_thresh_binary.jpg", dst);
        //   Imgproc.adaptiveThreshold(dst, dst2, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 19, -15);
//
        Imgcodecs.imwrite("ConvertThresholding.jpg", dstImage);
        return dstImage;

    }
}
