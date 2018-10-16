package de.awacademy.invaders;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sounds {

    private final String audioLaserShot="file:src/de/awacademy/invaders/model/sounds/shoot.wav";
    private final String audioBackgroundSong="file:src/de/awacademy/invaders/model/sounds/spaceInvadersSong.mp3";

    public Sounds(){
    }

    public void shootLaser(){
        AudioClip audioClip=new AudioClip(audioLaserShot);
        audioClip.play();
    }

    public void playBackgroundSong(){
        AudioClip audioClip=new AudioClip(audioBackgroundSong);
        audioClip.play();
    }
}
