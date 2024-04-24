package C22739165;

import ie.tudublin.Visual;
import processing.core.PApplet;

class RotatingCircles {
    Visual visual; // Reference to the Visual object for rendering
    float a = 0; // Rotation control variable
    float numCircles; // Number of circles to be drawn
    float[] bands; // Array to store frequency band data

    // Constructor to initialize RotatingCircles object
    RotatingCircles(Visual visual, int numCircles) {
        this.visual = visual;
        this.numCircles = numCircles;
        this.bands = new float[numCircles]; // Initialize the bands array
    }

    // Method to update frequency band data
    public void update(float[] bands) {
        // Copy frequency band data to the bands array, ensuring it doesn't exceed the array length
        System.arraycopy(bands, 0, this.bands, 0, Math.min(this.bands.length, bands.length));
    }

    // Method to draw the rotating circles
    public void draw() {
        visual.lights(); // Enable lighting for 3D rendering
        visual.pushMatrix(); // Save the current transformation state
        visual.translate(visual.width / 2, visual.height / 2); // Translate to the center of the screen

        // Increase rotation speed along the Z-axis
        visual.rotateZ(PApplet.radians(a)); 

        // Iterate over each circle to be drawn
        for (int i = 0; i < numCircles; i++) {
            float bandValue = bands[i % bands.length]; // Get the corresponding frequency band value
            float circleSize = PApplet.map(bandValue, 0, 1, 10, 20); // Map band value to circle size

            // Map the index to a vibrant color spectrum
            float hue = PApplet.map(i, 0, numCircles, 0, 255); 
            visual.stroke(hue, 255, 255); // Set stroke color
            visual.fill(hue, 255, 255, 127); // Set fill color with semi-transparency

            // Calculate the position of the circle in a circular pattern
            float angle = PApplet.TWO_PI * i / numCircles + PApplet.radians(a); 
            float x = 50 * PApplet.cos(angle); // Calculate x-coordinate
            float y = 50 * PApplet.sin(angle); // Calculate y-coordinate

            visual.pushMatrix(); // Save the current transformation state
            visual.translate(x, y); // Translate to the circle position
            visual.circle(0, 0, circleSize); // Draw the circle
            visual.popMatrix(); // Restore the previous transformation state
        }

        visual.popMatrix(); // Restore the original transformation state
        a += 1; // Increase the rotation angle for faster animation
    }
}
