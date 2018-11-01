package sample;


import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.util.Random;

public class LicenceClassifier {


    public static Mat rectangleDetection(Mat image){
        Random rand = new Random();

        int  n = rand.nextInt(5000) + 1;

        CascadeClassifier cascadeClassifier = new CascadeClassifier("haarcascade_russian_plate_number.xml");
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
            return Imgcodecs.imread("404.jpg");
        }
        else{
           markedRectangle=  new Mat(image, rectCrop);
           Imgcodecs.imwrite("Cropped2/croppingImg"+n + ".jpg", markedRectangle);
           return markedRectangle;
        }
    }
}


