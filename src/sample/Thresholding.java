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
      //  int radius = (int)(doubleRadius + 0.5);
      //  int kernelSize;

        //String file = "C:/Users/Pawel/Desktop/Projekt Inż/SerialDetector/a.png";

        Mat src = imread("audi.jpg", CV_LOAD_IMAGE_GRAYSCALE);
        Mat dst = new Mat();

        Imgproc.threshold(src, dst, 0, 255, Imgproc.THRESH_OTSU | Imgproc.THRESH_BINARY);
        //Imgproc.GaussianBlur(src,dst, new Size(kernelSize, kernelSize), radius);
      // Imgproc.imwrite("Adaptivemean_thresh_binary.jpg", dst);
       //  Imgproc.adaptiveThreshold(src, dst, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY_INV, 19, -15);
//
        Imgcodecs.imwrite("Adaptivemean_thresh_binary.jpg", dst);

    }
}
