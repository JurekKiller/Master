package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.Random;

public class Thresholding {

    public static Mat ConvertThresholding(Mat srcImage) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
      //  Mat src = BottomHat.ConversionBlackHat();

      //  Mat srcImage = licenceClassifier.rectangleDetection();
        Mat dstImage = new Mat();
        Random rand = new Random();

        int  n = rand.nextInt(5000) + 1;

       // Imgproc.threshold(srcImage, dstImage, 128, 255, Imgproc.THRESH_OTSU | Imgproc.THRESH_BINARY);
      //  Imgproc.GaussianBlur(srcImage,dstImage, new Size(kernelSize, kernelSize), radius);
        // Imgproc.imwrite("Adaptivemean_thresh_binary.jpg", dst);
        //   Imgproc.adaptiveThreshold(srcImage, dstImage, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 5, 10);
        Imgproc.GaussianBlur(srcImage, dstImage,new Size(5,5), 0);
        Imgcodecs.imwrite("NonBinar/ConvertThresholding" + rand+".jpg", srcImage);
        return dstImage;

    }
}
