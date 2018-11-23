package sample;

import org.opencv.core.Mat;

public class LicencePlateBuilder {

    private Mat image;
    private int identifier;

    private LicencePlateBuilder(final Builder builder) {
        this.image = builder.image;
        this.identifier = builder.identifier;
    }


    public static class Builder {

        private Mat image;
        private int identifier;


        public Builder setIdentifier(int identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder setImage(Mat image) {
            this.image = image;
            return this;
        }

        public LicencePlateBuilder build() {
            return new LicencePlateBuilder(this);
        }

    }

//    private LicencePlateBuilder(Builder builder) {
//        identifier = builder.identifier;
//        image = builder.image;
//    }




}