package com.example.synthesizer;

public class VFSineWave implements AudioComponent{

    private double phase_=0;
    private AudioComponent input_;
    private Boolean hasInput_ =false;
    @Override
    public AudioClip getClip() {
        int maxValue=Short.MAX_VALUE;
        AudioClip audioClip = new AudioClip();

        AudioClip inputClip=input_.getClip();

        int numSamples= (int) audioClip.TOTAL_SAMPLES;
        for (int i=0; i<numSamples; i++) {
            phase_ += 2 * Math.PI * inputClip.getSample(i)/AudioClip.rate_;
            int sampleValue=(int)(maxValue*Math.sin(phase_)+.5);
            audioClip.setSample(i, sampleValue);
        }
        return audioClip;
    }

    @Override
    public boolean hasInput() {
        if (input_==null) {
            return false;
        }
        return true;
    }
    @Override
    public void connectInput(AudioComponent input) {
        input_=input;
    }
}
