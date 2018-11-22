package sample;

import sample.BorderHistorgram.HoughTransformation;
import sample.BorderHistorgram.Photo;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_BYTE_GRAY;

public class Rotation {

    public static BufferedImage rotateImage(BufferedImage image) {

        Photo p = new Photo(image);
        HoughTransformation hough = p.getHoughTransformation();
        Photo transformed =
                new Photo(hough.render(HoughTransformation.RENDER_TRANSFORMONLY, HoughTransformation.COLOR_HUE));

        float a = hough.getDx();
        float b = hough.getDy();

        float angleDegrees = hough.getAngle();

        long start = System.currentTimeMillis();
        BufferedImage returnImage = new BufferedImage(transformed.getWidth(), transformed.getHeight(), TYPE_BYTE_GRAY);
        Graphics2D g2d = returnImage.createGraphics();
        g2d.rotate(Math.toRadians(angleDegrees * (-1)), returnImage.getWidth() / 2, returnImage.getHeight() / 2);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        long end = System.currentTimeMillis();
        float duration = 1.0f * (end - start) / 1000;

        System.out.println("Angle: " + angleDegrees);
        System.out.println("Duration: " + duration + " seconds");

        return returnImage;
    }
}
