package C22739165;

import ie.tudublin.Visual;
import processing.core.PApplet;

class RotatingCircles {
    Visual visual;
    float a = 0; // Angle for rotation, used for overall rotation
    float axisAngleX = 0; // Angle for rotation axis movement on X
    float axisAngleY = 0; // Angle for rotation axis movement on Y
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

        // Update the axis rotation angles
        axisAngleX += 0.01;
        axisAngleY += 0.015;

        for (int i = 0; i < numCircles; i++) {
            float angleOffset = PApplet.map(i, 0, numCircles - 1, 0, PApplet.TWO_PI);
            float bandValue = bands[i % bands.length];
            float circleSize = PApplet.map(bandValue, 0, 1, 30, 40); // Keeping the size change minimal
            float gray = PApplet.map(i, 0, numCircles - 1, 0, 255);

            visual.pushMatrix();
            visual.stroke(gray, 100, 255 - gray, 200);
            visual.noFill();

            // Apply rotations based on changing axes
            visual.rotateX(axisAngleX + angleOffset); // Rotating around a dynamic X-axis
            visual.rotateY(axisAngleY + angleOffset); // Rotating around a dynamic Y-axis
            visual.circle(0, 0, circleSize);

            visual.popMatrix();
        }

        visual.popMatrix();
        a += 0.005; // Continue to gradually increase the general rotation angle
    }
}
