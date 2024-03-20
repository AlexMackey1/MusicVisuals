package C22739165;

import ie.tudublin.Visual;

public class Alex {
    Visual visual; // Assuming Visual is your PApplet subclass

    public Alex(Visual visual) {
        this.visual = visual;
    }

    public void draw() {
        visual.background(0);
        visual.calculateAverageAmplitude();
        visual.stroke(visual.map(visual.getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        visual.strokeWeight(5);
        visual.noFill();
        visual.lights();
        visual.pushMatrix();
        visual.translate(visual.width / 2, visual.height / 2, -200);
        visual.rotateX(visual.frameCount * 0.01f);
        visual.rotateZ(visual.frameCount * 0.01f);
        visual.box(50 + (200 * visual.getSmoothedAmplitude()));
        visual.popMatrix();
    }
}
