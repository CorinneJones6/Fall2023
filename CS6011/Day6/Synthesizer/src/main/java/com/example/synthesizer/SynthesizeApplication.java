package com.example.synthesizer;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.util.ArrayList;

public class SynthesizeApplication extends Application {
    AnchorPane mainCenter;
    public static Circle speaker;
    public static ArrayList<AudioComponentWidgetBase> widgets_ = new ArrayList<>();
    public static ArrayList<AudioComponentWidgetBase> connectedWidgets_=new ArrayList<>();

    public void start(Stage stage) throws IOException {

        //notes from 10/5/23
        BorderPane mainLayout = new BorderPane();

        //Right Panel
        VBox rightpanel = new VBox();
        rightpanel.setStyle("-fx-background-color: #1a6271");
        rightpanel.setPadding(new Insets(4));
        Button sinewaveBtn=new Button("SineWave");
//        sinewaveBtn.setStyle("-fx-font-family: Georgia");
//        sinewaveBtn.setStyle("-fx-background-color: ");
        sinewaveBtn.setOnAction(e->createComponent(e));
        rightpanel.getChildren().add(sinewaveBtn);


        Button volumeBtn=new Button("Volume");
//        volumeBtn.setOnAction(e->createComponenet(e));
        rightpanel.getChildren().add(volumeBtn);


        //Center Panel
        mainCenter=new AnchorPane();
        mainCenter.setStyle("-fx-background-color: #9edcdc");
        speaker=new Circle(400, 200, 15);
        speaker.setFill(Color.valueOf("#946c84"));
        mainCenter.getChildren().add(speaker);


        //Adding the AnchorPane's to the layout
        mainLayout.setCenter(mainCenter);
        mainLayout.setRight(rightpanel);

        //Bottom Panel
        HBox bottomPanel=new HBox();
        bottomPanel.setStyle("-fx-background-color: #1a6271");
        bottomPanel.setAlignment(Pos.CENTER);
        Button playBtn=new Button("Play");
        playBtn.setOnAction(e->playAudio(e));
        bottomPanel.getChildren().add(playBtn);
        mainLayout.setBottom(bottomPanel);

        Scene scene = new Scene(mainLayout, 600, 400);
        stage.setTitle("Hello");
        stage.setScene(scene);
        stage.show();
    }

    private void playAudio(ActionEvent e) {
        try {
            Clip c = AudioSystem.getClip();
            AudioFormat format16 = new AudioFormat(44100, 16, 1, true, false);
            Mixer mixer =new Mixer();
            for (AudioComponentWidgetBase w: connectedWidgets_){
                AudioComponent ac=w.ac_;
                mixer.connectInput(ac);
            }
            AudioClip clip=mixer.getClip();
//            byte[] data = connectedWidgets.get(0).ac_.getClip().getData();
            c.open(format16, clip.getData(), 0, clip.getData().length);
            c.start();
            AudioListener listener = new AudioListener(c);
            c.addLineListener(listener);
        }
        catch (LineUnavailableException k){
            System.out.println(k.getMessage());
        }
    }

    private void createComponent(ActionEvent e) {

        AudioComponent sineWave=new SineWave(200);
        AudioComponentWidgetBase acw=new AudioComponentWidgetBase(sineWave, mainCenter);
        mainCenter.getChildren().add(acw);
        widgets_.add(acw);

    }


//        //Main Window
//        VBox freqBox = new VBox();
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.setStyle("-fx-padding: 10; -fx-background-color: #94c4cc");
//        freqBox.setAlignment(Pos.CENTER);
//        freqBox.setStyle("-fx-background-color: #f8d288");
//        freqBox.relocate(80, 80);
//
//        //Slider
//        Slider freqSlider = new Slider(0, 300, 30);
//        int result = (int)freqSlider.getValue();
//        Label frequencyLabel = new Label("Frequency: "+result+"Hz");
//        frequencyLabel.setStyle("-fx-font-family: Verdana");
//        frequencyLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 14));
//
//
//        //Play Button
//        VBox v1= new VBox();
//        Button playButton = new Button ("X");
//        playButton.setStyle("-fx-color: #0b6b7e");
//
//
//        //Adding the items to the visual
//        freqBox.getChildren().add(frequencyLabel);
//        freqBox.getChildren().add(freqSlider);
//        freqBox.getChildren().add(playButton);
//
//        mainLayout.getChildren().add(freqBox);
//
//
//
//        freqSlider.setOnMouseDragged(e->handleSlider(e, freqSlider, frequencyLabel, v1));
//        playButton.setOnAction(e-> {
//            try {
//                playSound(e, freqSlider);
//            } catch (LineUnavailableException ex) {
//                throw new RuntimeException(ex);
//            }
//        });
//
//
//        Scene scene = new Scene(mainLayout, 320, 240);
//        stage.setTitle("Synthesizer");
//
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    private void handleSlider(MouseEvent e, Slider freqSlider, Label frequencyLabel, VBox v1) {
//        int result = (int)freqSlider.getValue();
//        frequencyLabel.setText("Frequency: "+result+"Hz");
//        v1.relocate(0, 50);
//    }
//
//    private void playSound(ActionEvent e, Slider freqSlider) throws LineUnavailableException {
//
//        Clip c = AudioSystem.getClip();
//
//        AudioFormat format16 = new AudioFormat(44100, 16, 1, true, false);
//
//        double newValue = freqSlider.getValue();
//        AudioComponent gen = new SineWave(newValue); // Your code
//
//        AudioClip clip = gen.getClip();
//
//        c.open(format16, clip.getData(), 0, clip.getData().length);
//
//        c.start();
//        AudioListener listener = new AudioListener(c);
//        c.addLineListener(listener);

    public static void main(String[] args) {
        launch();
    }
}