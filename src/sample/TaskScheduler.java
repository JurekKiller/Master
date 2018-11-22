package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import sample.BorderHistorgram.Plate;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.entry;
import static java.util.Objects.requireNonNull;
import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_COLOR;
import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;

public class TaskScheduler {
    public static void main(String[] args) {


        final Map<String, String> classifiers = Map.ofEntries(
                entry("LBPcascade_europe", "Klasyfikatory/lpdcascade_eu.xml"),
                entry("LBPcascade_poland", "Klasyfikatory/ldpcascade_pl.xml"),
                entry("HAARcascade_europe", "Klasyfikatory/haarrcascade_eu.xml"),
                entry("HAARcascade_russia", "Klasyfikatory/haarcascade_rus.xml")
        );

        System.out.println(classifiers.get("HAARcascade_russia"));
        List<BufferedImage> haarcascade_rus = plateDetection(classifiers.get("HAARcascade_russia"), CV_LOAD_IMAGE_GRAYSCALE);
        OCRrecognition(haarcascade_rus);

        System.out.println(classifiers.get("LBPcascade_europe"));
        String a = classifiers.get("LBPcascade_europe");
        List<BufferedImage> lpdcascade_eu = plateDetection(classifiers.get("LBPcascade_europe"), CV_LOAD_IMAGE_COLOR);
        OCRrecognition(lpdcascade_eu);

        System.out.println(classifiers.get("HAARcascade_europe"));
        List<BufferedImage> haarrcascade_eu_unchanged = plateDetection(classifiers.get("HAARcascade_europe"), CV_LOAD_IMAGE_COLOR);
        OCRrecognition(haarrcascade_eu_unchanged);


        System.out.println("");

    }


    public static List<BufferedImage> plateDetection(String XML, int settings) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        File rootDir = new File("Samochody");
        File[] files = rootDir.listFiles();

        Arrays.stream(requireNonNull(files)).forEach(images -> System.out.println("LOADED : " + images.getName()));

        List<Mat> listOfImages = loadFiles(files, settings);

        List<Mat> croppedPlateList = cropPlate(listOfImages, XML);

        List<BufferedImage> binarizationImages = thresholding(croppedPlateList, settings);

        return binarizationImages;
    }


    private static List<Mat> loadFiles(File[] files, int settings) {
        List<Mat> listOfImages = Arrays.stream(files)
                .map(file -> Imgcodecs.imread(file.getPath(), settings))
                .collect(Collectors.toList());


        if (settings == CV_LOAD_IMAGE_GRAYSCALE) {
            listOfImages = listOfImages.stream()
                    .map(ImageBlurring::medianBlurring)
                    .collect(Collectors.toList());
        }

        return listOfImages;
    }

    private static List<Mat> cropPlate(List<Mat> listOfImages, String XML) {
        return listOfImages.stream()
                .map(x -> LicenceClassifier.rectangleDetection(x, XML))
                .map(Filter::medianFilter)
                .collect(Collectors.toList());
    }

    private static List<BufferedImage> thresholding(List<Mat> croppedPlateList, int settings) {
        return croppedPlateList.stream()
                .map(image -> Thresholding.adaptiveThresholding(image, settings))
                .map(FormatConverter::MatToBufferImage)
                .collect(Collectors.toList());

    }


    private static void OCRrecognition(List<BufferedImage> tablice) {

        List<BufferedImage> readyFormOCR = tablice.stream()
                .map(Rotation::rotateImage)
                .map(Plate::new)
                .map(Plate::normalize)
                .collect(Collectors.toList());

        readyFormOCR.parallelStream().forEach(StringDetection::ConvertImageToString);
    }
}
