package sample;


import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class FormatConverter {

    public static BufferedImage MatToBufferImage(Mat matrix) {
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".jpg", matrix, mob);
        byte ba[] = mob.toArray();
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(new ByteArrayInputStream(ba));
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveToImage(bi);
        return bi;
    }

    public static void saveToImage(BufferedImage bufferedImage) {
        Random rand = new Random();
        int n = rand.nextInt(50000) + 1;
        File outputfile2 = new File("BorderHistorgram/BufferImage" + n + ".jpg");
        try {
            ImageIO.write(bufferedImage, "jpg", outputfile2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static BufferedImage MBFImageToBufferImage(MBFImage image) {
        Random rand = new Random();

        int n = rand.nextInt(50000) + 1;
        try {
            ImageUtilities.write(image, new File("SWT/swt" + n + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ImageUtilities.createBufferedImage(image);
    }

}
