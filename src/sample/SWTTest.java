package sample;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import sample.BorderHistorgram.Plate;

import java.io.IOException;

import static sample.FormatConverter.MatToBufferImage;

public class SWTTest {
    public static void main(String[] args) throws IOException {




        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat srcImage = Imgcodecs.imread("SWT/swt28937.jpg");
//
//        File file = new File("a.jpg");
//        FileInputStream fis = new FileInputStream(file);
//        Photo p = new Photo(fis);
//        HoughTransformation hough = p.getHoughTransformation();
//        Photo transformed =
//                new Photo(hough.render(HoughTransformation.RENDER_TRANSFORMONLY, HoughTransformation.COLOR_HUE));
//        transformed.saveImage("DUPA.jpg");
//        p.close();
//        transformed.close();


        Plate plate = new Plate(MatToBufferImage(srcImage));
        // Graph a = plate.histogram(MatToBufferImage(srcImage));
//        plate.horizontalEdgeDetector(MatToBufferImage(srcImage));
//         plate.verticalEdgeDetector(MatToBufferImage(srcImage));
        plate.normalize();
        //  plate.renderGraph();
        plate.saveImage("PUPA2.jpg");

//       List a = plate.getChars();
//        Photo photo = new Photo(MatToBufferImage(srcImage));
//        photo.verticalEdgeDetector(MatToBufferImage(srcImage));
//        photo.getHoughTransformation();


//        HorizontalProj.Hprojt();
//
//        File inputFile = new File("swt15965.jpg");
//        Image someImage = null;
//        try {
//            someImage = ImageIO.read(inputFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ImagePlus imagePlus = new ImagePlus();
//        imagePlus.setImage(someImage);
//        ColorProcessor processor = (ColorProcessor) imagePlus.getProcessor();
//
//        HorizontalProj w = new HorizontalProj();
//        w.run(processor);
//
//       System.out.print(HorizontalProj.Hprojt());


//          Mat a = BorderSubstracer.borderSubtracter("a.jpg");
//        Imgcodecs.imwrite("bodrerSu3b.jpg",a);

//
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        final SWTTextDetector detector = new SWTTextDetector();
//        detector.getOptions().direction = SWTTextDetector.Direction.DarkOnLight;
//        Mat srcImage = Imgcodecs.imread("Cropped2/croppingImg2008.jpg");
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
//
//        DisplayUtilities.display(image, "Filtered candidate letters, lines and words.");
    }
}