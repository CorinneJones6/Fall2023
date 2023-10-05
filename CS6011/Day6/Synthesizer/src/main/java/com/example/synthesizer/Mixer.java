package com.example.synthesizer;

import java.util.ArrayList;

public class Mixer implements AudioComponent{

    private  ArrayList<AudioComponent> audioArray;
    private boolean input_=false;

   Mixer(){
       audioArray = new ArrayList<>();
   }


   public void ArraySize(){
       System.out.println("size: " + audioArray.size());
   }

@Override
public AudioClip getClip() {
    AudioClip result = new AudioClip();
    //loop through array list of waves
    for(AudioComponent component: audioArray){
        AudioClip clip=component.getClip();
        //for each component loop through the component
        for (int i = 0; i < AudioClip.TOTAL_SAMPLES; i++) {
            //Add sample to results audio-clip (to add waves you add corresponding amplitudes)
            int Sample =(result.getSample(i) + clip.getSample(i));
            // Ensure the adjustedSample value stays within the valid range (clamp the sounds)
            int max = Short.MAX_VALUE;
            int min = Short.MIN_VALUE;

            // Set the adjusted sample value in the result clip
            if (Sample < min) {
                Sample=min;
            }
            else if (Sample > max) {
                Sample = max;
            }
            result.setSample(i, Sample);
        }
    }
    //return result;
    return result;
}
    @Override
    public boolean hasInput() {
       if (!audioArray.isEmpty()) {
           return true;
       }
       return false;
    }

    @Override
    public void connectInput(AudioComponent input) {
        audioArray.add(input);
    }
}
