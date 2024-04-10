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
            visual.rotateX(currentAngle);
            visual.rotateY(currentAngle);
            float boxSize = 50 + (amplitude * 200); // Modify as needed for different effects
            visual.box(boxSize);
            visual.popMatrix();
        }
        visual.popMatrix();
        angle += 0.5; // Rotate gradually
    }
}
