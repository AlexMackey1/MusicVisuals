package C22739165;

import ie.tudublin.Visual;
import processing.core.PApplet;

class BoxSet {
    Visual visual;
    float x, y; // Position
    int numBoxes;
    float angle = 0;
    float amplitude;
    // Color components
    float colorR, colorG, colorB;

    BoxSet(Visual visual, float x, float y, int numBoxes) {
        this.visual = visual;
        this.x = x;
        this.y = y;
        this.numBoxes = numBoxes;
    }

    public void update(float amplitude, float[] bands) {
        this.amplitude = amplitude;
        
        if (bands.length >= 3) {
            // Map the first three bands to RGB color components
            colorR = PApplet.map(bands[0], 0, 1, 100, 255);
            colorG = PApplet.map(bands[1], 0, 1, 100, 255);
            colorB = PApplet.map(bands[2], 0, 1, 100, 255);
        }
    }

    public void draw() {
        visual.pushMatrix();
        visual.translate(x, y);
        for (int i = 0; i < numBoxes; i++) {
            visual.pushMatrix();
            float spacing = 360.0f / numBoxes;
            float currentAngle = PApplet.radians(spacing * i + angle);

            visual.stroke(colorR, colorG, colorB); // Use the RGB values from the frequency bands
            visual.fill(colorR, colorG, colorB, 150); // Add semi-transparency to the fill

            visual.rotateX(currentAngle);
            visual.rotateY(currentAngle);
            // Increase the base size and the amplitude effect for a larger expansion
            float boxSize = 100 + (amplitude * 400); // Larger base size and more dramatic effect
            visual.box(boxSize);
            visual.popMatrix();
        }
        visual.popMatrix();
        angle += 0.5; // Rotate gradually
    }
}

