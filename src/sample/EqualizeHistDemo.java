package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

class EqualizeHist {
    public void run(String[] args) {
        String filename = args.length > 0 ? args[0] : "adaptiveThresholding/threshold41666.jpg";
        Mat src = Imgcodecs.imread(filename);
        if (src.empty()) {
            System.err.println("Cannot read image: " + filename);
            System.exit(0);
        }
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);
        Mat dst = new Mat();
        Imgproc.equalizeHist(src, dst);
        HighGui.imshow("Source image", src);
        HighGui.imshow("Equalized Image", dst);
        HighGui.waitKey(0);
        System.exit(0);
    }
}

public class EqualizeHistDemo {
    public static void main(String[] args) {
        // Load the native OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        new EqualizeHist().run(args);
    }
}
