package C22739165;

import ie.tudublin.Visual;
import processing.core.PApplet;

class BoxSet {
    Visual visual;
    float x, y; // Position
    int numBoxes;
    float angle = 0;
    float amplitude;
    float[] colorShift;

    BoxSet(Visual visual, float x, float y, int numBoxes) {
        this.visual = visual;
        this.x = x;
        this.y = y;
        this.numBoxes = numBoxes;
        this.colorShift = new float[numBoxes]; // Initialize color shift array
        for (int i = 0; i < numBoxes; i++) {
            colorShift[i] = (360 / numBoxes) * i; // Distribute hues evenly across the spectrum
        }
    }

    public void update(float amplitude, float[] bands) {
        this.amplitude = amplitude;
        for (int i = 0; i < numBoxes; i++) {
            // Slowly increment the hue to cycle through colors
            colorShift[i] += 1;
            if (colorShift[i] > 360) {
                colorShift[i] = 0; // Reset hue after completing a full cycle
            }
        }
    }

    public void draw() {
        visual.colorMode(Visual.HSB, 360, 100, 100, 100); // Use HSB for vibrant, full-spectrum colors
        visual.pushMatrix();
        visual.translate(x, y);

        // Rotation controlled by mouse position or audio triggers
        angle += PApplet.map(amplitude, 0, 1, 0, 5);
        visual.rotateY(angle);
        visual.rotateX(angle / 2);

        for (int i = 0; i < numBoxes; i++) {
            visual.pushMatrix();
            float spacing = PApplet.TWO_PI / numBoxes;
            float currentAngle = spacing * i;

            float boxSize = 50 + amplitude * 100; // Dynamically change the size

            // Use vibrant rainbow colors
            visual.stroke(colorShift[i], 100, 100);
            visual.fill(colorShift[i], 100, 100, 50); // Use a semi-transparent fill for depth

            visual.rotateY(currentAngle);
            visual.rotateX(currentAngle / 2);
            visual.translate(50, 0, 0);
            visual.box(boxSize);
            visual.popMatrix();
        }
        visual.popMatrix();
    }
}
