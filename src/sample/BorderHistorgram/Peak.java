

package sample.BorderHistorgram;

public class Peak {
    private int left, center, right;

    public Peak(int left, int center, int right) {
        this.left = left;
        this.center = center;
        this.right = right;
    }

    public Peak(int left, int right) {
        this.left = left;
        center = (left + right) / 2;
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getCenter() {
        return center;
    }

    public void setCenter(int center) {
        this.center = center;
    }

    public int getDiff() {
        return right - left;
    }
}
