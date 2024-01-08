package com.example.synthesizer;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

import static javax.swing.text.StyleConstants.Alignment;

public class SynthesizeApplication extends Application {
    AnchorPane mainCenter;
    public static Circle speaker;
    private static Mixer mixer;
    public static ArrayList<AudioComponentWidgetBase> widgets_ = new ArrayList<>();
    public static ArrayList<AudioComponentWidgetBase> connectedWidgets_=new ArrayList<>();

    public void start(Stage stage) throws IOException {
        mixer=new Mixer();
        //notes from 10/5/23
        BorderPane mainLayout = new BorderPane();

        //Right Panel
        VBox rightpanel = new VBox();

        //Right Panel Generator Widget Buttons
        Button generatorBtn=new Button("::Generators::");
        Button sinewaveBtn=new Button("SineWave");
        Button whitenoiseBtn=new Button("WhiteNoise");
        Button squarewaveBtn=new Button("SquareWave");
        Button trianglewaveBtn=new Button("TriangleWave");

        //Right Panel Filter Widget Buttons
        Button filterBtn=new Button("::Filters::");
        Button volumeBtn=new Button("Volume");
        Button reverbBtn=new Button ("Reverb");
        Button lowPassBtn=new Button("LowPass");
        Button highPassBtn=new Button("HighPass");

        //Right Panel Mixer Widget Buttons
        Button mixerBtn=new Button("::Mixers::");
        Button combineBtn=new Button("Mixer");

        // Set a common preferred width for all buttons
         double buttonWidth = 95;
         generatorBtn.setPrefWidth(buttonWidth);
         sinewaveBtn.setPrefWidth(buttonWidth);
         whitenoiseBtn.setPrefWidth(buttonWidth);
         squarewaveBtn.setPrefWidth(buttonWidth);
         trianglewaveBtn.setPrefWidth(buttonWidth);

         filterBtn.setPrefWidth(buttonWidth);
         volumeBtn.setPrefWidth(buttonWidth);
         reverbBtn.setPrefWidth(buttonWidth);
         lowPassBtn.setPrefWidth(buttonWidth);
         highPassBtn.setPrefWidth(buttonWidth);

         mixerBtn.setPrefWidth(buttonWidth);
         combineBtn.setPrefWidth(buttonWidth);

        //Generator Widget Actions
        sinewaveBtn.setOnAction(this::createSineWaveComponent);
        whitenoiseBtn.setOnAction(this::createWhiteNoiseComponent);
        squarewaveBtn.setOnAction(this::createSquareWaveComponent);
        trianglewaveBtn.setOnAction(this::createTriangleWaveComponent);

        //Filter Widget Actions
        volumeBtn.setOnAction(this::createVolumeComponent);
        reverbBtn.setOnAction(this::createReverbComponent);
        lowPassBtn.setOnAction(this::createLowPassComponent);
        highPassBtn.setOnAction(this::createHighPassComponent);
        //Mixer Widget Actions
        combineBtn.setOnAction(this::createMixerComponent);

        rightpanel.getChildren().add(generatorBtn);
        rightpanel.getChildren().add(sinewaveBtn);
        rightpanel.getChildren().add(whitenoiseBtn);
        rightpanel.getChildren().add(squarewaveBtn);
        rightpanel.getChildren().add(trianglewaveBtn);
        rightpanel.getChildren().add(filterBtn);
        rightpanel.getChildren().add(volumeBtn);
        rightpanel.getChildren().add(reverbBtn);
        rightpanel.getChildren().add(lowPassBtn);
        rightpanel.getChildren().add(highPassBtn);
        rightpanel.getChildren().add(mixerBtn);
        rightpanel.getChildren().add(combineBtn);

        //Set Style for the labels
        generatorBtn.setStyle("-fx-background-color: #47a8a9");
        sinewaveBtn.setStyle("-fx-background-color: #47a8a9");
        whitenoiseBtn.setStyle("-fx-background-color: #47a8a9");
        squarewaveBtn.setStyle("-fx-background-color: #47a8a9");
        trianglewaveBtn.setStyle("-fx-background-color: #47a8a9");

        filterBtn.setStyle("-fx-background-color: #47a8a9");
        volumeBtn.setStyle("-fx-background-color: #47a8a9");
        reverbBtn.setStyle("-fx-background-color: #47a8a9");
        lowPassBtn.setStyle("-fx-background-color: #47a8a9");
        highPassBtn.setStyle("-fx-background-color: #47a8a9");

        mixerBtn.setStyle("-fx-background-color: #47a8a9");
        combineBtn.setStyle("-fx-background-color: #47a8a9");

        //right panel setting the style
        rightpanel.setStyle("-fx-font-family: Georgia");
        rightpanel.setStyle("-fx-background-color: #1a6271");
        rightpanel.setPadding(new Insets(4));
        rightpanel.setSpacing(5);
        rightpanel.setAlignment(Pos.CENTER);

        //Center Panel + Speaker
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

        //Bottom Panel Buttons
        Button playBtn=new Button("Play");
        playBtn.setStyle("-fx-font-family: Georgia");
        playBtn.setOnAction(e->playAudio(e));
        playBtn.setPrefHeight(40);
//        playBtn.setStyle("-fx-font-size: 18px");
        playBtn.setStyle("-fx-background-color: #47a8a9");
        bottomPanel.getChildren().add(playBtn);
        mainLayout.setBottom(bottomPanel);

        Scene scene = new Scene(mainLayout, 600, 400);
        stage.setTitle("Synthesizer");
        stage.setScene(scene);
        stage.show();
    }

    private void playAudio(ActionEvent e) {
        try {
            Clip c = AudioSystem.getClip();
            AudioFormat format16 = new AudioFormat(44100, 16, 1, true, false);
//            Mixer mixer =new Mixer();
//            for (AudioComponentWidgetBase w: connectedWidgets_){
//                AudioComponent ac=w.ac_;
//                mixer.connectInput(ac);
//            }
            AudioClip clip=mixer.getClip();
            c.open(format16, clip.getData(), 0, clip.getData().length);
            c.start();
            AudioListener listener = new AudioListener(c);
            c.addLineListener(listener);
        }
        catch (LineUnavailableException k){
            System.out.println(k.getMessage());
        }
    }
    private void createSineWaveComponent(ActionEvent e) {
        AudioComponent sineWave=new SineWave(300);
        SineWaveWidget acw=new SineWaveWidget(sineWave, mainCenter);
        acw.createView();
        mainCenter.getChildren().add(acw);
        widgets_.add(acw);
    }
    private void createWhiteNoiseComponent(ActionEvent e) {
        AudioComponent whiteNoise=new WhiteNoise();
        WhiteNoiseWidget acw=new WhiteNoiseWidget(whiteNoise, mainCenter);
        acw.createView();
        mainCenter.getChildren().add(acw);
        widgets_.add(acw);
    }
    private void createSquareWaveComponent(ActionEvent e){
        AudioComponent squareWave=new SquareWave();
        SquareWaveWidget acw = new SquareWaveWidget(squareWave, mainCenter);
        acw.createView();
        mainCenter.getChildren().add(acw);
    }
    private void createTriangleWaveComponent(ActionEvent e){
        AudioComponent triangleWave=new TriangleWave();
        TriangleWaveWidget acw = new TriangleWaveWidget(triangleWave, mainCenter);
        acw.createView();
        mainCenter.getChildren().add(acw);
    }
    private void createVolumeComponent(ActionEvent e) {
        AudioComponent volumeAdjuster=new VolumeFilter(100);
        VolumeWidget acw=new VolumeWidget(volumeAdjuster, mainCenter);
        acw.createView();
        mainCenter.getChildren().add(acw);
        widgets_.add(acw);
    }
    private void createReverbComponent(ActionEvent e) {
        AudioComponent reverbAdjuster = new ReverbFilter();
        ReverbFilterWidget acw = new ReverbFilterWidget(reverbAdjuster, mainCenter);
        acw.createView();
        mainCenter.getChildren().add(acw);
        widgets_.add(acw);
    }
    private void createLowPassComponent(ActionEvent e) {
        AudioComponent lowPassAdjuster = new LowPassFilter();
        LowPassFilterWidget acw = new LowPassFilterWidget(lowPassAdjuster, mainCenter);
        acw.createView();
        mainCenter.getChildren().add(acw);
        widgets_.add(acw);
    }

    private void createHighPassComponent(ActionEvent e){
        AudioComponent highPassAdjuster = new HighPassFilter();
        HighPassFilterWidget acw = new HighPassFilterWidget(highPassAdjuster, mainCenter);
        acw.createView();
        mainCenter.getChildren().add(acw);
        widgets_.add(acw);
    }

    private void createMixerComponent(ActionEvent actionEvent) {
        AudioComponent combineNoise=new Mixer();
        MixerWidget acw=new MixerWidget(combineNoise, mainCenter);
        acw.createView();
        mainCenter.getChildren().add(acw);
        widgets_.add(acw);
    }

    public static AudioComponentWidgetBase findClosestConnectable(MouseEvent e){
        for(AudioComponentWidgetBase acw:widgets_){
            Bounds speakerBounds=acw.getCirclBounds();
            double distance=Math.sqrt(Math.pow(speakerBounds.getCenterX()-e.getSceneX(), 2.0)+Math.pow(speakerBounds.getCenterY()-e.getSceneY(), 2.0));
            if (distance <15){
                return acw;
            }
        }
        return null;
    }

    public static void connectToSpeaker(AudioComponent ac){
        mixer.connectInput(ac);
    }
    public static void main(String[] args) {
        launch();
    }
}