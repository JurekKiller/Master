package sample;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MatToBufferImage {

    public static BufferedImage MatToBufferImage(Mat matrix) {
        MatOfByte mob=new MatOfByte();
        Imgcodecs.imencode(".jpg", matrix, mob);
        byte ba[]=mob.toArray();

        BufferedImage bi= null;
        try {
            bi = ImageIO.read(new ByteArrayInputStream(ba));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi;
    }
}
