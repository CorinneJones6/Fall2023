package com.example.synthesizer;

public class TriangleWave implements AudioComponent{
    double frequency_;
    double sampleRate=AudioClip.rate_;
    boolean hasInput_=false;
    TriangleWave(){
        frequency_=200;
    }
    TriangleWave(double frequency){
        frequency_=frequency;
    }
    @Override
    public AudioClip getClip() {
        AudioClip audioclip = new AudioClip();
        int result;
        for (int i=0; i<AudioClip.TOTAL_SAMPLES; i++) {
            result=(int) (2*(i%(sampleRate/frequency_))/(sampleRate/frequency_)-1);
            audioclip.setSample(i, result);
        }
        return audioclip;
    }

    @Override
    public boolean hasInput() {
        return hasInput_;
    }

    @Override
    public void connectInput(AudioComponent input) {

    }
    public void setFrequency(int frequency){
        frequency_=frequency;
    }
}
