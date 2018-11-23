package sample;


import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class LicenceClassifier {

    protected static List<Mat> cropPlate(List<Mat> imagesLoaded, String XML) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        return imagesLoaded.stream()
                .map(x -> LicenceClassifier.rectangleDetection(x, XML))
                .map(Filter::medianFilter)
                .collect(Collectors.toList());
    }


    private static Mat rectangleDetection(Mat image, String XLM) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Random rand = new Random();

        int  n = rand.nextInt(5000) + 1;

        CascadeClassifier cascadeClassifier = new CascadeClassifier(XLM);
        MatOfRect rectangleD = new MatOfRect();

        cascadeClassifier.detectMultiScale(image, rectangleD);
        Rect rectCrop = null;
        for (Rect rect : rectangleD.toArray()) {

            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
            rectCrop = new Rect(rect.x, rect.y, rect.width, rect.height);
        }
        Mat markedRectangle;
        if(rectCrop==null){
            Mat unrecognised = Imgcodecs.imread("404.jpg");
            Mat retuned = new Mat();
            Imgproc.cvtColor(unrecognised, retuned, Imgproc.COLOR_RGB2GRAY);
            return retuned;
        }
        else{
           markedRectangle=  new Mat(image, rectCrop);
            Imgcodecs.imwrite("Cropped/croppingImg" + n + ".jpg", markedRectangle);
           return markedRectangle;
        }
    }
}


