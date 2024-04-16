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

    public void update(float amplitude) {
        this.amplitude = amplitude;
    }

    public void draw() {
        visual.pushMatrix();
        visual.translate(x, y);
        for (int i = 0; i < numBoxes; i++) {
            visual.pushMatrix();
            float spacing = 360.0f / numBoxes;
            float currentAngle = PApplet.radians(spacing * i + angle);

            // Calculate color based on amplitude
            float r = PApplet.map(amplitude, 0, 1, 100, 255); // Red from low to high intensity
            float g = PApplet.map(amplitude, 0, 1, 255, 100); // Green from high to low intensity
            float b = 255 - r; // Blue inversely related to red

            visual.stroke(r, g, b);
            visual.fill(r, g, b, 150); // Semi-transparent fill

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
