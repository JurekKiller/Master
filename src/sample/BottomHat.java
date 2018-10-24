package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.opencv.imgcodecs.Imgcodecs.imread;

public class BottomHat {

    public static Mat ConversionBottonHatt() {
        int kernelSize = 7;
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //  int radius = (int)(doubleRadius + 0.5);
        //  int kernelSize;
        //private int kernelSize = 0;
        //String file = "C:/Users/Pawel/Desktop/Projekt Inż/SerialDetector/a.png";

        Mat src = imread("tyl.jpg", CV_LOAD_IMAGE_GRAYSCALE);
        Mat matImgDst = new Mat();
        Mat matImgDstInv = new Mat();
     //   Mat matImgDstInv = new Mat();
        Mat element = Imgproc.getStructuringElement( Imgproc.CV_SHAPE_RECT, new Size(2 * kernelSize + 1, 2 * kernelSize + 1),
                new Point(kernelSize, kernelSize));


        Imgproc.morphologyEx(src,matImgDst, Imgproc.MORPH_BLACKHAT,element);



        Core.bitwise_not(matImgDst,matImgDstInv);
        Imgcodecs.imwrite("Blackhatt2.jpg",matImgDstInv);
        return matImgDstInv;
    }




    }

