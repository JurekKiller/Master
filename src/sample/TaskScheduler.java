package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import sample.BorderHistorgram.Plate;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static sample.Filter.medianFilter;

public class TaskScheduler {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        File rootDir = new File("Final");
        File[] files = rootDir.listFiles();
        Plate plate;

//        Mat af = Imgcodecs.imread("BorderH/BorderH_threshold32749.jpg");
//        Imgcodecs.imwrite("dupa.jpg", af);
//        BufferedImage buffer = FormatConverter.MatToBufferImage(af);
//        StringDetection.ConvertImageToString(buffer);

//
        Arrays.stream(files).forEach(images -> System.out.println("LOADED : " + images.getName()));

        List<Mat> listOfImages = Arrays.stream(files)
                .map(file -> Imgcodecs.imread(file.getPath(), CV_LOAD_IMAGE_GRAYSCALE))
                .collect(Collectors.toList());


        List<Mat> ab = listOfImages.stream()
                .map(ImageBlurring::medianBlurring)
                .collect(Collectors.toList());


        List<Mat> a = ab.stream()
                .map(LicenceClassifier::rectangleDetection)
                // .map(x -> adaptiveThresholding(x))
                .map(y -> medianFilter(y))
                .collect(Collectors.toList());


        List<BufferedImage> ac = a.stream()
                .map(Thresholding::adaptiveThresholding)
                .map(FormatConverter::MatToBufferImage)
                //   .map(SWTransform::SWTransform)
                //   .map(FormatConverter::MBFImageToBufferImage)
                //    .map(MatToBufferImage::mat2)
                //   .map(Thresholding::adaptiveThresholding)
                //    .map(MatToBufferImage::MatToBufferImage)
                .collect(Collectors.toList());
//
        List<BufferedImage> readyFormOCR = ac.stream()
                .map(angle -> Rotation.rotateImage(angle))
                .map(newPlate -> new Plate(newPlate))
                .map(borderHistorgram -> borderHistorgram.normalize())
                .collect(Collectors.toList());


        readyFormOCR.parallelStream().forEach(StringDetection::ConvertImageToString);


//
//        ac.stream().forEach(StringDetection::ConvertImageToString);
        // System.out.println(a.size());
        //    System.out.println("end");
        System.out.println("");

    }

}
