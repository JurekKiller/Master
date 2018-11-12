package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

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


//       Imgproc.cvtColor(srcImage  , disImag, Imgproc.COLOR_RGB2GRAY);
        //Mat element = Imgproc.getStructuringElement(Imgproc.CV_SHAPE_RECT, new Size(2 * kernelSize + 1, 2 * kernelSize + 1),
        //    new Point(kernelSize, kernelSize));
        //Imgproc.morphologyEx(srcImage, dstImage, Imgproc.MORPH_BLACKHAT, element);
        //   Imgproc.adaptiveThreshold(srcImage, disImag2, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 3, Cooooo7);

//        Imgproc.cvtColor(srcImage, disImag, Imgproc.COLOR_RGB2GRAY);
        //    Imgproc.threshold(srcImage, dstImage, 100, 255, 0);
        //  Mat kernel = Imgproc.getStructuringElement(Imgproc.CV_SHAPE_CROSS, new Size(1,1));

        Imgproc.threshold(srcImage, dstImage, 0, 255, Imgproc.THRESH_OTSU | Imgproc.THRESH_BINARY); // not bad !!

        //   Imgproc.morphologyEx(srcImage,disImag,Imgproc.MORPH_OPEN,new Mat(new Size(3, 3), CvType.CV_8U, new Scalar(255)));

        // Imgproc.morphologyEx(dstImage,disImag2,Imgproc.MORPH_CLOSE,new Mat(new Size(3, 3), CvType.CV_8U, new Scalar(255)));


        //  Imgproc.Canny(disImag2,matImgDstInv,0,255);
        //  Imgproc.morphologyEx(disImag2,disImag,Imgproc.MORPH_HITMISS,kernel);
        //Imgproc.morphologyEx(disImag2, disImag, Imgproc.MORPH_ERODE, kernel);
        //  Core.bitwise_not(disImag, matImgDstInv);

        Imgcodecs.imwrite("adaptiveThresholding/threshold" + n + ".jpg", dstImage);


        return dstImage;
    }
}
