package com.example.synthesizer;
import javax.sound.sampled.*;
import java.io.IOException;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) throws IOException, LineUnavailableException {

    // Get properties from the system about samples rates, etc.
// AudioSystem is a class from the Java standard library.
    Clip c = AudioSystem.getClip(); // Note, this is different from our AudioClip class.

    // This is the format that we're following, 44.1 KHz mono audio, 16 bits per sample.
    AudioFormat format16 = new AudioFormat( 44100, 16, 1, true, false );


//        //This tests a sinewave
//        AudioComponent gen = new SineWave(200);
//        AudioClip clip = gen.getClip();

//        //This tests a squarewave
//        AudioComponent gen = new SquareWave(200);
//        AudioClip clip = gen.getClip();

//        //This tests whitenoise
//        AudioComponent gen = new WhiteNoise();
//        AudioClip clip = gen.getClip();

//        //This tests a trianglewave
//        AudioComponent gen = new TriangleWave(400);
//        AudioClip clip = gen.getClip();



//        //This tests a mixer
//        AudioComponent gen = new Mixer();
//        AudioComponent sinewave1=new SineWave(220);
//        AudioComponent sinewave2=new SineWave(440);
//        gen.connectInput(sinewave1);
//        gen.connectInput(sinewave2);
//        AudioClip clip = gen.getClip();


////This tests the linearRamp and frequency wave
//        AudioComponent linearRamp = new LinearRamp(50, 5000);
//        AudioComponent frequencyWave= new VFSineWave();
//        frequencyWave.connectInput(linearRamp);
//        AudioClip clip=frequencyWave.getClip();


        //This tests the volume filter
        AudioComponent gen = new SineWave(220);
        VolumeFilter changeVolume=new VolumeFilter(10);
        changeVolume.connectInput(gen);
        AudioClip clip=changeVolume.getClip();

//        //This tests the reverb filter
//        AudioComponent gen = new SineWave(220);
//        ReverbFilter changeReverb=new ReverbFilter();
//        changeReverb.connectInput(gen);
//        AudioClip clip=changeReverb.getClip();

//        //this tests the low pass filter
//        AudioComponent gen = new WhiteNoise();
//        LowPassFilter changeLowPass=new LowPassFilter();
//        changeLowPass.connectInput(gen);
//        AudioClip clip = changeLowPass.getClip();

//        AudioComponent gen = new WhiteNoise();
//        AudioClip clip = gen.getClip();

//        //this tests the high pass filter
//        AudioComponent gen = new WhiteNoise();
//        HighPassFilter changeHighPass=new HighPassFilter();
//        changeHighPass.connectInput(gen);
//        AudioClip clip =changeHighPass.getClip();




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
