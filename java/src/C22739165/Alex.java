package C22739165;

import ie.tudublin.Visual;
import processing.core.PApplet;
import processing.core.PImage;

public class Alex {
    Visual visual;
    int numBoxes = 20;
    float angle = 0;
    PImage bgImage; // For the background image

    public Alex(Visual visual) {
        this.visual = visual;
        bgImage = visual.loadImage("java/data/test.jpg"); // Load the background image
        if (bgImage != null) {
            // Resize the image to match the application window size
            bgImage.resize(visual.width, visual.height);
        } else {
            System.out.println("Failed to load test.jpg. Check if the file exists in the data folder.");
        }
    }
    

    public void draw() {
        visual.background(bgImage); // Use the image as the background
        visual.calculateAverageAmplitude();
        float amplitude = visual.getSmoothedAmplitude();
        
        visual.stroke(visual.map(amplitude, 0, 1, 0, 255), 255, 255);
        visual.strokeWeight(2);
        visual.noFill();
        visual.lights();

        float spacing = 360.0f / numBoxes;
        for (int i = 0; i < numBoxes; i++) {
            visual.pushMatrix();
            visual.translate(visual.width / 2, visual.height / 2);
            float currentAngle = PApplet.radians(spacing * i + angle);
            visual.rotateX(currentAngle);
            visual.rotateY(currentAngle);
            float boxSize = 50 + (amplitude * 200);
            visual.box(boxSize);
            visual.popMatrix();
        }

        angle += 0.5;
    }
}
