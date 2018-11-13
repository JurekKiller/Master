package sample;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Rotation {

    public static BufferedImage rotateImage(BufferedImage image, float angleDegrees, int imageType) {
        long start = System.currentTimeMillis();
        //angleDegrees %= 360;
        BufferedImage returnImage = new BufferedImage(image.getWidth(), image.getHeight(), imageType);
        Graphics2D g2d = returnImage.createGraphics();
        g2d.rotate(Math.toRadians(angleDegrees), returnImage.getWidth() / 2, returnImage.getHeight() / 2);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        long end = System.currentTimeMillis();
        float duration = 1.0f * (end - start) / 1000;
        //System.out.println("Duration: " + duration + " seconds");
        return returnImage;
    }
    // test code:

}
