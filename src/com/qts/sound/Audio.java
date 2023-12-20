package com.qts.sound;
import javax.sound.sampled.*;
import java.io.*;

public class Audio {
    private Clip clip;

    public void playGuan(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }

    }//player.playBgm(("resources/audio/pvz.wav"));

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

    public void errorRun(){
        playGuan(("resources/audio/false.wav"));


    }
    public void kedaRun(){
        playGuan(("resources/audio/keda.wav"));
    }
    public void daRun(){
        playGuan(("resources/audio/da2.wav"));
    }
    public void trueRun(){
        playGuan(("resources/audio/true.wav"));
    }



//    public static void main(String[] args) {
//        Sound player = new Sound();
////        player.play("D:\\Project\\QTSDemo\\QTS\\resources\\audio\\pvz.wav");
//        player.playBgm(("resources/audio/pvz.wav"));
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        player.stop();
//    }
}