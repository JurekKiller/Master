package sample;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;

public class FilesLoader {
    private String rootDirectory = "Samochody";

    public static List<Mat> loadFiles(File[] files, int settings) {
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


    public String getRootDirectory() {
        return rootDirectory;
    }
}
