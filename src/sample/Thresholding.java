package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.util.Random;


public class Thresholding {

    public static Mat adaptiveThresholding(Mat srcImage) {
        int Cooooo3 = 3;
        int Cooooo5 = 5;
        int Cooooo7 = 13;

        Mat matImgDstInv = new Mat();
        Random rand = new Random();
        Mat disImag = new Mat();
        Mat disImag2 = new Mat();
        int n = rand.nextInt(50000) + 1;
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat dstImage = new Mat();
        int kernelSize = 11;


        //Mat element = Imgproc.getStructuringElement(Imgproc.CV_SHAPE_RECT, new Size(2 * kernelSize + 1, 2 * kernelSize + 1),
        //    new Point(kernelSize, kernelSize));
        //Imgproc.morphologyEx(srcImage, dstImage, Imgproc.MORPH_BLACKHAT, element);
        //  Imgproc.adaptiveThreshold(dstImage, disImag2, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 3, Cooooo7);
        //   Imgproc.threshold(srcImage, dstImage, 128, 255, 0);
        // Mat kernel = Imgproc.getStructuringElement(Imgproc.CV_SHAPE_CROSS, new Size(3,3));

        //   Imgproc.morphologyEx(srcImage,disImag,Imgproc.MORPH_OPEN,new Mat(new Size(3, 3), CvType.CV_8U, new Scalar(255)));

        // Imgproc.morphologyEx(dstImage,disImag2,Imgproc.MORPH_CLOSE,new Mat(new Size(3, 3), CvType.CV_8U, new Scalar(255)));


        //  Imgproc.Canny(disImag2,matImgDstInv,0,255);
        //  Imgproc.morphologyEx(dstImage,disImag2,Imgproc.MORPH_ERODE,kernel);
        //Imgproc.morphologyEx(disImag2, disImag, Imgproc.MORPH_ERODE, kernel);
        //  Core.bitwise_not(disImag, matImgDstInv);

        Imgcodecs.imwrite("adaptiveThresholding/Canny2" + n + ".jpg", srcImage);


        return srcImage;
    }
}
