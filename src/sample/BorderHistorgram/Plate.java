/*
 * Copyright 2013 JavaANPR contributors
 * Copyright 2006 Ondrej Martinsky
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package sample.BorderHistorgram;

import net.sf.javaanpr.configurator.Configurator;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Plate extends Photo implements Cloneable {

    private static final Graph.ProbabilityDistributor distributor =
            new Graph.ProbabilityDistributor(0, 0, 0, 0);
    private static final int numberOfCandidates =
            Configurator.getConfigurator().getIntProperty("intelligence_numberOfChars");
    private static final int horizontalDetectionType =
            Configurator.getConfigurator().getIntProperty("platehorizontalgraph_detectionType");
    private final Plate plateCopy; // TODO refactor: remove this variable completely
    private PlateGraph graphHandle;

    public Plate(BufferedImage bi) {
        super(bi);
        plateCopy = new Plate(Photo.duplicateBufferedImage(getImage()), true);

    }

    private Plate(BufferedImage bi, boolean isCopy) { // TODO refactor: remove this, is only a copy constructor
        super(bi);
        plateCopy = null;
    }

    public BufferedImage renderGraph() {
        computeGraph();
        return graphHandle.renderHorizontally(getWidth(), 100);
    }

    public List<Peak> listOfPeaks() {
        computeGraph();
        return graphHandle.peaks;
    }

    private List<Peak> computeGraph() {
        if (graphHandle != null) {
            return graphHandle.peaks;
        }
        graphHandle = histogram(plateCopy.getImage());
        graphHandle.applyProbabilityDistributor(Plate.distributor);
        graphHandle.findPeaks(Plate.numberOfCandidates);
        return graphHandle.peaks;
    }


    @Override
    public Plate clone() {
        super.clone();
        return new Plate(duplicateBufferedImage(getImage()));
    }

    public void horizontalEdgeBi(BufferedImage image) {
        BufferedImage imageCopy = Photo.duplicateBufferedImage(image);
        float[] data = {-1, 0, 1};
        new ConvolveOp(new Kernel(1, 3, data), ConvolveOp.EDGE_NO_OP, null).filter(imageCopy, image);
    }

    /**
     * Create a clone, normalize it, threshold it with coefficient 0.999.
     * <p>
     * Function {@link Plate#cutTopBottom(BufferedImage, PlateVerticalGraph)} and
     * {@link Plate#cutLeftRight(BufferedImage, PlateHorizontalGraph)} crop the original image using horizontal and
     * vertical projections of the cloned image (which is thresholded).
     */
    public BufferedImage normalize() {
        Random rand = new Random();
        int n = rand.nextInt(50000) + 1;
        Plate clone1 = clone();
        //clone1.verticalEdgeDetector(clone1.getImage());
        PlateVerticalGraph vertical = clone1.histogramYaxis(clone1.getImage());
        setImage(cutTopBottom(getImage(), vertical));
        plateCopy.setImage(cutTopBottom(plateCopy.getImage(), vertical));
        Plate clone2 = clone();
//        if (Plate.horizontalDetectionType == 1) {
//            clone2.horizontalEdgeDetector(clone2.getImage());
//        }
        // clone2.horizontalEdgeDetector(clone1.getImage());
        PlateHorizontalGraph horizontal = clone2.histogramXaxis(clone2.getImage());
        setImage(cutLeftRight(getImage(), horizontal));
        plateCopy.setImage(cutLeftRight(plateCopy.getImage(), horizontal));
        try {
            saveImage("BorderH/sabed0" + n + ".jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //    FormatConverter.saveToImage(bufferedImage);
        return getImage();

    }

    public void normalized2() {
        Plate clone1 = clone();
//        clone1.horizontalEdgeDetector(clone1.getImage());
        PlateHorizontalGraph horizontalGraph = clone1.histogramXaxis(clone1.getImage());
        setImage(cutLeftRight(getImage(), horizontalGraph));
        plateCopy.setImage(cutLeftRight(plateCopy.getImage(), horizontalGraph));


        Plate clone2 = clone();
        clone2.verticalEdgeDetector(clone2.getImage());
        PlateVerticalGraph vertical = clone2.histogramYaxis(clone2.getImage());
        BufferedImage bufferedImage = clone().renderGraph();

        setImage(cutTopBottom(getImage(), vertical));
        plateCopy.setImage(cutTopBottom(plateCopy.getImage(), vertical));


    }


    private BufferedImage cutTopBottom(BufferedImage origin, PlateVerticalGraph graph) {
        graph.applyProbabilityDistributor(new Graph.ProbabilityDistributor(0f, 0f, 2, 2));
        List<Peak> peaks = graph.findPeak(3);
        Peak p = getBestVerticalPeak(peaks);
        return origin.getSubimage(0, p.getLeft(), getImage().getWidth(), p.getDiff());
    }

    private BufferedImage cutLeftRight(BufferedImage origin, PlateHorizontalGraph graph) {
        graph.applyProbabilityDistributor(new Graph.ProbabilityDistributor(0f, 0f, 2, 2));
        int width = origin.getWidth();
        int heiht = origin.getHeight();

        List<Peak> peaks = graph.findPeak();
        List<Peak> peaks2 = listOfPeaks();

        if (peaks.get(0).getLeft() == peaks.get(0).getRight()) {
            peaks.remove(0);
            peaks.add(peaks2.get(1));
        }
//        peaks.size();
//        crop(origin,);
        // Scalr.crop(origin,60,origin.getHeight());

        if (peaks.size() != 0) {
            Peak p = peaks.get(0);
            int diff = p.getDiff();
            int pp = peaks2.size();
            int left = p.getLeft();
            return origin.getSubimage(p.getLeft(), 0, p.getDiff(), getImage().getHeight());
        }
        return origin;
    }


    private static Peak getBestVerticalPeak(List<Peak> peaks) {

        Comparator<Peak> comparator = Comparator.comparing(x -> x.getDiff());
        Comparator<Peak> comparator2 = Comparator.comparing(x -> x.getDiff() < 10);
        List<Peak> lista = peaks.stream()
                .filter(x -> x.getLeft() > 10)
                .collect(Collectors.toList());

        if (lista.isEmpty()) {
            return peaks.get(0);
        }
        if (lista.size() == 2) {
            return peaks.stream().min(comparator2).get();

        }
        return lista.stream().max(comparator).get();

    }

    private boolean validate(List<Peak> peaks) {
        return !peaks.isEmpty();

    }



    public PlateGraph histogram(BufferedImage bi) {
        PlateGraph graph = new PlateGraph(this);
        for (int x = 0; x < bi.getWidth(); x++) {
            float counter = 0;
            for (int y = 0; y < bi.getHeight(); y++) {
                counter += Photo.getPixel(bi, x, y);
            }
            graph.addPeak(counter);
        }
        return graph;
    }

    private PlateVerticalGraph histogramYaxis(BufferedImage bi) {
        BufferedImageOp imageOp;

        PlateVerticalGraph graph = new PlateVerticalGraph();
        int w = bi.getWidth();
        int h = bi.getHeight();
        for (int y = 0; y < h; y++) {
            float counter = 0;
            float counter2 = 0;
            for (int x = 0; x < w; x++) {
                counter += Photo.getPixel(bi, x, y);
                // counter += Photo.getPixel(bi,x,y);

            }
            graph.addPeak(counter);
        }
        return graph;
    }


    private PlateHorizontalGraph histogramXaxis(BufferedImage bi) {
        PlateHorizontalGraph graph = new PlateHorizontalGraph();
        int w = bi.getWidth();
        int h = bi.getHeight();
        for (int x = 0; x < w; x++) {
            float counter = 0;
            for (int y = 0; y < h; y++) {
                // counter += Photo.getPixel(bi,x,y);
                counter += Photo.getPixel(bi, x, y);
            }
            graph.addPeak(counter);
        }
        return graph;
    }

    @Override
    public void verticalEdgeDetector(BufferedImage source) {
        float[] matrix = {-1, 0, 1};
        BufferedImage destination = Photo.duplicateBufferedImage(source);
        new ConvolveOp(new Kernel(3, 1, matrix), ConvolveOp.EDGE_NO_OP, null).filter(destination, source);
    }

    public void horizontalEdgeDetector(BufferedImage source) {
        BufferedImage destination = Photo.duplicateBufferedImage(source);
        float[] matrix = {-1, -2, -1, 0, 0, 0, 1, 2, 1};
        new ConvolveOp(new Kernel(3, 3, matrix), ConvolveOp.EDGE_NO_OP, null).filter(destination, source);
    }

}
