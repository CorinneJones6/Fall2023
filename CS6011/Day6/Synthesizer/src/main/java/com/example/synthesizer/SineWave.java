package com.example.synthesizer;
import com.example.synthesizer.AudioClip;

public class SineWave implements AudioComponent {

    double frequency_;
    //todo: just use the samplerate from audioclip
    double sampleRate=44100;
    boolean hasInput=false;

    public SineWave(){

    }
    public SineWave(double frequency){
        frequency_=frequency;
    }
    @Override
    public AudioClip getClip(){
        AudioClip audioclip = new AudioClip();
        int result;
        for (int i=0; i<AudioClip.TOTAL_SAMPLES; i++) {
            result=(int) (Short.MAX_VALUE * Math.sin(2 * Math.PI * frequency_ * i / sampleRate)+.5);
            audioclip.setSample(i, result);
        }
        return audioclip;
    }
    @Override
   public boolean hasInput(){
        return hasInput;
    }
    @Override
    public void connectInput(AudioComponent input){
        //if (my array has input), set has input to true.

    }
    public void setFrequency(int frequency){
        frequency_=frequency;
    }
}
