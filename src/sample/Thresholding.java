package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.opencv.imgcodecs.Imgcodecs.imread;

public class Thresholding {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        //String file = "C:/Users/Pawel/Desktop/Projekt Inż/SerialDetector/a.png";

        Mat src = imread("audi.jpg", CV_LOAD_IMAGE_GRAYSCALE);
        Mat dst = new Mat();

        Imgproc.adaptiveThreshold(src, dst, 125, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 11, 12);
        Imgcodecs.imwrite("Adaptivemean_thresh_binary.jpg", dst);

    }
}
