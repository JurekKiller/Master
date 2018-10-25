package sample;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class Chooser extends Application {

    private File image;

    @Override
    public void start(Stage mainStage) throws Exception {
        mainStage.setTitle("JavaFX App");

        FileChooser fileChooser = new FileChooser();
        setExtension(fileChooser);
        File image = fileChooser.showOpenDialog(mainStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void setExtension(FileChooser fileChooser) {
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
    }

    public File getImage() {
        return image;
    }
}