package sample;

import org.opencv.core.Mat;

public class LicencePlateBuilder {

    private Mat image;
    private int identifier;

    public static class Builder {

        private Mat image;
        private int identifier;

        public Builder identifier(int identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder image(Mat image) {
            this.image = image;
            return this;
        }
    }

    private LicencePlateBuilder(Builder builder) {
        identifier = builder.identifier;
        image = builder.image;
    }
}