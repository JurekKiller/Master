package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.util.Random;

public class Filter {

    public static Mat medianFilter(Mat images){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        int SAMPLING_WINDOW_3x3 = 3;
        Mat dstImage3x3 = new Mat();
        Random rand = new Random();

        int n = rand.nextInt(50000) + 1;
        Imgproc.medianBlur(images, dstImage3x3, SAMPLING_WINDOW_3x3);
        //   Imgcodecs.imwrite("MedianBlur3x3/ConvertThresholding" + n +".jpg",dstImage3x3);


        return dstImage3x3;
    }

}
