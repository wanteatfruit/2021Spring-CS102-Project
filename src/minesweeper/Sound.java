package minesweeper;

import controller.CharacterSkill;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class Sound extends Thread{


    public void playMusic(){
        try{
            File musicPath=new File("sounds/ilventotoro.wav");
            AudioInputStream audioInputStream= AudioSystem.getAudioInputStream(musicPath);
            Clip clip=AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void playVOSkillWhenChoosingP1(){
        try{
            CharacterSkill skill=CharacterSelection.getP1().getCharacter().getCharacterSkill();
            switch (skill){
                case Killer_Queen:
                    FileInputStream fileInputStream=new FileInputStream("sounds/KQ choose.wav");
                    AudioStream audioStream=new AudioStream(fileInputStream);
                    AudioPlayer.player.start(audioStream);
                    break;
                case Star_Platinum:
                    FileInputStream fileInputStream2=new FileInputStream("sounds/sp choose.wav");
                    AudioStream audioStream2=new AudioStream(fileInputStream2);
                    AudioPlayer.player.start(audioStream2);
                    break;
                case Golden_Experience:
                    FileInputStream fileInputStream3=new FileInputStream("sounds/ge choose.wav");
                    AudioStream audioStream3=new AudioStream(fileInputStream3);
                    AudioPlayer.player.start(audioStream3);
                    break;
                case Bites_the_Dust:
                    FileInputStream fileInputStream4=new FileInputStream("sounds/btd choose.wav");
                    AudioStream audioStream4=new AudioStream(fileInputStream4);
                    AudioPlayer.player.start(audioStream4);
                    break;
                case The_World:
                    FileInputStream fileInputStream5=new FileInputStream("sounds/tw choose.wav");
                    AudioStream audioStream5=new AudioStream(fileInputStream5);
                    AudioPlayer.player.start(audioStream5);
                    break;
                case Crazy_Diamond:
                    FileInputStream fileInputStream6=new FileInputStream("sounds/cd choose.wav");
                    AudioStream audioStream6=new AudioStream(fileInputStream6);
                    AudioPlayer.player.start(audioStream6);
                    break;
                case Sticky_Fingers:
                    FileInputStream fileInputStream7=new FileInputStream("sounds/sf choose.wav");
                    AudioStream audioStream7=new AudioStream(fileInputStream7);
                    AudioPlayer.player.start(audioStream7);
                    break;

            }
        }
        catch (NullPointerException | IOException e){
            JOptionPane.showMessageDialog(null, "You haven't selected a character.");
        }
    }

    public static void playVOSkillWhenChoosingP2(){
        try{
            switch (CharacterSelection.getP2().getCharacter().getCharacterSkill()){
                case Killer_Queen:
                    FileInputStream fileInputStream=new FileInputStream("sounds/KQ choose.wav");
                    AudioStream audioStream=new AudioStream(fileInputStream);
                    AudioPlayer.player.start(audioStream);
                    break;
                case Star_Platinum:
                    FileInputStream fileInputStream2=new FileInputStream("sounds/sp choose.wav");
                    AudioStream audioStream2=new AudioStream(fileInputStream2);
                    AudioPlayer.player.start(audioStream2);
                    break;
                case Golden_Experience:
                    FileInputStream fileInputStream3=new FileInputStream("sounds/ge choose.wav");
                    AudioStream audioStream3=new AudioStream(fileInputStream3);
                    AudioPlayer.player.start(audioStream3);
                    break;
                case Bites_the_Dust:
                    FileInputStream fileInputStream4=new FileInputStream("sounds/btd choose.wav");
                    AudioStream audioStream4=new AudioStream(fileInputStream4);
                    AudioPlayer.player.start(audioStream4);
                    break;
                case The_World:
                    FileInputStream fileInputStream5=new FileInputStream("sounds/tw choose.wav");
                    AudioStream audioStream5=new AudioStream(fileInputStream5);
                    AudioPlayer.player.start(audioStream5);
                    break;
                case Crazy_Diamond:
                    FileInputStream fileInputStream6=new FileInputStream("sounds/cd choose.wav");
                    AudioStream audioStream6=new AudioStream(fileInputStream6);
                    AudioPlayer.player.start(audioStream6);
                    break;
                case Sticky_Fingers:
                    FileInputStream fileInputStream7=new FileInputStream("sounds/sf choose.wav");
                    AudioStream audioStream7=new AudioStream(fileInputStream7);
                    AudioPlayer.player.start(audioStream7);
                    break;


            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "You haven't selected a character.");
        }
    }

    public static void playVOSkillUsedInGame(){
        try{
            switch (MainFrame.controller.getOnTurnPlayer().getCharacter().getCharacterSkill()){
                case Killer_Queen:
                    FileInputStream fileInputStream=new FileInputStream("sounds/杀手皇后.wav");
                    AudioStream audioStream=new AudioStream(fileInputStream);
                    AudioPlayer.player.start(audioStream);
                    break;
                case Star_Platinum:
                    FileInputStream fileInputStream2=new FileInputStream("sounds/sp skill.wav");
                    AudioStream audioStream2=new AudioStream(fileInputStream2);
                    AudioPlayer.player.start(audioStream2);
                    break;
                case Golden_Experience:
                    FileInputStream fileInputStream3=new FileInputStream("sounds/ge skill.wav");
                    AudioStream audioStream3=new AudioStream(fileInputStream3);
                    AudioPlayer.player.start(audioStream3);
                    break;
                case Bites_the_Dust:
                    FileInputStream fileInputStream4=new FileInputStream("sounds/btd skill.wav");
                    AudioStream audioStream4=new AudioStream(fileInputStream4);
                    AudioPlayer.player.start(audioStream4);
                    break;
                case The_World:
                    FileInputStream fileInputStream5=new FileInputStream("sounds/tw skill.wav");
                    AudioStream audioStream5=new AudioStream(fileInputStream5);
                    AudioPlayer.player.start(audioStream5);
                    break;
                case Crazy_Diamond:
                    FileInputStream fileInputStream6=new FileInputStream("sounds/cd skill.wav");
                    AudioStream audioStream6=new AudioStream(fileInputStream6);
                    AudioPlayer.player.start(audioStream6);
                    break;
                case Sticky_Fingers:
                    FileInputStream fileInputStream7=new FileInputStream("sounds/sf skill.wav");
                    AudioStream audioStream7=new AudioStream(fileInputStream7);
                    AudioPlayer.player.start(audioStream7);
                    break;


            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void playSkillSEUsedInGame(){
        try{
            switch (MainFrame.controller.getOnTurnPlayer().getCharacter().getCharacterSkill()){
                case Killer_Queen:
                    FileInputStream fileInputStream=new FileInputStream("sounds/kq skillse.wav");
                    AudioStream audioStream=new AudioStream(fileInputStream);
                    AudioPlayer.player.start(audioStream);
                    break;
                case Star_Platinum:
                    FileInputStream fileInputStream2=new FileInputStream("sounds/sp skill.wav");
                    AudioStream audioStream2=new AudioStream(fileInputStream2);
                    AudioPlayer.player.start(audioStream2);
                    break;
                case Golden_Experience:
                    FileInputStream fileInputStream3=new FileInputStream("sounds/ge skillse.wav");
                    AudioStream audioStream3=new AudioStream(fileInputStream3);
                    AudioPlayer.player.start(audioStream3);
                    break;
                case Bites_the_Dust:
                    FileInputStream fileInputStream4=new FileInputStream("sounds/btd skillse.wav");
                    AudioStream audioStream4=new AudioStream(fileInputStream4);
                    AudioPlayer.player.start(audioStream4);
                    break;
                case The_World:
                    FileInputStream fileInputStream5=new FileInputStream("sounds/tw skill.wav");
                    AudioStream audioStream5=new AudioStream(fileInputStream5);
                    AudioPlayer.player.start(audioStream5);
                    break;
                case Crazy_Diamond:
                    FileInputStream fileInputStream6=new FileInputStream("sounds/cd skill.wav");
                    AudioStream audioStream6=new AudioStream(fileInputStream6);
                    AudioPlayer.player.start(audioStream6);
                    break;
                case Sticky_Fingers:
                    FileInputStream fileInputStream7=new FileInputStream("sounds/sf skill.wav");
                    AudioStream audioStream7=new AudioStream(fileInputStream7);
                    AudioPlayer.player.start(audioStream7);
                    break;


            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void playButtonSound(){
        try{
            FileInputStream fileInputStream=new FileInputStream("sounds/杀手皇后2.wav");
            AudioStream audioStream=new AudioStream(fileInputStream);
            AudioPlayer.player.start(audioStream);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void playMineSound(){
        try{
            FileInputStream fileInputStream=new FileInputStream("sounds/DIO-3.wav");
            AudioStream audioStream=new AudioStream(fileInputStream);
            AudioPlayer.player.start(audioStream);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void playVOFlaggedWrong(){
        try{
            switch (MainFrame.controller.getOnTurnPlayer().getCharacter().getCharacterSkill()){
                case Killer_Queen:
                    FileInputStream fileInputStream=new FileInputStream("sounds/kq flagw.wav");
                    AudioStream audioStream=new AudioStream(fileInputStream);
                    AudioPlayer.player.start(audioStream);
                    break;
                case Star_Platinum:
                    FileInputStream fileInputStream2=new FileInputStream("sounds/sp flagwr.wav");
                    AudioStream audioStream2=new AudioStream(fileInputStream2);
                    AudioPlayer.player.start(audioStream2);
                    break;
                case Golden_Experience:
                    FileInputStream fileInputStream3=new FileInputStream("sounds/ge flagw.wav");
                    AudioStream audioStream3=new AudioStream(fileInputStream3);
                    AudioPlayer.player.start(audioStream3);
                    break;
                case Bites_the_Dust:
                    FileInputStream fileInputStream4=new FileInputStream("sounds/btd flagw.wav");
                    AudioStream audioStream4=new AudioStream(fileInputStream4);
                    AudioPlayer.player.start(audioStream4);
                    break;
                case The_World:
                    FileInputStream fileInputStream5=new FileInputStream("sounds/tw flagw.wav");
                    AudioStream audioStream5=new AudioStream(fileInputStream5);
                    AudioPlayer.player.start(audioStream5);
                    break;
                case Crazy_Diamond:
                    FileInputStream fileInputStream6=new FileInputStream("sounds/cd flagw.wav");
                    AudioStream audioStream6=new AudioStream(fileInputStream6);
                    AudioPlayer.player.start(audioStream6);
                    break;
                case Sticky_Fingers:
                    FileInputStream fileInputStream7=new FileInputStream("sounds/sf flagw.wav");
                    AudioStream audioStream7=new AudioStream(fileInputStream7);
                    AudioPlayer.player.start(audioStream7);
                    break;


            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void playVOFlaggedRight(){
        try{
            switch (MainFrame.controller.getOnTurnPlayer().getCharacter().getCharacterSkill()){
                case Killer_Queen:
                    FileInputStream fileInputStream=new FileInputStream("sounds/kq flagr.wav");
                    AudioStream audioStream=new AudioStream(fileInputStream);
                    AudioPlayer.player.start(audioStream);
                    break;
                case Star_Platinum:
                    FileInputStream fileInputStream2=new FileInputStream("sounds/sp flagr.wav");
                    AudioStream audioStream2=new AudioStream(fileInputStream2);
                    AudioPlayer.player.start(audioStream2);
                    break;
                case Golden_Experience:
                    FileInputStream fileInputStream3=new FileInputStream("sounds/ge flagr.wav");
                    AudioStream audioStream3=new AudioStream(fileInputStream3);
                    AudioPlayer.player.start(audioStream3);
                    break;
                case Bites_the_Dust:
                    FileInputStream fileInputStream4=new FileInputStream("sounds/btd flagr.wav");
                    AudioStream audioStream4=new AudioStream(fileInputStream4);
                    AudioPlayer.player.start(audioStream4);
                    break;
                case The_World:
                    FileInputStream fileInputStream5=new FileInputStream("sounds/tw flagr.wav");
                    AudioStream audioStream5=new AudioStream(fileInputStream5);
                    AudioPlayer.player.start(audioStream5);
                    break;
                case Crazy_Diamond:
                    FileInputStream fileInputStream6=new FileInputStream("sounds/cd flagr.wav");
                    AudioStream audioStream6=new AudioStream(fileInputStream6);
                    AudioPlayer.player.start(audioStream6);
                    break;
                case Sticky_Fingers:
                    FileInputStream fileInputStream7=new FileInputStream("sounds/sf flagr.wav");
                    AudioStream audioStream7=new AudioStream(fileInputStream7);
                    AudioPlayer.player.start(audioStream7);
                    break;


            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void playVOClickMine(){
        try{
            switch (MainFrame.controller.getOnTurnPlayer().getCharacter().getCharacterSkill()){
                case Killer_Queen:
                    FileInputStream fileInputStream=new FileInputStream("sounds/kq clickm.wav");
                    AudioStream audioStream=new AudioStream(fileInputStream);
                    AudioPlayer.player.start(audioStream);
                    break;
                case Star_Platinum:
                    FileInputStream fileInputStream2=new FileInputStream("sounds/sp clickm.wav");
                    AudioStream audioStream2=new AudioStream(fileInputStream2);
                    AudioPlayer.player.start(audioStream2);
                    break;
                case Golden_Experience:
                    FileInputStream fileInputStream3=new FileInputStream("sounds/ge clickm.wav");
                    AudioStream audioStream3=new AudioStream(fileInputStream3);
                    AudioPlayer.player.start(audioStream3);
                    break;
                case Bites_the_Dust:
                    FileInputStream fileInputStream4=new FileInputStream("sounds/btd clickm.wav");
                    AudioStream audioStream4=new AudioStream(fileInputStream4);
                    AudioPlayer.player.start(audioStream4);
                    break;
                case The_World:
                    FileInputStream fileInputStream5=new FileInputStream("sounds/tw clickm.wav");
                    AudioStream audioStream5=new AudioStream(fileInputStream5);
                    AudioPlayer.player.start(audioStream5);
                    break;
                case Crazy_Diamond:
                    FileInputStream fileInputStream6=new FileInputStream("sounds/cd clickm.wav");
                    AudioStream audioStream6=new AudioStream(fileInputStream6);
                    AudioPlayer.player.start(audioStream6);
                    break;
                case Sticky_Fingers:
                    FileInputStream fileInputStream7=new FileInputStream("sounds/sf clickm.wav");
                    AudioStream audioStream7=new AudioStream(fileInputStream7);
                    AudioPlayer.player.start(audioStream7);
                    break;


            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void playSpecialBomb(){
        try{
            FileInputStream fileInputStream=new FileInputStream("sounds/special bomb.wav");
            AudioStream audioStream=new AudioStream(fileInputStream);
            AudioPlayer.player.start(audioStream);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void winningVO(){

    }

}
