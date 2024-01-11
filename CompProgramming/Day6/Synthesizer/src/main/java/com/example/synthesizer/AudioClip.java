package com.example.synthesizer;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class AudioClip {

    static final double duration_=2.0;
    static final int rate_ = 44100;
    public static final double TOTAL_SAMPLES=duration_*rate_;

    byte[] data_ = new byte[(int) (rate_*duration_*2)]; // Each sample is of 2 bytes // 44100 samples per second (Data rate for CDs.)

    int getSample (int index){
        byte byte1=data_[index*2];
        byte byte2=data_[index*2+1];
        return (byte2<<8) | (byte1 & 0xFF);
    }
    void setSample (int index, int value){
        byte byte1=(byte)(value>>8);
        byte byte2=(byte)(value &0xFF);
        data_[(index*2)+1]=byte1;
        data_[index*2]=byte2;
    }
    byte[] getData (){
        byte[]newArray=Arrays.copyOf(data_, data_.length);
        return newArray;
    }

}
