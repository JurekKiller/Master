package sample;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ImageLoader  {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

    }
    private BufferedImage image;

    private void ImageLoader2(Path path) {

        File imageFile = new File("img.jpg");



        try (Stream<Path> paths = Files.walk(Paths.get("C:/Users/Pawel/Desktop/Projekt Inż"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(System.out::println);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

//        try {
//            image = ImageIO.read(imageFile);
//        } catch (IOException e) {
//            System.err.println("Image load failed");
//            e.printStackTrace();
//        }
    }

//    static String readFirstLineFromFileWithFinallyBlock(String path)
//            throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader(path));
//        try {
//            return br.readLine();
//        } finally {
//            if (br != null) br.close();
//        }
//    }


        public static void main(String[] args) throws IOException {

       //readFirstLineFromFileWithFinallyBlock("C:/Users/Pawel/Desktop/Projekt Inż");
      // System.out.print(obraz.image.getHeight() + obraz.image.getWidth());

        System.out.println("Welcome to OpenCV " + Core.VERSION);
        Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(0));
        System.out.println("OpenCV Mat: " + m);
        Mat mr1 = m.row(1);
        mr1.setTo(new Scalar(1));
        Mat mc5 = m.col(5);
        mc5.setTo(new Scalar(5));
        System.out.println("OpenCV Mat data:\n" + m.dump());
        System.out.print(Core.VERSION_STATUS);


    }
}
