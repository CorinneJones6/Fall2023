package com.example.synthesizer;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;

public class ReverbFilterWidget extends FilterWidgetBase{
    ReverbFilterWidget(AudioComponent ac, AnchorPane parent) {
        super(ac, parent);
    }
    @Override
    void createView(){
        Label btnLabel = new Label("ReverbFilter");
        leftSide_.getChildren().add(btnLabel);
    }
}
