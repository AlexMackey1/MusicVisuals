package C22739165;

import ie.tudublin.Visual;
import processing.core.PApplet;

class RotatingCircles {
    Visual visual;
    float a = 0; // Angle for rotation
    float numCircles; // Number of circles
    float[] bands; // Frequency bands data

    RotatingCircles(Visual visual, int numCircles) {
        this.visual = visual;
        this.numCircles = numCircles;
        this.bands = new float[numCircles];
    }

    public void update(float[] bands) {
        // Copy bands data for visualization
        System.arraycopy(bands, 0, this.bands, 0, PApplet.min(this.bands.length, bands.length));
    }

    public void draw() {
        visual.lights();
        visual.pushMatrix();
        visual.translate(visual.width / 2, visual.height / 2);
    
        for (int i = 0; i < numCircles; i++) {
            float angleOffset = PApplet.map(i, 0, numCircles - 1, 0, PApplet.TWO_PI);
            float bandValue = bands[i % bands.length];
            float circleSize = PApplet.map(bandValue, 0, 1, 50, 300);
            float gray = PApplet.map(i, 0, numCircles - 1, 0, 255);
            float crazyAngle = a + bandValue * 5;
    
            visual.pushMatrix();
            visual.stroke(gray, 100, 255 - gray, 200);
            visual.noFill();
            visual.rotateY(crazyAngle + angleOffset);
            visual.rotateX(crazyAngle / 2 + angleOffset * 2);
            visual.circle(0, 0, circleSize);
            visual.popMatrix();
        }
    
        visual.popMatrix();
        a += 0.01;
    }
    
}
