package sample;

import net.sourceforge.tess4j.Tesseract;

import java.awt.image.BufferedImage;

public class StringDetection {
    public static void ConvertImageToString(BufferedImage image) {
        Tesseract tesseract = new Tesseract();
        try {
            //ConvertThresholding();
            //BufferedImage bufferedImage = MatToBufferImage.MatToBufferImage(ConvertThresholding());
            tesseract.setDatapath("tessdata");
            String string = tesseract.doOCR(image);
            //String text = tesseract.doOCR(new File("C:/Users/Pawel/Desktop/Projekt Inż/SerialDetector/BorderH/BorderH_threshold28792.jpg"));
            System.out.println(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}