package Mercury;

import ie.tudublin.Visual;
import C22739165.Alex;
import C22410766.AaronVisual;
import C22483302.SergeiAwesomeVisual;

public class BohemianRhapsody extends Visual {
    int mode = 0;
    Alex alex;
    AaronVisual aaron;
    SergeiAwesomeVisual sergei;

    public void settings() {
        size(800, 600, P3D);
    }

    public void setup() {
        startMinim();
        // Make sure the path is correct. It assumes the file is in the data folder.
        loadAudio("MusicVisuals/java/data/QueenBohemianRhapsody.mp3");
        getAudioPlayer().play();  // Start playing the audio for debugging
        colorMode(HSB);
        alex = new Alex(this); // Pass 'this' as the PApplet context
        aaron = new AaronVisual(this);
        sergei = new SergeiAwesomeVisual(this);
    }

    public void keyPressed() {
        if (key == 'p') {  // Press 'p' to play/pause for testing
            if (getAudioPlayer().isPlaying()) {
                getAudioPlayer().pause();
            } else {
                getAudioPlayer().play();
            }
        }
        if (key >= '0' && key <= '9') {
            mode = key - '0';
        }
    }

    public void draw() {
        switch (mode) {
            case 0:
                alex.draw();
                break;
            case 1:
                aaron.draw();
                break;
            case 2:
                //sergei.draw();
                break;
            default:
                background(0); // Default background
                break;
        }
    }
}
