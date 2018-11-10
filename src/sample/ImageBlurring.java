package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.Random;

public class ImageBlurring {

    public static Mat medianBlurring(Mat srcImage) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        int SAMPLING_WINDOW_5x5 = 3;
        int SAMPLING_WINDOW_3x3 = 3;
        Mat dstImage5x5 = new Mat();
        Mat matCanny = new Mat();

        Mat dstImage3x3 = new Mat();
        Random rand = new Random();

        int n = rand.nextInt(50000) + 1;

        int lowThreshold = 100;
        int ratio = 3;

        // Imgproc.threshold(srcImage, dstImage, 128, 255, Imgproc.THRESH_OTSU | Imgproc.THRESH_BINARY);
        //  Imgproc.GaussianBlur(srcImage,dstImage, new Size(kernelSize, kernelSize), radius);
        // Imgproc.imwrite("Adaptivemean_thresh_binary.jpg", dst);
        //        Imgproc.adaptiveThreshold(srcImage, dstImage, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 5, 10);
//

        Imgproc.medianBlur(srcImage, dstImage5x5, SAMPLING_WINDOW_5x5);
        Imgproc.medianBlur(srcImage, dstImage3x3, SAMPLING_WINDOW_3x3);
        //Imgproc.Canny(dstImage5x5,matCanny,lowThreshold, ratio*lowThreshold, 3);


      //  Imgcodecs.imwrite("MedianBlur5x5/ConvertThresholding" + n + ".jpg", dstImage5x5);
        Imgcodecs.imwrite("MedianBlur3x3/ConvertThresholding3x3" + n + ".jpg", dstImage3x3);
        return dstImage3x3;
    }
}
