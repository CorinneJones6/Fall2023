package com.example.synthesizer;
import com.example.synthesizer.AudioClip;

public interface AudioComponent {
    AudioClip getClip();
    boolean hasInput();
    void connectInput(AudioComponent input);
}
