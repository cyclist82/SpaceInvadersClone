package de.awacademy.invaders;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sounds {

    private final AudioClip laserShot = new AudioClip(getClass().getResource("/audio/shoot.wav").toString());
    private final AudioClip backgroundSong = new AudioClip(getClass().getResource("/audio/spaceInvadersSong.mp3").toString());
    private final AudioClip backGroundSongSW = new AudioClip(getClass().getResource("/audio/imperialMarch.mp3").toString());
    private final AudioClip enemyIsKilled = new AudioClip(getClass().getResource("/audio/invaderkilled.wav").toString());
    private final AudioClip playerIsHit = new AudioClip(getClass().getResource("/audio/explosion.wav").toString());


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

    public boolean backRoundIsPlaying() {
        return backgroundSong.isPlaying();
    }

    public boolean backRoundSWIsPlaying() {
        return backGroundSongSW.isPlaying();
    }
}

