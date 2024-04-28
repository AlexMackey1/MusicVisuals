package C22410766;

import ie.tudublin.Visual;

public class start {
    Visual visual;
    float a, b;
    float offset = (float) (Visual.PI/24.0);

    public start(Visual visual) {
        this.visual = visual;


    }
    public void draw(){
        visual.background(0);

        float centerX = visual.width / 2;
        float centerY = visual.height / 2;

        // Pulsing effect by changing the text size using a sinusoidal function
        float pulse = Visual.abs(Visual.sin(a)) * 10;  // Multiplier determines the scale of pulse

        visual.fill(255);  // White color text
        visual.textAlign(Visual.CENTER, Visual.CENTER);  // Center the text alignment

        // Main title text with pulsing effect
        visual.textSize(50 + pulse);  // Base size of 50 with added pulsing effect
        visual.text("OOP Visual assignment", centerX, centerY - 30);  // Adjust position as needed

        // Subtitle text with a smaller pulsing effect
        visual.textSize(30 + pulse / 2);  // Subtitle with a less pronounced pulse
        visual.text("by Aaron, Alex, Sergei", centerX, centerY + 30);  // Adjust position as needed

        a += 0.05;  // Control the speed of the pulsing
    }
}
