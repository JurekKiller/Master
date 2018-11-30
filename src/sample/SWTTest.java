package sample;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import sample.BorderHistorgram.Graph;
import sample.BorderHistorgram.HoughTransformation;
import sample.BorderHistorgram.Plate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import static sample.FormatConverter.MatToBufferImage;

public class SWTTest {
    public static void main(String[] args) throws IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);


        File file = new File("Cropped/croppingImg1508.jpg");
        FileInputStream fis = new FileInputStream(file);
        Mat a = Imgcodecs.imread("Cropped/croppingImg3903.jpg");
        BufferedImage image = FormatConverter.MatToBufferImage(a);
        Plate photo = new Plate(image);


        photo.saveImage("dupa.jpg");
        photo.normalize();
        BufferedImage render = photo.renderGraph();

        photo.saveImage("dupaN.jpg");
    }

    public static void BorderHistogram(String path) {


        System.out.println(path);
        Random rand = new Random();
        int n = rand.nextInt(50000) + 1;
        Mat srcImage2 = Imgcodecs.imread("adaptiveThresholding/" + path);

        Graph graph = new Graph();

        Plate plate = new Plate(MatToBufferImage(srcImage2));
        HoughTransformation aa = plate.getHoughTransformation();
        System.out.println("Angle" + aa.getAngle());
        plate.normalize();
        HoughTransformation aad = plate.getHoughTransformation();
        System.out.println("Angle" + aad.getAngle());
        BufferedImage a = plate.renderGraph();
        System.out.println(a.getWidth() + " widthcrop" + ":::" + a.getHeight() + "wysokosc");
        HoughTransformation as = plate.getHoughTransformation();
        System.out.println("Angle" + as.getAngle());

        int k = n;
        try {
            BufferedImage bi = a;  // retrieve image
            File outputfile = new File("BorderH/render_" + path);
            ImageIO.write(bi, "png", outputfile);

        } catch (IOException e) {
        }
        try {
            plate.saveImage("BorderH/BorderH_" + path);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}