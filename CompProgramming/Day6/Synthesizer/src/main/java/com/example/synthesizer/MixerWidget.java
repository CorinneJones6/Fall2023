package com.example.synthesizer;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;

public class MixerWidget extends MixerWidgetBase{
    MixerWidget(AudioComponent ac, AnchorPane parent){
        super(ac, parent);
    }
    @Override
    void createView(){
        Label mixerLabel = new Label("Mixer");
        leftSide_.getChildren().add(mixerLabel);
    }
}
