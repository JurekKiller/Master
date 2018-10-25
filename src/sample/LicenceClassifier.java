package sample;


import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class LicenceClassifier {
    public Mat rectangleDetection() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        LicenceClassifier licenceClassifier = new LicenceClassifier();
        Mat imageBlackHat = BottomHat.ConversionBlackHat();

        return licenceClassifier.croppingImg(imageBlackHat);

    }

    private Mat croppingImg(Mat image) {
        CascadeClassifier cascadeClassifier = new CascadeClassifier("haarcascade_russian_plate_number.xml");
        MatOfRect rectangleD = new MatOfRect();

        cascadeClassifier.detectMultiScale(image, rectangleD);
        Rect rectCrop = null;
        for (Rect rect : rectangleD.toArray()) {

            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
            rectCrop = new Rect(rect.x, rect.y, rect.width, rect.height);
        }
        Mat markedRectangle = new Mat(image, rectCrop);
        Imgcodecs.imwrite("croppingImg.jpg", markedRectangle);
        return markedRectangle;
    }
}


