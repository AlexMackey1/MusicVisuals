package C22739165;

import ie.tudublin.Visual;
import processing.core.PApplet;

class RotatingCircles {
    Visual visual;
    float a = 0; // Rotation control
    float numCircles;
    float[] bands;

    RotatingCircles(Visual visual, int numCircles) {
        this.visual = visual;
        this.numCircles = numCircles;
        this.bands = new float[numCircles];
    }

    public void update(float[] bands) {
        System.arraycopy(bands, 0, this.bands, 0, Math.min(this.bands.length, bands.length));
    }

    public void draw() {
        visual.lights();
        visual.pushMatrix();
        visual.translate(visual.width / 2, visual.height / 2);

        // Increase rotation speed
        visual.rotateZ(PApplet.radians(a));

        for (int i = 0; i < numCircles; i++) {
            float bandValue = bands[i % bands.length];
            float circleSize = PApplet.map(bandValue, 0, 1, 10, 20); // Keeping circle size adjustment

            // Use a vibrant color spectrum based on the index
            float hue = PApplet.map(i, 0, numCircles, 0, 255);
            visual.stroke(hue, 255, 255);
            visual.fill(hue, 255, 255, 127); // Semi-transparent fill for vibrant effect

            // Positioning circles in a simple circular pattern
            float angle = PApplet.TWO_PI * i / numCircles + PApplet.radians(a);
            float x = 50 * PApplet.cos(angle); // Position circles in a circle
            float y = 50 * PApplet.sin(angle);

            visual.pushMatrix();
            visual.translate(x, y);
            visual.circle(0, 0, circleSize);
            visual.popMatrix();
        }

        visual.popMatrix();
        a += 1; // Increase the rotation angle more quickly for faster animation
    }
}
