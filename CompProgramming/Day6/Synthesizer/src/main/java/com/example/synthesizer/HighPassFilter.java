package com.example.synthesizer;

public class HighPassFilter implements AudioComponent{
    AudioComponent input_;
    @Override
    public AudioClip getClip() {
        AudioClip original = input_.getClip();
        AudioClip result = new AudioClip();
        double smoothing = 0.1;
        int smoothedValue = original.getSample(0);

        for (int i = 0; i < AudioClip.TOTAL_SAMPLES; i++) {
            int newValue = original.getSample(i);

            // Apply high-pass filter to retain high-frequency components
            int highPassValue = newValue - smoothedValue;
            result.setSample(i, highPassValue);

            // Update the smoothed value for the next iteration
            smoothedValue = newValue;
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
