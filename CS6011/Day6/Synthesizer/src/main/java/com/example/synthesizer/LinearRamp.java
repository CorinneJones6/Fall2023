package com.example.synthesizer;

public class LinearRamp implements AudioComponent{

   private float start_; //50?
   private float stop_; //2000?

    LinearRamp(float start, float stop){
      start_=start;
      stop_=stop;
    }
    @Override
    public AudioClip getClip() {
        AudioClip audioClip = new AudioClip();
        int numSamples= (int) audioClip.TOTAL_SAMPLES;
        for (int i=0; i<numSamples; i++) {
            double sampleValue = (start_ +( stop_-start_)  / numSamples* (i+1));
            audioClip.setSample(i, (int) (sampleValue+.5));
        }
        return audioClip;
    }

    @Override
    public boolean hasInput() {
        return false;
    }

    @Override
    public void connectInput(AudioComponent input) {

    }
    public void setBeginEnd(float start, float stop){
        start_=start;
        stop_=stop;
    }
}
