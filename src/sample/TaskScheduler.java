package sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import sample.BorderHistorgram.Plate;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_COLOR;

public class TaskScheduler {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

//        System.out.println(Classifiers.map.get("HAARcascade_russia"));
//        List<BufferedImage> haarcascade_rus = plateDetection(Classifiers.map.get("HAARcascade_russia"), CV_LOAD_IMAGE_GRAYSCALE);
//        OCRrecognition(haarcascade_rus);
//
//        System.out.println(Classifiers.map.get("LBPcascade_europe"));
//        String a = Classifiers.map.get("LBPcascade_europe");
//        List<BufferedImage> lpdcascade_eu = plateDetection(Classifiers.map.get("LBPcascade_europe"), CV_LOAD_IMAGE_COLOR);
//        OCRrecognition(lpdcascade_eu);
//
//        System.out.println(Classifiers.map.get("HAARcascade_europe"));
//        List<BufferedImage> haarrcascade_eu_unchanged = plateDetection(Classifiers.map.get("HAARcascade_europe"), CV_LOAD_IMAGE_COLOR);
//        OCRrecognition(haarrcascade_eu_unchanged);

        System.out.println(Classifiers.map.get("HAARcascade_europe_rag"));
        List<BufferedImage> haarrcascade_eu_rag = plateDetection(Classifiers.map.get("HAARcascade_europe_rag"), CV_LOAD_IMAGE_COLOR);
        OCRrecognition(haarrcascade_eu_rag);


    }


    public static List<BufferedImage> plateDetection(String XML, int settings) {
        FilesLoader filesLoader = new FilesLoader();
        File rootDir = new File(filesLoader.getRootDirectory());
        File[] files = rootDir.listFiles();

        Arrays.stream(requireNonNull(files)).forEach(images -> System.out.println("LOADED : " + images.getName()));
        List<Mat> loadedImages = FilesLoader.loadFiles(files, settings);
        List<Mat> croppedPlateList = LicenceClassifier.cropPlate(loadedImages, XML);

        return Thresholding.imageThreshold(croppedPlateList, settings);
    }

    private static void OCRrecognition(List<BufferedImage> plates) {

        List<BufferedImage> rotatedImagesList = Rotation.rotation(plates);
        List<BufferedImage> OCRRecognition = rotatedImagesList.stream()
                .map(Plate::new)
                .map(Plate::normalize)
                .collect(Collectors.toList());

        OCRRecognition.parallelStream().forEach(StringDetection::ConvertImageToString);
    }
}
