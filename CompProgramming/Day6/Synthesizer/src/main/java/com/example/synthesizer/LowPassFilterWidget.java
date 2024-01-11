package com.example.synthesizer;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class LowPassFilterWidget extends FilterWidgetBase{

    LowPassFilterWidget(AudioComponent ac, AnchorPane parent) {
        super(ac, parent);
    }
    @Override
    void createView(){
        Label btnLabel = new Label("LowPassFilter");
        leftSide_.getChildren().add(btnLabel);
    }
}
