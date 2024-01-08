package com.example.synthesizer;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.nio.file.DirectoryStream;

public class HighPassFilterWidget extends FilterWidgetBase {
    HighPassFilterWidget(AudioComponent ac, AnchorPane parent) {
        super(ac, parent);
    }
    @Override
    void createView(){
        Label btnLabel = new Label("HighPassFilter");
        leftSide_.getChildren().add(btnLabel);
    }
}
