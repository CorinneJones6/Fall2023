package com.example.synthesizer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Slider;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class SynthesizeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //Main Window
        VBox freqBox = new VBox();
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.setStyle("-fx-padding: 10; -fx-background-color: #94c4cc");
        freqBox.setAlignment(Pos.CENTER);
        freqBox.setStyle("-fx-background-color: #f8d288");
        freqBox.relocate(80, 80);

        //Slider
        Slider freqSlider = new Slider(0, 300, 30);
        int result = (int)freqSlider.getValue();
        Label frequencyLabel = new Label("Frequency: "+result+"Hz");
        frequencyLabel.setStyle("-fx-font-family: Verdana");
        frequencyLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 14));


        //Play Button
        VBox v1= new VBox();
        Button playButton = new Button ("X");
        playButton.setStyle("-fx-color: #0b6b7e");


        //Adding the items to the visual
        freqBox.getChildren().add(frequencyLabel);
        freqBox.getChildren().add(freqSlider);
        freqBox.getChildren().add(playButton);

        mainLayout.getChildren().add(freqBox);



        freqSlider.setOnMouseDragged(e->handleSlider(e, freqSlider, frequencyLabel, v1));
        playButton.setOnAction(e-> {
            try {
                playSound(e, freqSlider);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
        });


        Scene scene = new Scene(mainLayout, 320, 240);
        stage.setTitle("Synthesizer");

        stage.setScene(scene);
        stage.show();
    }

    private void handleSlider(MouseEvent e, Slider freqSlider, Label frequencyLabel, VBox v1) {
        int result = (int)freqSlider.getValue();
        frequencyLabel.setText("Frequency: "+result+"Hz");
        v1.relocate(0, 50);
    }

    private void playSound(ActionEvent e, Slider freqSlider) throws LineUnavailableException {

        Clip c = AudioSystem.getClip();

        AudioFormat format16 = new AudioFormat(44100, 16, 1, true, false);

        double newValue = freqSlider.getValue();
        AudioComponent gen = new SineWave(newValue); // Your code

        AudioClip clip = gen.getClip();

        c.open(format16, clip.getData(), 0, clip.getData().length);

        c.start();
        AudioListener listener = new AudioListener(c);
        c.addLineListener(listener);
    }

    public static void main(String[] args) {
        launch();
    }
}