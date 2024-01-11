package com.example.synthesizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

class AudioClipTest {

@Test
 void testAudioClip() {
   // Create an instance of the class that contains the getSample method
   AudioClip audioclip = new AudioClip();

  for (int i=Short.MIN_VALUE; i<Short.MAX_VALUE; i++){
   audioclip.setSample(10, i);
   Assertions.assertEquals(i, audioclip.getSample(10));
  }
}
@Test
void testSineWave(){


}
@Test
void testVolumeAdjuster(){

}
@Test
void testMixer(){


}
@Test
void testVFSineWave(){

}


}