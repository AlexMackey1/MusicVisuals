package C22410766;

import java.util.ArrayList;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;


public class AaronVisual {
    Visual visual;
    ArrayList<Particle> particles = new ArrayList<>();
    Particle p;

    public AaronVisual(Visual visual) {
        this.visual = visual;

        visual.startMinim();
        
    }

    float b = 0;                 // Angle of rotation
    float offset = (float) (visual.PI/24.0);  // Angle offset between boxes
    int num = 60;

    

    public void draw() {
        visual.background(0);
        try {
            visual.calculateFFT(); // Perform FFT analysis
        } catch (VisualException e) {
            e.printStackTrace();
        }
        visual.calculateFrequencyBands(); // Calculate frequency bands
        visual.calculateAverageAmplitude(); // Calculate amplitude
        float[] bands = visual.getSmoothedBands(); // max 667
        float amplitude = visual.getSmoothedAmplitude(); // max value is 0.233

        //float bgHue = visual.map(amplitude, 0, 0.3f, 0, 360);  // Use full color wheel
        //visual.background(bgHue, 100, 100, 1);  // Set background with HSB color mode
        float radius = visual.map(amplitude, 0, 0.3f, 200, 400);
        float centerX = visual.width / 2;
        float centerY = visual.height / 2;
        
        
        
        float hue = visual.map(amplitude, 0.15f, 0.3f, 100, 255);
        //visual.strokeWeight(10);
        
        visual.noFill();
        
        visual.lights();
        visual.translate(centerX, centerY); // Center translate to middle of window
        float rotateVelocity = visual.map(amplitude, 0.1f, 0.24f, 0.4f, 1.1f) * offset;
        

        for (int i = 0; i < num; i++) {
            float gray = visual.map(i, 0, num - 1, 0, 255);
            visual.pushMatrix();
            //visual.fill(gray);
            visual.stroke(hue, 255, 255);
            visual.rotateY(b + rotateVelocity * i);    // Rotate around Y axis
            visual.rotateX(b / 2 + rotateVelocity * i); // Rotate around X axis
            visual.ellipse(0, 0, radius, radius);
            visual.popMatrix();
        }

        b += 0.01;  // Increment angle
        if (b > visual.TWO_PI) {  // Reset 'a' to avoid overflow
            b = 0;
                }
        // Draw each circle
        
        

        
        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle p = particles.get(i);
            p.run();  // Update and display the particle

            if (p.isDead()) {
                particles.remove(i);  // Remove dead particles
            }
        }

        // Add new particles based on some condition (e.g., on beats)
        int numParticlesToSpawn = (int) (amplitude * 150);  // Scale number of particles with amplitude
        for (int i = 0; i < numParticlesToSpawn; i++) {
            float angle = visual.random(visual.TWO_PI);
            float velocityFactor = visual.map(amplitude, 0.1f, 0.24f, 1, 5);  // Map the band amplitude to a useful velocity range
            particles.add(new Particle(visual, 0, 0, radius/2, angle, velocityFactor));
            visual.println(velocityFactor);
        }
        
    }
    
}
