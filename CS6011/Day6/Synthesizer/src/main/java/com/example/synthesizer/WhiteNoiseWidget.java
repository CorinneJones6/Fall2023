package com.example.synthesizer;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;

public class WhiteNoiseWidget extends GeneratorWidgetBase{
    WhiteNoiseWidget(AudioComponent ac, AnchorPane parent) {
        super(ac, parent);
    }
    @Override
    void createView(){
        Label whiteLabel = new Label("White Noise");
        leftSide_.getChildren().add(whiteLabel);
    }
}
