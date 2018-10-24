package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Thresholding {

    public static Mat ConvertThresholding() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
      //  int radius = (int)(doubleRadius + 0.5);
        Mat src = BottomHat.ConversionBottonHatt();
      //  int kernelSize;

        //String file = "C:/Users/Pawel/Desktop/Projekt Inż/SerialDetector/a.png";

       //  imread("tyl.jpg", CV_LOAD_IMAGE_GRAYSCALE);
        Mat dst = new Mat();
        Mat dst2 = new Mat();
        Imgproc.threshold(src, dst, 150, 255, Imgproc.THRESH_OTSU | Imgproc.THRESH_BINARY);
        //Imgproc.GaussianBlur(src,dst, new Size(kernelSize, kernelSize), radius);
      // Imgproc.imwrite("Adaptivemean_thresh_binary.jpg", dst);
         Imgproc.adaptiveThreshold(dst, dst2, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 19, -15);
//
        Imgcodecs.imwrite("Otsu.jpg", dst);
        Imgcodecs.imwrite("Adaptivemean_thresh_binary.jpg", dst2);
        return dst;

    }
}
