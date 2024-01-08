package com.example.synthesizer;

public class ReverbFilter implements AudioComponent{
    AudioComponent input_;
    double scale_;
    double delay_;


    ReverbFilter(){
        scale_=0.5f;
        delay_=100;
    }
    ReverbFilter(double scale, double delay){
        scale_=scale;
        delay_=delay;
    }
    @Override
    public AudioClip getClip() {
        AudioClip original= input_.getClip();
        AudioClip result = new AudioClip();

        for (int i=0; i<AudioClip.TOTAL_SAMPLES; i++) {
            int delayedIndex=(int)(i-delay_);

            if(delayedIndex>=0&&delayedIndex<AudioClip.TOTAL_SAMPLES){
                int sampleValue=(int)(original.getSample(i)+scale_*original.getSample(delayedIndex));
                result.setSample(i, sampleValue);
            }
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
