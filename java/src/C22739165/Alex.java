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
        visual.background(0);
        try {
            visual.calculateFFT();
        } catch (VisualException e) {
            e.printStackTrace();
        }
        visual.calculateFrequencyBands();
        visual.calculateAverageAmplitude();
        float[] bands = visual.getSmoothedBands();
        float amplitude = visual.getSmoothedAmplitude();

        // Update and draw each BoxSet with both amplitude and frequency bands
        for (BoxSet boxSet : boxSets) {
            boxSet.update(amplitude, bands); // Pass both amplitude and bands to update
            boxSet.draw();
        }
    }
}
