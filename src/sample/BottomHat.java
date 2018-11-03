package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.Random;

public class BottomHat {

    public static Mat ConversionBlackHat(Mat srcImage) {
        int kernelSize =11;
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Random rand = new Random();
        int n = rand.nextInt(50000) + 1;

        Mat matImgDst = new Mat();
        Mat matImgDstInv = new Mat();
        Mat element = Imgproc.getStructuringElement(Imgproc.CV_SHAPE_RECT, new Size(2 * kernelSize + 1, 2 * kernelSize + 1),
                new Point(kernelSize, kernelSize));

        Imgproc.morphologyEx(srcImage, matImgDst, Imgproc.MORPH_BLACKHAT, element);
        Core.bitwise_not(matImgDst, matImgDstInv);
        Imgcodecs.imwrite("BlackHat/ConversionBlackHat_"+n+".jpg", matImgDst);
        return matImgDst;
    }
}

