package com.example.synthesizer;

public class LowPassFilter implements AudioComponent{
    AudioComponent input_;
    @Override
    public AudioClip getClip() {
        AudioClip original= input_.getClip();
        AudioClip result = new AudioClip();
        double smoothing =0.1;
        int smoothedValue=original.getSample(0);
        // If you have a fixed frame rate
        for (int i=0; i<AudioClip.TOTAL_SAMPLES; i++) {
            int newValue = original.getSample(i);

            smoothedValue += (int) ((newValue - smoothedValue) * smoothing);
            result.setSample(i, smoothedValue);
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
}
