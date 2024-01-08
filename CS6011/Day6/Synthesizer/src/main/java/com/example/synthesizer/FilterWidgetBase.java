package com.example.synthesizer;

import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class FilterWidgetBase extends AudioComponentWidgetBase{
    FilterWidgetBase(AudioComponent ac, AnchorPane parent){
        super(ac, parent);
        //Handle Drawing the Line

    }

//    @Override
//    boolean canConnect(){
//        if (!GeneratorWidgetBase) {
//            return true;
//        } else return false;
//    }
}
