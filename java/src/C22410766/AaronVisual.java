package C22410766;

import ie.tudublin.Visual;

public class AaronVisual {
    Visual visual;

    public AaronVisual(Visual visual) {
        this.visual = visual;
    }

    public void draw() {
        visual.background(255);
        visual.fill(255, 0, 0);
        visual.circle(visual.width / 2, visual.height / 2, 200);
    }
}
