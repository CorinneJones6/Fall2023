package com.example.synthesizer;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class VolumeWidget extends AudioComponentWidgetBase{
    VolumeWidget(AudioComponent ac, AnchorPane parent){
        super(ac, parent);
    }
    @Override
    void createView(){
        Label btnLabel = new Label("Volume 5dB");
        Slider btnSlider = new Slider(0, 10, 5);
        leftSide_.getChildren().add(btnLabel);
        leftSide_.getChildren().add(btnSlider);
        btnSlider.setOnMouseDragged(e->setIntensity(btnSlider, btnLabel));
    }
    private void setIntensity(Slider btnSlider, Label btnLabel) {
        ((VolumeFilter)ac_).setIntensity((int)(btnSlider.getValue()));
        int val = (int)btnSlider.getValue();
        btnLabel.setText("Volume "+val+"dB");
    }
}
