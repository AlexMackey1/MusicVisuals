package C22410766;

import ie.tudublin.*;


public class AaronVisual extends Visual{
    

    public void settings() {
        size(1024, 500);

        // Use this to make fullscreen
        // fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        // fullScreen(P3D, SPAN);
    }

    public void setup() {
        startMinim();

        // Call loadAudio to load an audio file to process
        loadAudio("MusicVisuals/java/data/QueenBohemianRhapsody.mp3");

        // Call this instead to read audio from the microphone
        startListening();

    }

    public void keyPressed() {
        if (key == '1') {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
    }

    public void draw() {
        background(0);
        circle(width/2, height/2, 200);
    }
}
