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

        // Create BoxSet instances in a diamond shape
        // Top
        boxSets.add(new BoxSet(visual, visual.width / 2, visual.height / 4, 20));
        // Right
        boxSets.add(new BoxSet(visual, 3 * visual.width / 4, visual.height / 2, 20));
        // Bottom
        boxSets.add(new BoxSet(visual, visual.width / 2, 3 * visual.height / 4, 20));
        // Left
        boxSets.add(new BoxSet(visual, visual.width / 4, visual.height / 2, 20));
    }

    public void draw() {
        visual.background(0); // Set the background to black
        try {
            visual.calculateFFT(); // Perform FFT analysis
        } catch (VisualException e) {
            e.printStackTrace();
        }
        visual.calculateFrequencyBands();
        visual.calculateAverageAmplitude();
        float[] bands = visual.getSmoothedBands();
        float amplitude = visual.getSmoothedAmplitude();

        // Update and draw each BoxSet with both amplitude and frequency bands
        for (BoxSet boxSet : boxSets) {
            boxSet.update(amplitude, bands); // Update with the current amplitude and frequency bands
            boxSet.draw(); // Draw the box set
        }
    }
}

