package com.example.synthesizer;

import java.util.ArrayList;

public class Mixer implements AudioComponent{

   ArrayList<AudioComponent> audioArray;

   Mixer(){
       audioArray = new ArrayList<>();
   }

   public void ArraySize(){
       System.out.println("size: " + audioArray.size());
   }

    @Override
    public AudioClip getClip() {
        AudioClip result = new AudioClip();

        for (int i = 0; i < AudioClip.TOTAL_SAMPLES; i++) {
            int Sample=0;
            for(AudioComponent audioComponents : audioArray) {
                Sample +=(audioComponents.getClip().getSample(i));
            }
            int max = Short.MAX_VALUE;
            int min = Short.MIN_VALUE;
            if (Sample < min) {
                Sample = min;
            } else if (Sample > max) {
                Sample = max;
            }
            result.setSample(i, Sample);
        }
        return result;
    }
    @Override
    public boolean hasInput() {
        return false;
    }

    @Override
    public void connectInput(AudioComponent input) {
        audioArray.add(input);
    }
}
