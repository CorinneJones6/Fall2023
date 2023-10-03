package com.example.synthesizer;
import com.example.synthesizer.AudioClip;

public class SineWave implements AudioComponent {

    double frequency_;
    double sampleRate=44100;
    boolean hasInput;

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
            result=(int) (Short.MAX_VALUE * Math.sin(2 * Math.PI * frequency_ * i / sampleRate));
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
}
