package sample;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import sample.BorderHistorgram.Graph;
import sample.BorderHistorgram.HoughTransformation;
import sample.BorderHistorgram.Photo;
import sample.BorderHistorgram.Plate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import static sample.FormatConverter.MatToBufferImage;

public class SWTTest {
    public static void main(String[] args) throws IOException {


        File rootDir = new File("adaptiveThresholding");
        File[] files = rootDir.listFiles();


        Arrays.stream(files).forEach(images -> System.out.println("LOADED : " + images.getName()));

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        File file = new File("Cropped/croppingImg1030.jpg");
        FileInputStream fis = new FileInputStream(file);
        Photo p = new Photo(fis);
        HoughTransformation hough = p.getHoughTransformation();
        Photo transformed =
                new Photo(hough.render(HoughTransformation.RENDER_TRANSFORMONLY, HoughTransformation.COLOR_HUE));

        float a = hough.getDx();
        float b = hough.getDy();
        float an = hough.getAngle();
        float c = (float) ((180 * Math.atan(b / a)) / Math.PI);

        transformed.saveImage("DUPA.jpg");
        p.close();
        transformed.close();


        Mat aaa = Imgcodecs.imread("Cropped/croppingImg1030.jpg");

        // BufferedImage bufferedImage = Rotation.rotateImage(FormatConverter.MatToBufferImage(aaa), (float) an, TYPE_BYTE_GRAY);
        //  FormatConverter.saveToImage(bufferedImage);

        //  Arrays.stream(files).forEach(images -> BorderHistogram(images.getName()));


        // Mat dstImage = new Mat();
        //  Imgproc.threshold(srcImage, dstImage, 110, 255, Imgproc.THRESH_OTSU | Imgproc.THRESH_BINARY); // not bad !!
//        File file = new File("a.jpg");
//        FileInputStream fis = new FileInputStream(file);
//        Photo p = new Photo(fis);
//        HoughTransformation hough = p.getHoughTransformation();
//        Photo transformed =
//                new Photo(hough.render(HoughTransformation.RENDER_TRANSFORMONLY, HoughTransformation.COLOR_HUE));
//        transformed.saveImage("DUPA.jpg");
//        p.close();
//        transformed.close();
        //  BufferedImage a= MatToBufferImage(dstImage);

//        CarSnapshot carSnapshot = new CarSnapshot(MatToBufferImage(srcImage2));
//
//        List<Band> aa = carSnapshot.getBands();
//        List<Plate> im = aa.get(0).getPlates();
//        Plate aa3 = im.get(0);]
//        aa3.normalize();
//        aa3.saveImage("plate.jpg");
//        List<Char> chars = aa3.getChars();
//        Char pp = chars.get(0);
//        pp.normalize();




//       List a = plate.getChars();
//        Photo photo = new Photo(MatToBufferImage(srcImage));
//        photo.verticalEdgeDetector(MatToBufferImage(srcImage));
//        photo.getHoughTransformation();


//        HorizontalProj.Hprojt();
//
//        File inputFile = new File("PUPA5.jpg");
//        Image someImage = null;
//        try {
//            someImage = ImageIO.read(inputFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ImagePlus imagePlus = new ImagePlus();
//        imagePlus.setImage(someImage);
//        ColorProcessor processor = (ColorProcessor) imagePlus.getProcessor();


//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        final SWTTextDetector detector = new SWTTextDetector();
//        detector.getOptions().direction = SWTTextDetector.Direction.DarkOnLight;
//        Mat srcImage = Imgcodecs.imread("PUPA5.jpg");
//        Mat dstImage = new Mat();
//        dstImage = Thresholding.adaptiveThresholding(srcImage);
//        //    Imgproc.threshold(srcImage, dstImage, 110, 255, 0);
//        //  Imgproc.adaptiveThreshold(srcImage, dstImage, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 11, 3);
//        //     Imgproc.adaptiveThreshold(srcImage,dstImage,255,Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,Imgproc.THRESH_BINARY,3,3);
//        BufferedImage a = FormatConverter.MatToBufferImage(dstImage);
//        final MBFImage image = ImageUtilities.createMBFImage(a, false);
//
//
//        //     final MBFImage image = ImageUtilities.readMBF(new File(
//        //"cars/croppingImg4626.jpg"));
//        // image.flipX();
//
//        // final MBFImage image = new MBFImage(1000, 500, 3);
//        // image.drawText("hello world hello world", 100, 100, new
//        // GeneralFont("Time New Roman", 80), 80);
//
//        detector.analyseImage(image.flatten());
//
//        final MBFImage allLetters = image.clone();
//        for (final LetterCandidate lc : detector.getLetters())
//            allLetters.drawShape(lc.getRegularBoundingBox(), RGBColour.GREEN);
//        DisplayUtilities.display(allLetters, "All candidate letters before line grouping.");
//
//        for (final LineCandidate line : detector.getLines()) {
//            image.drawShape(line.getRegularBoundingBox(), 3, RGBColour.RED);
//
//            for (final LetterCandidate lc : line.getLetters())
//                image.drawShape(lc.getRegularBoundingBox(), 3, RGBColour.GREEN);
//
//            for (final WordCandidate wc : line.getWords())
//                image.drawShape(wc.getRegularBoundingBox(), 3, RGBColour.BLUE);
//        }
//        DisplayUtilities.display(image, "Filtered candidate letters, lines and words.");
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
            // handle exception
        }
        try {
            plate.saveImage("BorderH/BorderH_" + path);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}