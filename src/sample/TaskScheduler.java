package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static sample.ImageBlurring.medianBlurring;
import static sample.Thresholding.adaptiveThresholding;

public class TaskScheduler {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        File rootDir = new File("punto");
        File[] files = rootDir.listFiles();





            Arrays.stream(files).forEach(images -> System.out.println("LOADED : " + images.getName()));

            List<Mat> listOfImages = Arrays.stream(files)
                    .map(file -> Imgcodecs.imread(file.getPath(), CV_LOAD_IMAGE_GRAYSCALE))
                    .collect(Collectors.toList());

            System.out.println(listOfImages.size());
            //   .map(file -> ConversionBlackHat(Imgcodecs.imread(file.getPath(), CV_LOAD_IMAGE_GRAYSCALE), file.getName(), file.getPath()))
//                .map(image -> Thresholding.ConvertThresholding(image))
//                .collect(Collectors.toList());


            List<Mat> ab = listOfImages.parallelStream()
                    .map(image -> medianBlurring(image))
                    .collect(Collectors.toList());


            List<Mat> a = ab.parallelStream()
                    .map(file -> LicenceClassifier.rectangleDetection(file))
                    .map(x -> adaptiveThresholding(x))
                 //   .map(y -> medianFilter(y))
                    .collect(Collectors.toList());


           List<BufferedImage> ac = a.parallelStream()
                    .map(file -> MatToBufferImage.MatToBufferImage(file))
                   .collect(Collectors.toList());

            ac.stream().forEach(x -> StringDetection.ConvertImageToString(x));
            System.out.println(a.size());
            System.out.println("dupa");
            System.out.println("");

        }

}