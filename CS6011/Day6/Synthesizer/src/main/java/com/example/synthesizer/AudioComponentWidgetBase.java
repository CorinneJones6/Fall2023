package com.example.synthesizer;

import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class AudioComponentWidgetBase extends Pane {

    public AudioComponent ac_;
    AnchorPane parent_;
    double mouseXPos_, mouseYPos_, widgetXPos_, widgetYPos_;
    Line line_;
    protected HBox baseLayout_;
    protected VBox rightSide_;
    protected VBox leftSide_;
    protected Circle signal_;
    protected boolean canConnect_;

    AudioComponentWidgetBase(AudioComponent ac, AnchorPane parent){
        ac_=ac;
        parent_=parent;
        canConnect_=false;

        //SineWave Main Box
        baseLayout_ =new HBox();
        baseLayout_.setStyle("-fx-background-color: #9edcdc");
        baseLayout_.setStyle("-fx-border-color: #946c84");

        //SineWave Right Side
        rightSide_ =new VBox();
        Button closeBtn=new Button("x");
        closeBtn.setOnAction(e->closeWidget(e));
        signal_ = new Circle(10);
        signal_.setFill(Color.valueOf("#946c84"));

        rightSide_.getChildren().add(closeBtn);
        rightSide_.getChildren().add(signal_);

        rightSide_.setStyle("-fx-background-color: #ebe3e5");
        rightSide_.setAlignment(Pos.CENTER);
        rightSide_.setPadding(new Insets(5));
        rightSide_.setSpacing(5);

        //SineWave Left Side Creation
        leftSide_ = new VBox();
        leftSide_.setStyle("-fx-background-color: #ebe3e5");

        leftSide_.setOnMousePressed(e->getPosInf(e));
        leftSide_.setOnMouseDragged(e->moveWidget(e));

        baseLayout_.getChildren().add(leftSide_); //to check it later on
        baseLayout_.getChildren().add(rightSide_);
        this.getChildren().add(baseLayout_);

        this.setLayoutX(50);
        this.setLayoutY(50);

        //Handle Drawing the Line
        signal_.setOnMousePressed(e->startConn(e, signal_));
        signal_.setOnMouseDragged(e->movConn(e, signal_));
        signal_.setOnMouseReleased(e->endConn(e, signal_));
    }
    void createView(){

    }
    public boolean canConnect(){
        return false;
    }
    private void moveWidget(MouseEvent e) {
        //delx means the changes in the x position
        double delX = e.getSceneX()- mouseXPos_;
        double delY=e.getSceneY()- mouseYPos_;

        this.relocate(delX+ widgetXPos_, delY+ widgetYPos_);
//        this.relocate(e.getSceneX(), e.getSceneY());
    }
    private void getPosInf(MouseEvent e) {
        mouseXPos_ =e.getSceneX();
        mouseYPos_ =e.getSceneY();
        widgetXPos_ =this.getLayoutX();
        widgetYPos_ =this.getLayoutY();
    }

    private void closeWidget(ActionEvent e) {
        parent_.getChildren().remove(this);
        SynthesizeApplication.widgets_.remove(this);
        SynthesizeApplication.connectedWidgets_.remove(this);
//        SynthesizeApplication.widgets_.remove(this.ac_);
//        SynthesizeApplication.connectedWidgets_.remove(this.ac_);
        parent_.getChildren().remove(line_);
    }
    public AudioComponent getAudioComponent(){
        return ac_;
    }
    private void endConn(MouseEvent e, Circle output) {
        Circle speaker_ = SynthesizeApplication.speaker;
        Bounds speakerBounds = speaker_.localToScene(speaker_.getBoundsInLocal());
        double distance=Math.sqrt(Math.pow(speakerBounds.getCenterX()-e.getSceneX(), 2.0)+Math.pow(speakerBounds.getCenterY()-e.getSceneY(), 2.0));

            AudioComponentWidgetBase widget = SynthesizeApplication.findClosestConnectable(e);

        if (widget != null){
//            SynthesizeApplication.connectedWidgets_.add(this);
            widget.ac_.connectInput(this.ac_);

        }
        else if (distance<15){
//            SynthesizeApplication.connectedWidgets_.add(this);
            SynthesizeApplication.connectToSpeaker(this.ac_);
        }
        else{
            parent_.getChildren().remove(line_);
            line_=null;
        }
    }
    private void movConn(MouseEvent e, Circle output) {
        Bounds parentBounds=parent_.getBoundsInParent();
        line_.setEndX(e.getSceneX()- parentBounds.getMinX());
        line_.setEndY(e.getSceneY()-parentBounds.getMinY());
    }
    private void startConn(MouseEvent e, Circle output) {
        if (line_!=null){
            parent_.getChildren().remove(line_);
        }

        Bounds parentBounds=parent_.getBoundsInParent();
        Bounds bounds=output.localToScene(output.getBoundsInLocal());

        line_=new Line();
        line_.setStrokeWidth(3);
        line_.setStroke(Color.valueOf("946c84"));

        line_.setStartX(bounds.getCenterX()-parentBounds.getMinX());
        line_.setStartY(bounds.getCenterY()-parentBounds.getMinY());

        line_.setEndX(e.getSceneX());
        line_.setEndY(e.getSceneY());

        parent_.getChildren().add(line_);
    }

   public Bounds getCirclBounds(){
        return signal_.localToScene(signal_.getBoundsInLocal());
    }
}
