package assign01;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;


/**
 * Represents a grayscale (black and white) image as a 2D array of "pixel" brightnesses
 * 255 is "white" 127 is "gray" 0 is "black" with intermediate values in between
 * Author: Ben Jones and Corinne Jones
 */
public class GrayscaleImage {
    private double[][] imageData; // the actual image data

    /**
     * Initialize an image from a 2D array of doubles
     * This constructor creates a copy of the input array
     * @param data initial pixel values
     * @throws IllegalArgumentException if the input array is empty or "jagged" meaning not all rows are the same length
     */

    public GrayscaleImage(double[][] data){
        if(data.length == 0 || data[0].length == 0){
            throw new IllegalArgumentException("Image is empty");
        }

        imageData = new double[data.length][data[0].length];
        for(var row = 0; row < imageData.length; row++){
            if(data[row].length != imageData[row].length){
                throw new IllegalArgumentException("All rows must have the same length");
            }
            for(var col = 0; col < imageData[row].length; col++){
                imageData[row][col] = data[row][col];
            }
        }
    }

    /**
     * Fetches an image from the specified URL and converts it to grayscale
     * Uses the AWT Graphics2D class to do the conversion, so it may add
     * an item to your dock/menu bar as if you're loading a GUI program
     * @param url where to download the image
     * @throws IOException if the image can't be downloaded for some reason
     */
    public GrayscaleImage(URL url) throws IOException {
        var inputImage = ImageIO.read(url);
        //convert input image to grayscale
        //based on (https://stackoverflow.com/questions/6881578/how-to-convert-between-color-models)
        var grayImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d= grayImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, null);
        g2d.dispose();
        imageData = new double[grayImage.getHeight()][grayImage.getWidth()];

        //raster is basically a width x height x 1 3-dimensional array
        var grayRaster = grayImage.getRaster();
        for(var row = 0; row < imageData.length; row++){
            for(var col = 0; col < imageData[0].length; col++){
                //getSample parameters are x (our column) and y (our row), so they're "backwards"
                imageData[row][col] = grayRaster.getSampleDouble(col, row, 0);
            }
        }
    }

    public void savePNG(File filename) throws IOException {
        var outputImage = new BufferedImage(imageData[0].length, imageData.length, BufferedImage.TYPE_BYTE_GRAY);
        var raster = outputImage.getRaster();
        for(var row = 0; row < imageData.length; row++){
            for(var col = 0; col < imageData[0].length; col++){
                raster.setSample(col, row, 0, imageData[row][col]);
            }
        }
        ImageIO.write(outputImage, "png", filename);
    }

    //Corinne's code is filled in below :)

    /**
     * Get the pixel brightness value at the specified coordinates
     * (0,0) is the top left corner of the image, (width -1, height -1) is the bottom right corner
     * @param x horizontal position, increases left to right
     * @param y vertical position, **increases top to bottom**
     * @return the brightness value at the specified coordinates
     * @throws IllegalArgumentException if x, y are not within the image width/height
     */
    public double getPixel (int x, int y){
        // Check if the x or y coordinate is less than 0, throw an exception if so
        if(x<0||y<0){
            throw new IllegalArgumentException();
        }
        // Return the pixel value from the imageData array at the specified (y, x) coordinates
        // Note that the coordinates are provided as (x, y), but they are access in the array as [y][x]
        return this.imageData[x][y];
    }

    /**
     * Two images are equal if they have the same size and each corresponding pixel
     * in the two images is exactly equal
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other){
        // Check if the 'other' object is not an instance of GrayscaleImage
        if(!(other instanceof GrayscaleImage)){
            return false;
        }
        // Cast the 'other' object to a GrayscaleImage
        GrayscaleImage otherImage = (GrayscaleImage)other;

        if (imageData.length!=otherImage.imageData.length||imageData[0].length!=otherImage.imageData[0].length){
            return false;
        }

        // Iterate through the rows and columns of both objects, comparing pixel values
        for(var row=0; row < imageData.length; row++) {
            for(var col=0; col<imageData[0].length; col++){
                // Compare pixel values at the same position in both images
                if(this.getPixel(row, col)!=otherImage.getPixel(row, col)){
                    // If a mismatch is found, the images are not equal, so return false
                    return false;
                }
            }
        }
        // If the loop completes without finding any differences, the images are equal, so return true
        return true;
    }


    /**
     * Computes the average of all values in image data
     * @return the average of the imageData array
     */
    public double averageBrightness(){
        // Initialize variables to keep track of the sum of pixel values and the total number of pixels
        double sum=0;
        double count=0;
        // Iterate through the rows and columns of the image data
        for(var row=0; row < imageData.length; row++) {
            for(var col=0; col<imageData[0].length; col++) {
                // Add the current pixel value to the running sum
                sum+=imageData[row][col];
                // Increment the count to keep track of the number of pixels
                count++;
            }
         }
        // Calculate and return the average brightness by dividing the sum by the count
        return sum/count;
    }

    /**
     * Return a new GrayScale image where the average new average brightness is 127
     * To do this, uniformly scale each pixel (ie, multiply each imageData entry by the same value)
     * Due to rounding, the new average brightness will not be 127 exactly, but should be very close
     * The original image should not be modified
     * @return a GrayScale image with pixel data uniformly rescaled so that its averageBrightness() is 127
     */

    public void setPixel(int x, int y, double data){
        // Helper function used throughout
        // Set the pixel value in the imageData array at the specified (y, x) coordinates
        imageData[x][y]=data;
    }
    public GrayscaleImage normalized(){
        // Calculate the average brightness of the original image
        double avgBright=this.averageBrightness();

        // Check if avgBright is zero to prevent division by zero
        if (avgBright == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }

        // Calculate the scaling factor for normalization
        double scale =127/avgBright;

        // Iterate through the rows and columns of the image data for normalization
        for(var row=0; row < imageData.length; row++) {
            for(var col=0; col<imageData[0].length; col++) {
                // Calculate the new pixel value by scaling the original pixel value
                double newPixel=(this.getPixel(row, col)*scale);
                // Update the pixel value in the original image with the new normalized value
                this.setPixel(row, col, newPixel);
            }
        }
        // Return the modified original image, which is now normalized
        return this;
    }


    /**
     * Returns a new grayscale image that has been "mirrored" across the y-axis
     * In other words, each row of the image should be reversed
     * The original image should be unchanged
     * @return a new GrayscaleImage that is a mirrored version of the this
     */
    public GrayscaleImage mirrored () {
        // Create a new GrayscaleImage with the same dimensions as the original image
        GrayscaleImage mirroredImage = new GrayscaleImage(this.imageData);

        // Iterate through the rows and columns of the original image for mirroring
        for (var row = 0; row < imageData.length; row++) {
            for (var col = 0; col < imageData[0].length; col++) {
                // Set the pixel value in the mirrored image by swapping the columns across the y-axis
                // The new column index is calculated as the difference between the last column index and the current column
                mirroredImage.setPixel(row, col, imageData[row][imageData[0].length - 1 - col]);
            }
        }
        // Return the new GrayscaleImage, which is now the mirrored version of the original image
        return mirroredImage;
    }

    /**
     * Returns a new GrayscaleImage of size width x height, containing the part of `this`
     * from startRow -> startRow + height, startCol -> startCol + width
     * The original image should be unmodified
     * @param startRow
     * @param startCol
     * @param width
     * @param height
     * @return A new GrayscaleImage containing the sub-image in the specified rectangle
     * @throws IllegalArgumentException if the specified rectangle goes outside the bounds of the original image
     */
    public GrayscaleImage cropped(int startRow, int startCol, int width, int height){
        // Check if the specified rectangle is outside the bounds of the original image and throw an exception if necessary
        if (startRow < 0 || startCol < 0 || startRow + height > this.imageData.length || startCol + width > this.imageData[0].length) {
            throw new IllegalArgumentException("The specified rectangle is outside the bounds of the original image.");
        }
        // Create a new GrayscaleImage to store the cropped image with the specified dimensions (width and height)
        GrayscaleImage croppedImage = new GrayscaleImage(new double [width][height]);

        // Iterate through the specified rectangular region in the original image for cropping
        for (int row = startRow; row < startRow+height; row++) {
            for (int col = startCol; col < startCol+width; col++) {
                // Get the pixel value from the original image at the appropriate position
                double pixel = this.getPixel(row, col);
                // Set the pixel value in the cropped image
                croppedImage.setPixel(row-startRow, col-startCol, pixel);
            }
        }
        // Return the new GrayscaleImage, which is the cropped version of the original image
        return croppedImage;
    }

    /**
     * Returns a new "centered" square image (new width == new height)
     * For example, if the width is 20 pixels greater than the height,
     * this should return a height x height image, with 10 pixels removed from the left and right
     * edges of the image
     * If the number of pixels to be removed is odd, remove 1 fewer pixel from the left or top part
     * (note this convention should be SIMPLER/EASIER to implement than the alternative)
     * The original image should not be changed
     * @return a new, square, GrayscaleImage
     */
    public GrayscaleImage squarified(){
        int height = imageData.length;
        int width = imageData[0].length;

        // If the image is already square, no changes are needed, so return the original image
        if (height == width) {
            return this;
        }
        // Determine the size of the new squared image based on the smaller dimension (height or width)
        int newSize = Math.min(height, width);

        // Create a new 2D array to store the pixel data for the squared image
        double[][] newImageData = new double[newSize][newSize];

        // Calculate the number of rows and columns to remove from the original image to achieve square dimensions
        int removeTop = (height - newSize) / 2;
        int removeLeft = (width - newSize) / 2;

        // Iterate through the new squared image and copy pixel values from the original image
        for (int newRow = 0; newRow < newSize; newRow++) {
            for (int newCol = 0; newCol < newSize; newCol++) {
                // Copy pixel values from the original image with adjustments for removal of rows and columns
                newImageData[newRow][newCol] = imageData[newRow + removeTop][newCol + removeLeft];
            }
        }
        // Return the new GrayscaleImage, which is now a squared version of the original image
        return new GrayscaleImage(newImageData);
    }

}
