package C22739165;

import ie.tudublin.Visual;
import processing.core.PApplet;

class BoxSet {
    Visual visual;
    float x, y; // Position
    int numBoxes;
    float angle = 0;
    float amplitude;
    float colorR, colorG, colorB;

    // Constructor to initialize a BoxSet object
    BoxSet(Visual visual, float x, float y, int numBoxes) {
        this.visual = visual;
        this.x = x;
        this.y = y;
        this.numBoxes = numBoxes;
    }

    public void update(float amplitude, float[] bands) {
        this.amplitude = amplitude;
        if (bands.length >= 3) {
            // Implementing gradient based on the band values
            colorR = PApplet.map(bands[0], 0, 1, 200, 255);
            colorG = PApplet.map(bands[1], 0, 1, 200, 255);
            colorB = PApplet.map(bands[2], 0, 1, 200, 255);
        }
    }

    // Draw method to render the BoxSet object
    public void draw() {
        visual.pushMatrix();
        visual.translate(x, y);

        // Rotation controlled by amplitude
        angle += PApplet.map(amplitude, 0, 1, 0, 5);
        visual.rotateY(angle);
        visual.rotateX(angle / 2);

        // Draw each box in the BoxSet
        for (int i = 0; i < numBoxes; i++) {
            visual.pushMatrix();
            float spacing = PApplet.TWO_PI / numBoxes;
            float currentAngle = spacing * i;

            float boxSize = 50 + amplitude * 100; // Dynamically change the size based on amplitude

            // Set stroke and fill colors for the box
            visual.stroke(colorR, colorG, colorB);
            visual.fill(colorR, colorG, colorB, 150); // Keep the opacity subtle

            // Apply rotation and translation transformations to each box
            visual.rotateY(currentAngle);
            visual.rotateX(currentAngle / 2);
            visual.translate(50, 0, 0);
            visual.box(boxSize); // Draw the box
            visual.popMatrix(); // Restore previous transformation state
        }
        visual.popMatrix(); // Restore original transformation state
    }
}
