package C22410766;

import java.util.ArrayList;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;
import processing.core.PApplet;


public class AaronVisual {
    Visual visual;
    ArrayList<Particle> particles = new ArrayList<>();
    ArrayList<ArrayList<Lines>> lineSets = new ArrayList<>();
    int[] displayTimers; // Array to hold the display timers for each band
    
    
    int len, linesPerBand = 20;
    float amplitude, centerX, centerY;
    float[] bands;
    float previousAmplitude;
    int flashDuration;
    

    public AaronVisual(Visual visual) {
        this.visual = visual;
        visual.startMinim();
        this.displayTimers = new int[visual.getSmoothedBands().length]; // Initialize the timers
        

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


        if (amplitude - previousAmplitude > 0.011) {
            flashDuration = 60;  // Set the duration for 1 second, assuming 60 fps
        }
        previousAmplitude = amplitude;
    
        if (flashDuration > 0) {
            
            lines();
            flashDuration--;  // Decrease the timer
        }


        particles(radius);
        
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
            float angle = visual.random(Visual.TWO_PI);
            float velocityFactor = Visual.map(amplitude, 0.1f, 0.24f, 1, 7);  // Map the band amplitude to a useful velocity range
            particles.add(new Particle(visual, 0, 0, radius/2, angle, velocityFactor));
        }
    }

    public void lines() {
        int maxBandIndex = 0;
        float maxBandValue = 0;
        float[] bandLengthsstart = {300, 220, 440, 160, 180, 200, 320, 240, 260}; // Example specific lengths for each band
        float[] bandLengthsend = {100, 200, 200, 250, 300, 350, 400, 450, 500}; // Example specific lengths for each band

        int[] bandColors = {visual.color(255, 155, 125),   // Red
                            visual.color(20, 165, 120), // Orange
                            visual.color(40, 155, 125), // Yellow
                            visual.color(100, 155, 125),   // Green
                            visual.color(150, 55, 125),   // Blue
                            visual.color(195, 55, 120),  // Indigo
                            visual.color(218, 30, 128),// Violet
                            visual.color(225, 30, 125), // Magenta
                            visual.color(255, 92, 123) // Pink
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
        

        // Update the timer for the highest amplitude band
        if (maxBandIndex != -1) {
            displayTimers[maxBandIndex] = 20; // Set the duration for 1 second, assuming 60 fps
        }

        // Decrease all timers
        for (int i = 0; i < displayTimers.length; i++) {
            if (displayTimers[i] > 0) {
                displayTimers[i]--;
            }
        }

        // Draw the lines for bands with active timers
        for (int i = 0; i < len; i++) {
            if (displayTimers[i] > 0 && Math.random() > 0.5) { // Check if the timer is still active
                for (Lines line : lineSets.get(i)) {
                    line.display();
                }
            }
        }
    }
    
}
