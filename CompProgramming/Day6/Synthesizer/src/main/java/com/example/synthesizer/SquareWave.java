package com.example.synthesizer;

public class SquareWave implements AudioComponent{
    double frequency_;
    double sampleRate=AudioClip.rate_;
    boolean hasInput_=false;
    SquareWave(){
    frequency_=200;
    }
    public SquareWave(double frequency){
        frequency_=frequency;
    }
    @Override
    public AudioClip getClip() {
        AudioClip audioclip = new AudioClip();
        for (int i=0; i<AudioClip.TOTAL_SAMPLES; i++) {
            if ((frequency_ * i / sampleRate) % 1 > 0.5) {
                audioclip.setSample(i, Short.MAX_VALUE);
            } else {
                audioclip.setSample(i, Short.MIN_VALUE);
            }
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
