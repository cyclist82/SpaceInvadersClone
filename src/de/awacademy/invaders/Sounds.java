package de.awacademy.invaders;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sounds {

    private final AudioClip laserShot = new AudioClip("file:src/de/awacademy/invaders/model/sounds/shoot.wav");
    private final AudioClip backgroundSong = new AudioClip("file:src/de/awacademy/invaders/model/sounds/spaceInvadersSong.mp3");
    private final AudioClip backGroundSongSW = new AudioClip("file:src/de/awacademy/invaders/model/sounds/ImperialMarch.mp3");
    private final AudioClip enemyIsKilled = new AudioClip("file:src/de/awacademy/invaders/model/sounds/invaderkilled.wav");
    private final AudioClip playerIsHit = new AudioClip("file:src/de/awacademy/invaders/model/sounds/explosion.wav");


    public Sounds() {
    }

    public void shootLaser() {
        laserShot.play();
    }

    public void stopBackgroundSong() {
        backgroundSong.stop();
    }

    public void playBackgroundSong() {
        backgroundSong.setCycleCount(AudioClip.INDEFINITE);
        backgroundSong.play();
    }

    public void stopBackgroundSongSW() {
        backGroundSongSW.stop();
    }

    public void playBackgroundSongSW() {
        backGroundSongSW.setCycleCount(AudioClip.INDEFINITE);
        backGroundSongSW.play();
    }

    public void playEnemyKiller() {
        enemyIsKilled.play();
    }

    public void playPlayerisHit() {
        playerIsHit.play();
    }

    // ???? wird nie true...schade
//    public boolean backRoundIsPlaying() {
//        return backgroundSong.isPlaying();
//    }
//
//    public boolean backRoundSWIsPlaying() {
//        return backGroundSongSW.isPlaying();
//    }
}

