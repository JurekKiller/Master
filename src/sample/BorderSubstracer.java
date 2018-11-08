package sample;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class BorderSubstracer {


    public static Mat borderSubtracter(String file_path) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat rawImage = Imgcodecs.imread(file_path);
        Mat originalImage = rawImage.clone();

        Imgproc.cvtColor(rawImage, rawImage, Imgproc.COLOR_BGR2GRAY);
        Mat sobelX = new Mat();
        Mat sobelY = new Mat();
        //Sobel in X and Y planes
        Imgproc.Sobel(rawImage, sobelX, CvType.CV_32FC1, 2, 0, 5, 0.1, Core.BORDER_DEFAULT);
        Imgproc.Sobel(rawImage, sobelY, CvType.CV_32FC1, 0, 2, 5, 0.1, Core.BORDER_DEFAULT);

        Mat summedImage = new Mat();
        Core.add(sobelX, sobelY, summedImage);
        sobelX.release();
        sobelY.release();

        Mat normalized = new Mat();

        Core.normalize(summedImage, normalized, 0, 255, Core.NORM_MINMAX, CvType.CV_8UC1);
        summedImage.release();

        Mat reducedX = new Mat();
        Core.reduce(normalized, reducedX, 0, Core.REDUCE_AVG, CvType.CV_8UC1);

        Mat reducedY = new Mat();
        Core.reduce(normalized, reducedY, 1, Core.REDUCE_AVG, CvType.CV_8UC1);
        normalized.release();
        Imgcodecs.imwrite("afterREduce.jpg", reducedX);
        Mat reducedSobelX = new Mat();
        Imgproc.Sobel(reducedX, reducedSobelX, reducedX.depth(), 2, 0);
        reducedX.release();
        Imgcodecs.imwrite("afterREduceSobelX.jpg", reducedSobelX);
        Mat reducedSobelY = new Mat();


        Imgproc.Sobel(reducedY, reducedSobelY, reducedX.depth(), 0, 2);
        Imgcodecs.imwrite("reducedSobelY.jpg", reducedSobelY);
        reducedY.release();
        //finding maximum values in both x and y planes
        Point peak_point;
        int half_pos = (int) reducedSobelX.total() / 2;

        Rect result = new Rect();

        Core.MinMaxLocResult mmr = Core.minMaxLoc(reducedSobelX.colRange(new Range(0, half_pos)));
        peak_point = mmr.maxLoc;
        result.x = (int) peak_point.x;
        mmr = Core.minMaxLoc(reducedSobelX.colRange(new Range(half_pos, (int) reducedSobelX.total())));
        peak_point = mmr.maxLoc;
        result.width = (int) (peak_point.x + half_pos - result.x);


        half_pos = (int) reducedSobelY.total() / 2;
        mmr = Core.minMaxLoc(reducedSobelY.rowRange(new Range(0, half_pos)));
        peak_point = mmr.maxLoc;
        result.y = (int) peak_point.y;

        mmr = Core.minMaxLoc(reducedSobelY.rowRange(new Range(half_pos, (int) reducedSobelY.total())));
        peak_point = mmr.maxLoc;
        result.height = (int) (peak_point.y + half_pos - result.y);

        Imgproc.rectangle(originalImage, result.br(), result.tl(), new Scalar(0, 255, 0), 5);
        return originalImage;
    }
}
