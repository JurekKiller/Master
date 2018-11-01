package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class CannyEgdes {

    public static void cannyEgdesConverter(){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat imgdes = new Mat();
      Mat image = Imgcodecs.imread("a.jpg");

      //  Imgproc.Canny(image,imgdes,this.threshold.getValue(), this.threshold.getValue() * 3, 3, false);
        Imgcodecs.imwrite("cannyImg.jpg",imgdes);
    }
}
