package com.example.synthesizer;
import java.util.Random;

public class WhiteNoise implements AudioComponent{
    boolean hasInput_=false;
    @Override
    public AudioClip getClip() {
        AudioClip audioclip = new AudioClip();
        Random random = new Random();
        int result;
        for (int i=0; i<AudioClip.TOTAL_SAMPLES; i++) {
            result = random.nextInt(Short.MAX_VALUE - Short.MIN_VALUE + 1) + Short.MIN_VALUE;
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
}
