package assign01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrayscaleImageTest {

    private GrayscaleImage smallSquare;
    private GrayscaleImage smallWide;
    private GrayscaleImage veryLarge; // A very large image
    private GrayscaleImage allBlackImage; // An all-black image
    private GrayscaleImage allWhiteImage; // An all-white image
    private GrayscaleImage singlePixel; //A striped square to test mirroring


    @BeforeEach
    void setUp() {
        smallSquare = new GrayscaleImage(new double[][]{
                {1, 2},
                {3, 4}});
        smallWide = new GrayscaleImage(new double[][]{{1, 2, 3}, {4, 5, 6}});

        // Create additional test images
        veryLarge = new GrayscaleImage(new double[1000][1000]); // A very large image with dimensions 1000x1000
        allBlackImage = new GrayscaleImage(new double[5][5]); // An all-black image
        allWhiteImage = new GrayscaleImage(new double[5][5]); // An all-white image
        singlePixel = new GrayscaleImage(new double [][]{{255}}); //Should be the same around y axis when mirrored

        // Initialize the all-white image with white pixels (255) and the all-black image with black pixels (0)
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                allWhiteImage.setPixel(row, col, 255);
                allBlackImage.setPixel(row, col, 0);
            }
        }
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                veryLarge.setPixel(i, j, i);
            }
        }
    }


    @Test
    void testGetPixel(){
        assertEquals(smallSquare.getPixel(0,0), 1);
        assertEquals(smallSquare.getPixel(1,0), 2);
        assertEquals(smallSquare.getPixel(0,1), 3);
        assertEquals(smallSquare.getPixel(1,1), 4);
        //Added the below tests
        assertEquals(veryLarge.getPixel(888,888), 888);
        assertEquals(allBlackImage.getPixel(4,4),0);
        assertEquals(allWhiteImage.getPixel(3, 3), 255);
    }

    @Test
    void testEquals() {
        assertEquals(smallSquare, smallSquare);
        var equivalent = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        assertEquals(smallSquare, equivalent);
    }
    @Test
    void testNotEqual(){
        //Originally this crashed since wasn't checking for the same length
        assertNotEquals(veryLarge, smallSquare);
        assertNotEquals(allBlackImage, allWhiteImage);
    }
    @Test
    void testAreEqual(){
        GrayscaleImage veryLarge2 = new GrayscaleImage(new double[1000][1000]);
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                veryLarge2.setPixel(i, j, i);
            }
        }
        assertEquals(allBlackImage, allBlackImage);
        assertEquals(veryLarge, veryLarge2);
    }
    @Test
    void averageBrightness() {
        assertEquals(smallSquare.averageBrightness(), 2.5, 2.5*.001);
        var bigZero = new GrayscaleImage(new double[1000][1000]);
        assertEquals(bigZero.averageBrightness(), 0);
        //added the two tests below
        assertEquals(allBlackImage.averageBrightness(), 0);
        assertEquals(allWhiteImage.averageBrightness(), 255);
        assertEquals(veryLarge.averageBrightness(), 499.5);
    }

    @Test
    void normalized() {
        var smallNorm = smallSquare.normalized();
        assertEquals(smallNorm.averageBrightness(), 127, 127*.001);
        var scale = 127/2.5;
        var expectedNorm = new GrayscaleImage(new double[][]{{scale, 2*scale},{3*scale, 4*scale}});
        for(var row = 0; row < 2; row++){
            for(var col = 0; col < 2; col++){
                assertEquals(smallNorm.getPixel(col, row), expectedNorm.getPixel(col, row),
                        expectedNorm.getPixel(col, row)*.001,
                        "pixel at row: " + row + " col: " + col + " incorrect");
            }
        }
        //added the following below
        var allWhite=allWhiteImage.normalized();
        assertEquals(allWhite.averageBrightness(), 127, 127*.001);
        var vLarge=veryLarge.normalized();
        assertEquals(vLarge.averageBrightness(), 127, 127*.001);
        var onePixel=singlePixel.normalized();
        assertEquals(onePixel.averageBrightness(), 127, 127*.001);

    }

    @Test
    void testAllBlackNormalized(){
        assertThrows(ArithmeticException.class, ()->{allBlackImage.normalized();});
    }

    @Test
    void mirrored() {
        var expected = new GrayscaleImage(new double[][]{{2,1},{4,3}});
        assertEquals(smallSquare.mirrored(), expected);
    }

    @Test
    void mirrorablePicture(){
        assertEquals(singlePixel.mirrored(), singlePixel);
        assertNotEquals(smallSquare.mirrored(), smallSquare);
    }

    @Test
    void cropped() {
        var cropped = smallSquare.cropped(1,1,1,1);
        assertEquals(cropped, new GrayscaleImage(new double[][]{{4}}));
    }

    @Test
    void testCroppedAllBlackImage() {
        // Create a cropped image from the allBlackImage (starting from row 1, col 1, with width 2 and height 2)
        GrayscaleImage cropped = allBlackImage.cropped(1, 1, 2, 2);

        // Define the expected cropped result
        GrayscaleImage expectedCropped = new GrayscaleImage(new double[][]{{0, 0}, {0, 0}});

        // Check if the cropped image matches the expected result
        assertEquals(cropped, expectedCropped);
    }

    @Test
    void squarified() {
        var squared = smallWide.squarified();
        var expected = new GrayscaleImage(new double[][]{{1,2},{4,5}});
        assertEquals(squared, expected);
        //added the following tests which test the "centering" of a long horizontal and long vertical GrayscaleImage
        GrayscaleImage longHorizontal=new GrayscaleImage(new double[][]{{1, 2, 3, 4}, {5, 6, 7, 8}});
        var expected2 = new GrayscaleImage(new double[][]{{2, 3}, {6, 7}});
        assertEquals(longHorizontal.squarified(), expected2);
        GrayscaleImage longVertical=new GrayscaleImage(new double[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}});
        var expected3= new GrayscaleImage(new double[][]{{3,4}, {5,6}});
        assertEquals(longVertical.squarified(), expected3);
    }

    @Test
    void testGetPixelThrowsOnNegativeX(){
        assertThrows(IllegalArgumentException.class, () -> { smallSquare.getPixel(-1,0);});
    }

    @Test
    void testCroppedThrowsException(){
        assertThrows(IllegalArgumentException.class, () ->{smallSquare.cropped(1, 2, 5, 5);});
    }
    @Test
    void testEmptyThrowsException(){
             assertThrows(IllegalArgumentException.class, ()->{GrayscaleImage emptySquare = new GrayscaleImage(new double[][]{{},{}});});
    }
}
