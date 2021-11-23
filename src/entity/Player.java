package entity;

import controller.Character;
import controller.CharacterSkill;
import minesweeper.MainFrame;

import java.util.Random;

public class Player {
    private static Random ran = new Random();

    private String userName;
    private int score = 0;
    private int mistake = 0;

    private Character character; //技能


    private int stepsInRound=0; //记录一个玩家一回合点击的格子次数  这里先规定一个玩家一回合可以点3个


    /**
     * 通过特定名字初始化一个玩家对象。
     * @param userName 玩家的名字
     */
    public Player(String userName){
        this.userName = userName;
    }

    /**
     * 通过默认名字初始化一个玩家对象。
     */
    public Player(){
        userName = "User#"+(ran.nextInt(9000)+1000);
    }

    public Player(String userName, Character character){
        this.userName=userName;
        this.character=character;
    }

    /**
     * 为玩家加一分。
     */
    public void addScore(){
        score++;
    }

    /**
     * 为玩家扣一分。
     */
    public void costScore(){
        score--;
    }

    /**
     * 为玩家增加一次失误数。
     */
    public void addMistake() { mistake++; }

    public int getStepsInRound() {
        return stepsInRound;
    }

    public void setStepsInRound(int stepsInRound) {
        this.stepsInRound = stepsInRound;
    }

    public void addStep(){
        stepsInRound++;
    }

    public void minusStep(){ //used for skills
        stepsInRound--;
    }

    public Character getCharacter() {
        return character;
    }

    public void setMistake(int mistake) {
        this.mistake = mistake;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public int getScore(){
        return score;
    }
    public String getUserName(){ return userName; }
    public int getMistake(){ return mistake; }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String toString() {
        if(MainFrame.controller.getOnTurnPlayer()==MainFrame.controller.getP1())
        return userName + " " + score + " " + mistake +
                " " + stepsInRound + " " + character.getCharacterSkill().toString()+" "+1 ;

        else return userName + " " + score + " " + mistake +
                " " + stepsInRound + " " + character.getCharacterSkill().toString()+" "+2 ;
    }

}
