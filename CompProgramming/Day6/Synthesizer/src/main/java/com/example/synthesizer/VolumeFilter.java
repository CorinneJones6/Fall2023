package com.example.synthesizer;

public class VolumeFilter implements AudioComponent {

    double scale_;
    AudioComponent input_;
    VolumeFilter(double scale){
        scale_=scale;
    }
    @Override
    public AudioClip getClip() {
        AudioClip original= input_.getClip();
        AudioClip result = new AudioClip();

        for (int i=0; i<AudioClip.TOTAL_SAMPLES; i++) {
            int AdjustedSample=(int)(scale_*original.getSample(i));
            int max=Short.MAX_VALUE;
            int min=Short.MIN_VALUE;

            if (AdjustedSample>max){
                AdjustedSample=max;
            }
            else if (AdjustedSample<min){
                AdjustedSample=min;
            }
            result.setSample(i, AdjustedSample);
        }
        return result;
    }
    @Override
    public boolean hasInput() {

        return input_!=null;
    }
    @Override
    public void connectInput(AudioComponent input) {
        input_=input;
    }
    public void setIntensity(int scale){
        scale_=scale;
    }
}
