package sample;


import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class LicenceClassifier {
//    public static void main(String[] args) {
//
//        // For proper execution of native libraries
//        // Core.NATIVE_LIBRARY_NAME must be loaded before
//        // calling any of the opencv methods
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        Mat dst = new Mat();
//        // Face detector creation by loading source cascade xml file
//        // using CascadeClassifier.
//        // the file can be downloade from
//        // https://github.com/opencv/opencv/blob/master/data/haarcascades/
//        // haarcascade_frontalface_alt.xml
//        // and must be placed in same directory of the source java file
//        CascadeClassifier cascadeClassifier = new CascadeClassifier();
//
//        if(cascadeClassifier.load("haarcascade_frontalcatface.xml"))
//        {
//            System.out.println("Success loading face cascade");
//        }
//
//        Mat frame_gray = new Mat();
//
//        Mat image = Imgcodecs.imread("face.jpg");
//        Imgproc.cvtColor(image, frame_gray, Imgproc.COLOR_BGRA2GRAY);
//
//        Imgproc.equalizeHist(frame_gray, frame_gray);
//
//
//
//      //  image.
//
//        MatOfRect carDetections = new MatOfRect();
//        cascadeClassifier.detectMultiScale(frame_gray,carDetections,1.1,2,2,new Size(10,10),new Size(400,400));
//        for (Rect rect : carDetections.toArray())
//        {
//            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
//                    new Scalar(0, 255, 0));
//        }
//
//
//        Imgcodecs.imwrite("Ouput2.jpg", dst);
//    }
//}






        public static void main(String[] args) {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            CascadeClassifier faceDetector = new CascadeClassifier("haarcascade_russian_plate_number.xml");

            Mat image = Imgcodecs.imread("Adaptivemean_thresh_binary.jpg");

            MatOfRect faceD = new MatOfRect();
            faceDetector.detectMultiScale(image,faceD);
            for (Rect rect:faceD.toArray()){
                Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                   new Scalar(0, 255, 0));
            }

            String outputFile = "OUTPUT2.png";
            //System.out.println(String.format("writing %s", outputFile));
            Imgcodecs.imwrite(outputFile,image);

        }
    }


