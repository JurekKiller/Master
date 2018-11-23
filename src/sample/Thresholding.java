package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_COLOR;
import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_UNCHANGED;


public class Thresholding {

    public static List<BufferedImage> imageThreshold(List<Mat> croppedPlateList, int settings) {
        return croppedPlateList.stream()
                .map(image -> Thresholding.threshold(image, settings))
                .map(FormatConverter::MatToBufferImage)
                .collect(Collectors.toList());
    }


    private static Mat threshold(Mat srcImage, int Settings) {
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


        if (Settings == CV_LOAD_IMAGE_UNCHANGED || Settings == CV_LOAD_IMAGE_COLOR) {
            Imgproc.cvtColor(srcImage, srcImage, Imgproc.COLOR_RGB2GRAY);
        }


        Imgproc.threshold(srcImage, dstImage, 0, 255, Imgproc.THRESH_OTSU | Imgproc.THRESH_BINARY); // not bad !!

        Imgcodecs.imwrite("adaptiveThresholding/threshold" + n + ".jpg", dstImage);


        return dstImage;
    }


    public static Mat morfpH(Mat matImgSrc) {
        int elementType = Imgproc.CV_SHAPE_ELLIPSE;
        int kernelSize = 1;
        Mat matImgDst = new Mat();
        int morphOpType = Imgproc.MORPH_OPEN;
        Mat element = Imgproc.getStructuringElement(elementType, new Size(2 * kernelSize + 1, 2 * kernelSize + 1),
                new Point(kernelSize, kernelSize));
        Imgproc.morphologyEx(matImgSrc, matImgDst, morphOpType, element);
        return matImgDst;
    }
}
