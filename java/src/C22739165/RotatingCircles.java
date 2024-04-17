package C22739165;

import ie.tudublin.Visual;
import processing.core.PApplet;

class RotatingCircles {
    Visual visual;
    float a = 0; // Angle for rotation, used more subtly
    float numCircles; // Number of circles
    float[] bands; // Frequency bands data

    RotatingCircles(Visual visual, int numCircles) {
        this.visual = visual;
        this.numCircles = numCircles;
        this.bands = new float[numCircles];
    }

    public void update(float[] bands) {
        System.arraycopy(bands, 0, this.bands, 0, PApplet.min(this.bands.length, bands.length));
    }

    public void draw() {
        visual.lights();
        visual.pushMatrix();
        visual.translate(visual.width / 2, visual.height / 2);

        for (int i = 0; i < numCircles; i++) {
            float angleOffset = PApplet.map(i, 0, numCircles - 1, 0, PApplet.PI); // Reduced rotation range
            float bandValue = bands[i % bands.length];
            // Further reduce the range of circle sizes for minimal variability
            float circleSize = PApplet.map(bandValue, 0, 1, 30, 40);
            float gray = PApplet.map(i, 0, numCircles - 1, 0, 255);
            float crazyAngle = a; // Use only a single slowly increasing angle for all circles

            visual.pushMatrix();
            visual.stroke(gray, 100, 255 - gray, 200);
            visual.noFill();
            visual.rotateY(crazyAngle + angleOffset); // Simplified rotation only around Y-axis
            visual.circle(0, 0, circleSize);
            visual.popMatrix();
        }

        visual.popMatrix();
        a += 0.005; // Slow down the rotation increment
    }
}
