package C22410766;

import java.util.ArrayList;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;
import processing.core.PApplet;


public class AaronVisual {
    Visual visual;
    ArrayList<Particle> particles = new ArrayList<>();
    ArrayList<ArrayList<Lines>> lineSets = new ArrayList<>();
    
    
    int len, linesPerBand = 20;
    float amplitude, centerX, centerY;
    float[] bands;
    float previousAmplitude;
    

    public AaronVisual(Visual visual) {
        this.visual = visual;
        visual.startMinim();

        this.centerX = visual.width / 2;
        this.centerY = visual.height / 2;

        
    }


    public void draw() {
        visual.background(0);
        
        try {
            visual.calculateFFT(); // Perform FFT analysis
        } catch (VisualException e) {
            e.printStackTrace();
        }

        visual.calculateFrequencyBands(); // Calculate frequency bands
        bands = visual.getSmoothedBands(); // max 667
        len = bands.length;
        
        visual.calculateAverageAmplitude(); // Calculate amplitude
        amplitude = visual.getSmoothedAmplitude(); // max value is 0.233
        float radius = Visual.map(amplitude, 0, 0.3f, 200, 400);
        

        float b = 0;                 // Angle of rotation
        float offset = (float) (visual.PI/24.0);  // Angle offset between boxes
        int num = 60;

        float hue = Visual.map(amplitude, 0.15f, 0.3f, 100, 255);
        visual.noFill();
        visual.lights();
        visual.translate(centerX, centerY); // Center translate to middle of window
        float rotateVelocity = Visual.map(amplitude, 0.1f, 0.24f, 0.4f, 1.1f) * offset;
        
        for (int i = 0; i < num; i++) {
            visual.pushMatrix();
            //visual.fill(gray);
            visual.stroke(hue, 255, 255);
            visual.rotateY(b + rotateVelocity * i);    // Rotate around Y axis
            visual.rotateX(b / 2 + rotateVelocity * i); // Rotate around X axis
            visual.ellipse(0, 0, radius, radius);
            visual.popMatrix();
        }

        b += 0.01;  // Increment angle
        if (b > Visual.TWO_PI) {  // Reset 'b' to avoid overflow
            b = 0;
                }

        if (amplitude - previousAmplitude > 0.011) {  // Simple beat detection
            // Trigger a special effect
            visual.fill(360, 100, 100);
            visual.rect(0, 0, visual.width, visual.height);  // Flash the screen
        }
        previousAmplitude = amplitude;
        visual.println(amplitude);

        particles(radius);
        lines();
    }

    public void particles(float radius) {
        int numParticlesToSpawn = (int) (amplitude * 350);  // Scale number of particles with amplitude

        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle p = particles.get(i);
            p.run();  // Update and display the particle

            if (p.isDead()) {
                particles.remove(i);  // Remove dead particles
            }
        }

        // Add new particles based on some condition (e.g., on beats)
        for (int i = 0; i < numParticlesToSpawn; i++) {
            float angle = visual.random(visual.TWO_PI);
            float velocityFactor = Visual.map(amplitude, 0.1f, 0.24f, 1, 7);  // Map the band amplitude to a useful velocity range
            particles.add(new Particle(visual, 0, 0, radius/2, angle, velocityFactor));
        }
    }

    public void lines() {
        int maxBandIndex = 0;
        float maxBandValue = 0;
        float[] bandLengthsstart = {100, 120, 140, 160, 180, 200, 220, 240, 260}; // Example specific lengths for each band
        float[] bandLengthsend = {100, 1200, 1400, 1600, 1800, 2000, 2200, 2400, 2600}; // Example specific lengths for each band

        int[] bandColors = {visual.color(255, 255, 125),   // Red
                            visual.color(20, 165, 120), // Orange
                            visual.color(40, 255, 125), // Yellow
                            visual.color(100, 255, 125),   // Green
                            visual.color(150, 55, 125),   // Blue
                            visual.color(195, 255, 120),  // Indigo
                            visual.color(218, 230, 128),// Violet
                            visual.color(225, 230, 125), // Magenta
                            visual.color(255, 192, 123) // Pink
        };

        for (int i = 0; i < len; i++) {
            ArrayList<Lines> lines = new ArrayList<>();
            for (int j = 0; j < linesPerBand; j++) {
                float angle = Visual.TWO_PI / linesPerBand * j + Visual.TWO_PI / len * i;
                float strokeWeight = visual.random(1, 3);
                lines.add(new Lines(visual, 0, 0, bandLengthsstart[i], angle, bandLengthsend[i], bandColors[i], strokeWeight));
            }
            lineSets.add(lines);
        }

        
        for (int i = 0; i < len; i++) {
            if (bands[i] > maxBandValue) {
                maxBandValue = bands[i];
                maxBandIndex = i;
            }
        }
        

        // Draw only the lines of the band with the highest current value
        for (int i = 0; i < len; i++) {
            for (Lines line : lineSets.get(i)) {
                if (i == maxBandIndex) {
                    line.display();
                }
            }
        }
    }
    
}
