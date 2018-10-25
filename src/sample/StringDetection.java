package sample;

import net.sourceforge.tess4j.Tesseract;

import java.awt.image.BufferedImage;
import java.io.File;

import static sample.Thresholding.ConvertThresholding;

public class StringDetection {
    public static void ConvertImageToString() {
        Tesseract tesseract = new Tesseract();
        try {
            ConvertThresholding();
            BufferedImage bufferedImage = MatToBufferImage.MatToBufferImage(ConvertThresholding());


            tesseract.setDatapath("tessdata");
            String string = tesseract.doOCR(bufferedImage);
            String text = tesseract.doOCR(new File("C:/Users/Pawel/Desktop/Projekt Inż/SerialDetector/Thresholding.jpg"));
            System.out.print(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}