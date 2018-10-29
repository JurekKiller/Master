package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static sample.BottomHat.ConversionBlackHat;

public class TaskScheduler {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        File rootDir= new File("cars");
        File[] files = rootDir.listFiles();




         List<Mat> a = Arrays.stream(files)

                .map(file -> ConversionBlackHat(Imgcodecs.imread(file.getPath(), CV_LOAD_IMAGE_GRAYSCALE), file.getName(), file.getPath()))
                .collect(Collectors.toList());

         List<Mat> b= a.stream()
                .map(file-> LicenceClassifier.rectangleDetection(file))
                .collect(Collectors.toList());




         System.out.print("dupa");

//        for(File file :files) {
//            String src = file.getPath();
//            System.out.println(src);
//            Mat image = Imgcodecs.imread(src, CV_LOAD_IMAGE_GRAYSCALE);
//            String name = file.getName();
//            System.out.println(name);
//
//            Mat bottomHat = BottomHat.ConversionBlackHat(image, name);
//
//
//        }
    }
}
