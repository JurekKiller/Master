package sample;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader  {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

    }
    private BufferedImage image;

    private ImageLoader() {
        super();
        File imageFile = new File("img.jpg");
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Image load failed");
            e.printStackTrace();
        }
    }
        public static void main(String[] args) {
       ImageLoader obraz  = new ImageLoader();
       System.out.print(obraz.image.getHeight() + obraz.image.getWidth());

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
