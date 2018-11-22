package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.util.Random;

public class ImageBlurring {

    public static Mat medianBlurring(Mat srcImage) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        int SAMPLING_WINDOW_5x5 = 3;
        int SAMPLING_WINDOW_3x3 = 3;
        Mat dstImage5x5 = new Mat();

        Mat dstImage3x3 = new Mat();
        Random rand = new Random();

        int n = rand.nextInt(50000) + 1;


        Imgproc.medianBlur(srcImage, dstImage5x5, SAMPLING_WINDOW_5x5);
        Imgproc.medianBlur(srcImage, dstImage3x3, SAMPLING_WINDOW_3x3);

        return dstImage3x3;
    }
}
