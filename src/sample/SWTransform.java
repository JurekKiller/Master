package sample;

import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.text.extraction.swt.LetterCandidate;
import org.openimaj.image.text.extraction.swt.LineCandidate;
import org.openimaj.image.text.extraction.swt.SWTTextDetector;
import org.openimaj.image.text.extraction.swt.WordCandidate;

import java.awt.image.BufferedImage;

public class SWTransform {


   // public static void main(String[] args) throws Exception {
       // CannyEgdes.cannyEgdesConverter();



        public static MBFImage SWTransform(BufferedImage img){
        final SWTTextDetector detector = new SWTTextDetector();
        detector.getOptions().direction = SWTTextDetector.Direction.DarkOnLight ;
      // detector.getOptions().minHeight = 60;
     //  detector.getOptions().maxHeight = 130;


       final MBFImage image = ImageUtilities.createMBFImage(img,false)     ;

//        final MBFImage image = ImageUtilities.readMBF(new File(
//                "Cropped2/croppingImg2057.jpg"));
       // image.flipX();

        // final MBFImage image = new MBFImage(1000, 500, 3);
        // image.drawText("hello world hello world", 100, 100, new
        // GeneralFont("Time New Roman", 80), 80);

        detector.analyseImage(image.flatten());

        final MBFImage allLetters = image.clone();
        for (final LetterCandidate lc : detector.getLetters())
         //   allLetters.drawShape(lc.getRegularBoundingBox(), RGBColour.GREEN);

     //   DisplayUtilities.display(allLetters, "All candidate letters before line grouping.");

        for (final LineCandidate line : detector.getLines()) {
           // image.drawShape(line.getRegularBoundingBox(), 3, RGBColour.RED);

          //  for (final LetterCandidate lc : line.getLetters())
            //   image.drawShape(lc.getRegularBoundingBox(), 3, RGBColour.GREEN);

            for (final WordCandidate wc : line.getWords()) {
                //   image.drawShape(wc.getRegularBoundingBox(), 3, RGBColour.BLUE);

                //  ImageUtilities.write(a, new File("shuffled2.jpg"));
                return image.extractROI(wc.getRegularBoundingBox());

            }
        }
     //   DisplayUtilities.display(image, "Filtered candidate letters, lines and words.");
        return image;
    }



}