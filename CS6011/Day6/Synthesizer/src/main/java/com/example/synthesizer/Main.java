package com.example.synthesizer;
import javax.sound.sampled.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, LineUnavailableException {

    // Get properties from the system about samples rates, etc.
// AudioSystem is a class from the Java standard library.
    Clip c = AudioSystem.getClip(); // Note, this is different from our AudioClip class.

    // This is the format that we're following, 44.1 KHz mono audio, 16 bits per sample.
    AudioFormat format16 = new AudioFormat( 44100, 16, 1, true, false );

//    AudioComponent gen = new SineWave(220); // Your code

        AudioComponent gen = new Mixer();

//        Mixer mixer = new Mixer();
        AudioComponent sinewave1=new SineWave(220.5);
        AudioComponent sinewave2=new SineWave(138.5);
        AudioComponent sinewave3 = new SineWave(164.5);

        gen.connectInput(sinewave1);
        gen.connectInput(sinewave2);
        gen.connectInput(sinewave3);

        AudioComponent linearRamp = new LinearRamp(50, 5000);
//        AudioComponent frequencyWave= new VFSineWave();
//
//        frequencyWave.connectInput(linearRamp);



//     VolumeAdjuster changeVolume=new VolumeAdjuster(.5);
//     changeVolume.connectInput(gen);

//    AudioClip clip = gen.getClip();         // Your code
//     AudioClip clip=changeVolume.getClip();
//        AudioClip clip=frequencyWave.getClip();
        AudioClip clip = linearRamp.getClip();

c.open( format16, clip.getData(), 0, clip.getData().length ); // Reads data from our byte array to play it.

System.out.println( "About to play..." );
c.start(); // Plays it.
c.loop( 0); // Plays it 2 more times if desired, so 6 seconds total

// Makes sure the program doesn't quit before the sound plays.
while( c.getFramePosition() < AudioClip.TOTAL_SAMPLES || c.isActive() || c.isRunning() ){
        // Do nothing while we wait for the note to play.
    }

System.out.println( "Done." );
c.close();
}
}
