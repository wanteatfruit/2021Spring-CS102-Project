package minesweeper;

import components.GridComponent;
import entity.Player;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 此类的对象是一个计分板容器，通过传入玩家对象，
 * 可以用update()方法实时更新玩家的分数以及失误数。
 * 目前：各种分数 每三步结算一次
 */
public class ScoreBoard extends JPanel {

    Player p1;
    Player p2;

    JLabel characterpic1=new JLabel();
    JLabel characterpic2=new JLabel();

    JLabel gif1 = new JLabel();
    JLabel gif2 = new JLabel();
    JLabel score1 = new JLabel();
    JLabel score2 = new JLabel();
    JLabel minesLeft = new JLabel();

    ImageIcon imageIconP1=new ImageIcon();
    ImageIcon imageIconP2=new ImageIcon();
    ImageIcon iconP1=new ImageIcon();
    ImageIcon iconP2=new ImageIcon();

    Time time=new Time();



    public ScoreBoard(Player p1, Player p2, int xCount, int yCount, int mineCount) throws IOException, FontFormatException {
        this.setSize(285, 225);
        this.setLayout(new BoxLayout(this,1));

        this.p1 = p1;
        this.p2 = p2;
        //set gif
        setGIFP1(); setGIFP2();
        setpicP1();
        //set timer
        time.setSize(100,5);
        time.setLocation((yCount*GridComponent.gridSize+200-250)/2,xCount * GridComponent.gridSize+240);
        score1.setFont(new Font("Arctic",Font.PLAIN,15));
        score2.setFont(new Font("Arctic",Font.PLAIN,15));
        minesLeft.setFont(new Font("Arctic",0,15));
        this.add(score1);
        this.add(score2);
        this.add(minesLeft);
        this.add(gif1);
        this.add(gif2);
        this.add(time);
        this.add(characterpic1);
        update();
    }

    public void update() {
        //set indicator
        gif1.setIcon(imageIconP1);
        gif2.setIcon(imageIconP2);
        minesLeft.setText(String.format("Mines: %d     Steps taken: %d     Round %d", MainFrame.controller.getMinesLeft(), MainFrame.controller.getOnTurnPlayer().getStepsInRound(), MainFrame.controller.getRoundCount()));


        if (MainFrame.controller.getOnTurnPlayer() == p1) {

            score1.setText(String.format("%s : %d score (+ %d mistake) ←", p1.getUserName(), p1.getScore(), p1.getMistake()));
            score2.setText(String.format("%s : %d score (+ %d mistake)", p2.getUserName(), p2.getScore(), p2.getMistake()));
            gif2.setVisible(false);
            gif1.setVisible(true);
        }
        if (MainFrame.controller.getOnTurnPlayer() == p2) {

            score2.setText(String.format("%s : %d score (+ %d mistake) ←", p2.getUserName(), p2.getScore(), p2.getMistake()));
            score1.setText(String.format("%s : %d score (+ %d mistake)", p1.getUserName(), p1.getScore(), p1.getMistake()));
            gif1.setVisible(false);
            gif2.setVisible(true);
        }

    }

    public void setGIFP1(){
        try{
            switch (MainFrame.controller.getP1().getCharacter().getCharacterSkill()){
                case Killer_Queen:
                    imageIconP1=new ImageIcon("images/kq act.gif");
                    Image image=imageIconP1.getImage();
                    image.getScaledInstance(100,50, Image.SCALE_AREA_AVERAGING);
                    imageIconP1=new ImageIcon(image);
                    break;
                case Star_Platinum:
                    imageIconP1=new ImageIcon("images/sp act.gif");
                    break;
                case Golden_Experience:
                    imageIconP1=new ImageIcon("images/ge act.gif");
                    break;
                case Bites_the_Dust:
                    imageIconP1=new ImageIcon("images/btd act.gif");
                    break;
                case The_World:
                    imageIconP1=new ImageIcon("images/tw act.gif");
                    break;
                case Crazy_Diamond:
                    imageIconP1=new ImageIcon("images/cd act.gif");
                    break;
                case Sticky_Fingers:
                    imageIconP1=new ImageIcon("images/sf act.gif");
                    break;

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setGIFP2(){
        try{
            switch (MainFrame.controller.getP2().getCharacter().getCharacterSkill()){
                case Killer_Queen:
                    imageIconP2=new ImageIcon("images/kq act.gif");
                    break;
                case Star_Platinum:
                    imageIconP2=new ImageIcon("images/sp act.gif");
                    break;
                case Golden_Experience:
                    imageIconP2=new ImageIcon("images/ge act.gif");
                    break;
                case Bites_the_Dust:
                    imageIconP2=new ImageIcon("images/btd act.gif");
                    break;
                case The_World:
                    imageIconP2=new ImageIcon("images/tw act.gif");
                    break;
                case Crazy_Diamond:
                    imageIconP2=new ImageIcon("images/cd act.gif");
                    break;
                case Sticky_Fingers:
                    imageIconP2=new ImageIcon("images/sf act.gif");
                    break;

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void setpicP1(){
        try{
            switch (MainFrame.controller.getP1().getCharacter().getCharacterSkill()){
                case Killer_Queen:
                    iconP1=new ImageIcon("C:\\Users\\Jesse\\IdeaProjects\\Minesweeper prj\\kq wait.png");
                    Image image=iconP1.getImage();
                    image.getScaledInstance(100,166, Image.SCALE_AREA_AVERAGING);
                    iconP1.setImage(image);
                    break;
                case Star_Platinum:
                    iconP1=new ImageIcon("C:\\Users\\Jesse\\IdeaProjects\\Minesweeper prj\\sp wait.png");
                    Image image2=iconP1.getImage();
                    image2.getScaledInstance(100,166, Image.SCALE_AREA_AVERAGING);
                    iconP1.setImage(image2);
                    break;
                case Golden_Experience:
                    iconP1=new ImageIcon("C:\\Users\\Jesse\\IdeaProjects\\Minesweeper prj\\ge wait.png");
                    Image image3=iconP1.getImage();
                    image3.getScaledInstance(100,166, Image.SCALE_AREA_AVERAGING);
                    iconP1.setImage(image3);
                    break;
                case Bites_the_Dust:
                    iconP1=new ImageIcon("C:\\Users\\Jesse\\IdeaProjects\\Minesweeper prj\\btd wait.png");
                    Image image4=iconP1.getImage();
                    image4.getScaledInstance(100,166, Image.SCALE_AREA_AVERAGING);
                    iconP1.setImage(image4);
                    break;
            }
            characterpic1.setIcon(iconP1);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Time getTime() {
        return time;
    }

    public ImageIcon getIconP1() {
        return iconP1;
    }
}


