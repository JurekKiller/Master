package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.Random;

public class Thresholding {

    public static Mat adaptiveThresholding(Mat srcImage){
        Random rand = new Random();

        int n = rand.nextInt(50000) + 1;
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat dstImage = new Mat();
        Imgproc.adaptiveThreshold(srcImage, dstImage, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 5, 5);
        Imgcodecs.imwrite("adaptiveThresholding/"+n+".jpg",dstImage);
        return dstImage;
    }
}
