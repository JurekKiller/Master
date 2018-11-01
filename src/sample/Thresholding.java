package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.util.Random;


public class Thresholding {

    public static Mat adaptiveThresholding(Mat srcImage) {
        Random rand = new Random();
        Mat disImag = new Mat();
        Mat disImag2 = new Mat();
        int n = rand.nextInt(50000) + 1;
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat dstImage = new Mat();
     //   Imgproc.adaptiveThreshold(srcImage, dstImage, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 5, 3);
     //   Imgproc.morphologyEx(dstImage,disImag,Imgproc.MORPH_OPEN,new Mat(new Size(3, 3), CvType.CV_8U, new Scalar(255)));

     //   Imgproc.morphologyEx(disImag,disImag2,Imgproc.MORPH_CLOSE,new Mat(new Size(3, 3), CvType.CV_8U, new Scalar(255)));
      //  Imgcodecs.imwrite("adaptiveThresholding/MORPH_CLOSE" + n + ".jpg", disImag2);





        return disImag2;
    }
}
