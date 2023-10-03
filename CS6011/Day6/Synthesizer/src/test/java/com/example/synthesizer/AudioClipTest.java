package com.example.synthesizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AudioClipTest {

@Test
 void testGetSample() {
   // Create an instance of the class that contains the getSample method
   AudioClip audioclip = new AudioClip();

   audioclip.setSample(0, 1);
   audioclip.setSample(1, -2);
   audioclip.setSample(2, 3);
   audioclip.setSample(3, -4);

   // Assert that the actual result matches the expected result
   Assertions.assertEquals(audioclip.getSample(0), 1);
   Assertions.assertEquals(audioclip.getSample(1), -2);
   Assertions.assertEquals(audioclip.getSample(2), 3);
   Assertions.assertEquals(audioclip.getSample(3), -4);
}


}