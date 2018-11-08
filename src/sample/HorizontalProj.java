package sample;

import ij.process.ImageProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.util.ArrayList;
import java.util.List;

import static org.opencv.core.Core.REDUCE_SUM;
import static org.opencv.core.Core.reduce;
import static org.opencv.core.CvType.CV_32S;
import static org.opencv.imgcodecs.Imgcodecs.IMREAD_GRAYSCALE;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgproc.Imgproc.THRESH_OTSU;
import static org.opencv.imgproc.Imgproc.threshold;

public class HorizontalProj {


    public static String Hprojt() {
        //  Mat   img = Imgcodecs.imread("test_1.png");
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat vertMat = new Mat();
        Mat horzMat = new Mat();


        Mat imgHist = imread("adaptiveThresholding/threshold27211.jpg", IMREAD_GRAYSCALE);
        threshold(imgHist, imgHist, 128, 255, THRESH_OTSU);

        reduce(imgHist, vertMat, 1, REDUCE_SUM, CV_32S);
        reduce(imgHist, horzMat, 0, REDUCE_SUM, CV_32S);
        int ss = vertMat.channels();
        Mat a = horzMat.t();
        return horzMat.dump();
        //  vertMat.total();
        //      vertMat.
        // BufferedImage aa= FormatConverter.MatToBufferImage(horzMat);


//        Imgcodecs.imwrite("dupa.jpg", vertMat);
//        MatOfFloat ranges = new MatOfFloat(0f, 256f);
//        MatOfInt histSize = new MatOfInt(25);
//        calcHist(Arrays.asList(vertMat), new MatOfInt(0),
//                new Mat(), vertMat, histSize, ranges);


    }


    public void run(ImageProcessor ip) {


        int M = ip.getWidth();
        int N = ip.getHeight();
        int[] horProj = new int[N];
        int[] verProj = new int[M];
        List<Integer> lista = new ArrayList<Integer>();

        for (int i = 0; i < N; i++) {
            for (int k = 0; k < M; k++) {
                int p = ip.getPixel(i, k);
                horProj[i] += p;
                verProj[k] += p;


            }
        }

        ip.getHistogram();
        int a = horProj.length;
//        bla = ImageProcessor.toBuffer
        //     Imgproc.calcHist(horProj, new MatOfInt(2), new Mat(), rHist, new MatOfInt(histSize), histRange, accumulate);
        // Mat n = new Mat();

    }


}

