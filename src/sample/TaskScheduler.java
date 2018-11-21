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

import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_COLOR;
import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static sample.Filter.medianFilter;

public class TaskScheduler {
    public static void main(String[] args) {

//
        List<String> cascadeClassifiers = Arrays.asList(
                "Klasyfikatory/lpdcascade_eu.xml",
                "Klasyfikatory/ldpcascade_pl.xml",
                "Klasyfikatory/haarrcascade_eu.xml",
                "Klasyfikatory/haarcascade_rus.xml");

//

        System.out.println(cascadeClassifiers.get(3));
        List<BufferedImage> haarcascade_rus = detekcjaTablcy(cascadeClassifiers.get(3), CV_LOAD_IMAGE_GRAYSCALE);
        OCRdetection(haarcascade_rus);

        System.out.println(cascadeClassifiers.get(0));
        List<BufferedImage> lpdcascade_eu = detekcjaTablcy(cascadeClassifiers.get(0), CV_LOAD_IMAGE_COLOR);
        OCRdetection(lpdcascade_eu);

//        System.out.println(cascadeClassifiers.get(1));
//        List<BufferedImage> ldpcascade_pl = detekcjaTablcy(cascadeClassifiers.get(1),CV_LOAD_IMAGE_UNCHANGED);
//        OCRdetection(ldpcascade_pl);

//
        System.out.println(cascadeClassifiers.get(2));
        List<BufferedImage> haarrcascade_eu_unchanged = detekcjaTablcy(cascadeClassifiers.get(2), CV_LOAD_IMAGE_COLOR);
        OCRdetection(haarrcascade_eu_unchanged);


        System.out.println("");

    }


    public static List<BufferedImage> detekcjaTablcy(String XML, int settings) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        File rootDir = new File("Samochody");
        File[] files = rootDir.listFiles();
        Plate plate;

//        Mat af = Imgcodecs.imread("BorderH/BorderH_threshold32749.jpg");
//        Imgcodecs.imwrite("dupa.jpg", af);
//        BufferedImage buffer = FormatConverter.MatToBufferImage(af);
//        StringDetection.ConvertImageToString(buffer);

//
        Arrays.stream(files).forEach(images -> System.out.println("LOADED : " + images.getName()));

        List<Mat> listOfImages = Arrays.stream(files)
                .map(file -> Imgcodecs.imread(file.getPath(), settings))
                .collect(Collectors.toList());


//        )
        if (settings == CV_LOAD_IMAGE_GRAYSCALE) {
            listOfImages = listOfImages.stream()
                    //
                    .map(ImageBlurring::medianBlurring)
                    .collect(Collectors.toList());
        }


        List<Mat> a = listOfImages.stream()
                //.map(x -> Thresholding.morfpH(x))
                .map(x -> LicenceClassifier.rectangleDetection(x, XML))
                // .map(x -> adaptiveThresholding(x))
                .map(y -> medianFilter(y))
                .collect(Collectors.toList());


        return a.stream()
                .map(image -> Thresholding.adaptiveThresholding(image, settings))
                .map(FormatConverter::MatToBufferImage)
                //   .map(SWTransform::SWTransform)
                //   .map(FormatConverter::MBFImageToBufferImage)
                //    .map(MatToBufferImage::mat2)
                //   .map(Thresholding::adaptiveThresholding)
                //    .map(MatToBufferImage::MatToBufferImage)
                .collect(Collectors.toList());
    }

    public static void OCRdetection(List<BufferedImage> tablice) {

        List<BufferedImage> readyFormOCR = tablice.stream()
                .map(angle -> Rotation.rotateImage(angle))
                .map(newPlate -> new Plate(newPlate))
                .map(borderHistorgram -> borderHistorgram.normalize())
                .collect(Collectors.toList());


        readyFormOCR.parallelStream().forEach(StringDetection::ConvertImageToString);


    }

}
