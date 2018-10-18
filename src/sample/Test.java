package sample;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class Test {
    public static void main(String []args) {
        Tesseract tesseract = new Tesseract();
        try {
            tesseract.setDatapath("tessdata");
            String text = tesseract.doOCR(new File("C:/Users/Pawel/Desktop/Projekt Inż/SerialDetector/Adaptivemean_thresh_binary.jpg"));
            System.out.print(text);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }
}