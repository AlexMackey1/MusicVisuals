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

    BoxSet(Visual visual, float x, float y, int numBoxes) {
        this.visual = visual;
        this.x = x;
        this.y = y;
        this.numBoxes = numBoxes;
    }

    public void update(float amplitude, float[] bands) {
        this.amplitude = amplitude;
        if (bands.length >= 3) {
            // Maintain the color transition effects
            colorR = 200 + 55 * PApplet.sin(PApplet.radians(bands[0] * 360));
            colorG = 200 + 55 * PApplet.sin(PApplet.radians(bands[1] * 360));
            colorB = 200 + 55 * PApplet.sin(PApplet.radians(bands[2] * 360));
        }
    }

    public void draw() {
        visual.pushMatrix();
        visual.translate(x, y);

        // Ensure the rotation is continuous
        angle = (angle + 0.05f) % PApplet.TWO_PI; // Smooth incremental rotation
        visual.rotateY(angle);
        visual.rotateX(angle / 2);

        for (int i = 0; i < numBoxes; i++) {
            visual.pushMatrix();
            float spacing = PApplet.TWO_PI / numBoxes;
            float currentAngle = spacing * i;

            visual.stroke(colorR, colorG, colorB);
            visual.fill(colorR, colorG, colorB, 150); // Keep the opacity subtle

            visual.rotateY(currentAngle);
            visual.rotateX(currentAngle / 2);
            // Dynamically increase the size based on the amplitude
            float boxSize = 50 + (300 * amplitude); // Base size + scaled with amplitude
            visual.translate(50, 0, 0); // Position adjustment for design consistency
            visual.box(boxSize);
            visual.popMatrix();
        }
        visual.popMatrix();
    }
}
