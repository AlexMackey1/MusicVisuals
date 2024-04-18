package C22739165;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;

import java.util.ArrayList;

public class Alex {
    Visual visual;
    ArrayList<BoxSet> boxSets; // List of BoxSet objects

    public Alex(Visual visual) {
        this.visual = visual;
        boxSets = new ArrayList<BoxSet>();

        // Example: Create two BoxSet instances at different positions
        boxSets.add(new BoxSet(visual, visual.width / 3, visual.height / 2, 20));
        boxSets.add(new BoxSet(visual, 2 * visual.width / 3, visual.height / 2, 20));
    }
    
    public void draw() {
        visual.background(0); // Set the background to black
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


        // Update and draw each BoxSet
        for (BoxSet boxSet : boxSets) {
            boxSet.update(amplitude); // Update with the current amplitude
            boxSet.draw(); // Draw the box set
        }
    }
}
