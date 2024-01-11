package lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiffUtilTest {
    private int[] arr1, arr2, arr3, arr4, arr5, arr6, arr7;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        arr1 = new int[0];
        arr2 = new int[] { 3, 3, 3 };
        arr3 = new int[] { 52, 4, -8, 0, -17 };
        arr4 = new int[] {-91, -13, -81, -73, -96}; //should be 5, all negative
        arr5 = new int[] {5}; //should be -1
        arr6 = new int [10000]; //ascending order to 10,000
        for(int i = 0; i < 10000; i++) {
            arr6[i] = i+1;
        }
        arr7 = new int [10000]; //descending order from 10,000
        for (int i=9999; i>0; i--){
            arr7[i]=i+1;
        }



    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        arr1 = null;
        arr2 = null;
        arr3 = null;
        arr4 = null;
        arr5 = null;
        arr6 = null;
    }

    @org.junit.jupiter.api.Test
    void findSmallestDiff() {
    }
    @Test
    public void emptyArray() {
        assertEquals(-1, DiffUtil.findSmallestDiff(arr1));
    }
    @Test
    public void allArrayElementsEqual() {
        assertEquals(0, DiffUtil.findSmallestDiff(arr2));
    }
    @Test
    public void smallRandomArrayElements() {
        assertEquals(4, DiffUtil.findSmallestDiff(arr3));
    }
    @Test
    public void allNegativeArrayElements(){
        assertEquals(5, DiffUtil.findSmallestDiff(arr4));
    }
    @Test
    public void oneNumArray(){
        assertEquals(-1, DiffUtil.findSmallestDiff(arr5));
    }
    @Test
    public void largeAscendingOrderArray(){
        assertEquals(1,DiffUtil.findSmallestDiff(arr6));
    }
    @Test
    public void largeDescendingOrderArray(){
        assertEquals(1, DiffUtil.findSmallestDiff(arr7));
    }
}