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

    public static Mat ConversionBlackHat() {
        int kernelSize = 9;
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat src = imread("audi.jpg", CV_LOAD_IMAGE_GRAYSCALE);
        Mat matImgDst = new Mat();
        Mat matImgDstInv = new Mat();
        Mat element = Imgproc.getStructuringElement(Imgproc.CV_SHAPE_RECT, new Size(2 * kernelSize + 1, 2 * kernelSize + 1),
                new Point(kernelSize, kernelSize));


        Imgproc.morphologyEx(src, matImgDst, Imgproc.MORPH_BLACKHAT, element);
        Core.bitwise_not(matImgDst, matImgDstInv);
        Imgcodecs.imwrite("ConversionBlackHat.jpg", matImgDstInv);
        return matImgDstInv;
    }


}

