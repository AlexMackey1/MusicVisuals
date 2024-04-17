package C22739165;

import ie.tudublin.Visual;
import processing.core.PApplet;

class BoxSet {
    Visual visual;
    float x, y; // Position
    int numBoxes;
    float angle = 0;
    float amplitude;

    BoxSet(Visual visual, float x, float y, int numBoxes) {
        this.visual = visual;
        this.x = x;
        this.y = y;
        this.numBoxes = numBoxes;
    }

    // Add new properties to store the color
    float colorR, colorG, colorB;

    public void update(float amplitude, float[] bands) {
        this.amplitude = amplitude;
        if (bands.length >= 3) {
            // Assuming you want to use the first three bands for coloring
            colorR = PApplet.map(bands[0], 0, 1, 100, 255); // Map the first band to red
            colorG = PApplet.map(bands[1], 0, 1, 100, 255); // Map the second band to green
            colorB = PApplet.map(bands[2], 0, 1, 100, 255); // Map the third band to blue
        }
    }

    public void draw() {
        visual.pushMatrix();
        visual.translate(x, y);
        for (int i = 0; i < numBoxes; i++) {
            visual.pushMatrix();
            float spacing = 360.0f / numBoxes;
            float currentAngle = PApplet.radians(spacing * i + angle);

            visual.stroke(colorR, colorG, colorB); // Use the colors based on the frequency bands
            visual.fill(colorR, colorG, colorB, 150); // Use a semi-transparent fill

            visual.rotateX(currentAngle);
            visual.rotateY(currentAngle);
            float boxSize = 50 + (amplitude * 200);
            visual.box(boxSize);
            visual.popMatrix();
        }
        visual.popMatrix();
        angle += 0.5; // Rotate gradually
    }
}
