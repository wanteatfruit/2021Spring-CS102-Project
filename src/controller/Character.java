package controller;

import components.GridComponent;
import entity.GridStatus;
import entity.Player;
import minesweeper.MainFrame;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.Random;

/**
 * 为界面加入一个“发动技能”按钮，按下后发动技能 ：如何在p1回合不发动p2技能
 * 加入一个“选择角色”功能，但是初步不用实现
 * 目前假定p1=killer queen，设定只能用一次
 * 技能使用次数应该与界面大小有关（默认9*9），次数一般和雷数成正比
 *
 */
public class Character  {
    private CharacterSkill characterSkill; //skill name

    private Player chosenPlayer;


    public Character(CharacterSkill characterSkill){
        this.characterSkill=characterSkill;
    }

    public void KillerQueen(){ //add a bomb randomly, 并且不改变周围数字！ 因为改数字的话这个角色就太弱了 只能用一次

        Random random=new Random();
        ArrayList<Integer> row=new ArrayList<>();
        ArrayList<Integer> col=new ArrayList<>(); //储存可以随机放雷的格子
        GridComponent[][] gridComponents=MainFrame.controller.getGamePanel().getMineField(); //temp component used to save location of mines
        for(int i=0;i< gridComponents.length;i++){
            for(int j=0;j<gridComponents[i].length;j++){
                if(gridComponents[i][j].getContent()!=-1&&gridComponents[i][j].getStatus()== GridStatus.Covered){ //look for empty && covered grid
                    row.add(i);
                    col.add(j);
                }
            }
        }
        int targetIndex= random.nextInt(row.size());
        MainFrame.controller.getGamePanel().getMineField()[row.get(targetIndex)][col.get(targetIndex)].setContent(-1); //set the mine
        MainFrame.controller.addMine();
        MainFrame.controller.getScoreBoard().update();
    }

    public void GoldenExperience(){  //delete a bomb randomly, 可以改数字，也可以不改，并且方法也很简单
        Random random=new Random();
        ArrayList<Integer> row=new ArrayList<>();
        ArrayList<Integer> col=new ArrayList<>(); //储存可以随机放雷的格子
        GridComponent[][] gridComponents=MainFrame.controller.getGamePanel().getMineField(); //temp component used to save location of mines
        for(int i=0;i< gridComponents.length;i++){
            for(int j=0;j<gridComponents[i].length;j++){
                if(gridComponents[i][j].getContent()==-1&&gridComponents[i][j].getStatus()== GridStatus.Covered){ //look for mine && covered grid
                    row.add(i);
                    col.add(j);
                }
            }
        }
        int targetIndex= random.nextInt(row.size());
        MainFrame.controller.getGamePanel().getMineField()[row.get(targetIndex)][col.get(targetIndex)].setContent(0); //set the removed
        MainFrame.controller.minusMine();
        MainFrame.controller.getScoreBoard().update();
    }

    public void bitesTheDust(){ //reset mistake and score to 0 if left clicked on the special mine
        Random random=new Random();
        ArrayList<Integer> row=new ArrayList<>();
        ArrayList<Integer> col=new ArrayList<>(); //储存可以随机放雷的格子
        GridComponent[][] gridComponents=MainFrame.controller.getGamePanel().getMineField(); //temp component used to save location of mines
        for(int i=0;i< gridComponents.length;i++){
            for(int j=0;j<gridComponents[i].length;j++){
                if(gridComponents[i][j].getContent()!=-1&&gridComponents[i][j].getStatus()== GridStatus.Covered){ //look for empty && covered grid
                    row.add(i);
                    col.add(j);
                }
            }
        }
        int targetIndex= random.nextInt(row.size());
        MainFrame.controller.getGamePanel().getMineField()[row.get(targetIndex)][col.get(targetIndex)].setContent(-2); //set the special mine, note that component also added a -2 option
        MainFrame.controller.addMine();
        MainFrame.controller.getScoreBoard().update();
    }

    public void theWorld(){ //todo:
        MainFrame.controller.getScoreBoard().getTime().getTimer().stop();
        MainFrame.controller.getScoreBoard().getTime().getTimer().stop();
        MainFrame.controller.getScoreBoard().getTime().getTimer().stop();
        MainFrame.controller.getScoreBoard().getTime().getTimer().stop();
    }

    public void crazyDia() {
        ArrayList<Integer> row = new ArrayList<>();
        ArrayList<Integer> col = new ArrayList<>(); //记录点到的格子

        GridComponent[][] gridComponents = MainFrame.controller.getGamePanel().getMineField(); //temp component used to save location of mines

        for (int i = 0; i < gridComponents.length; i++) {
            for (int j = 0; j < gridComponents[i].length; j++) {
                if (GridComponent.getGridComponentArrayList().contains(gridComponents[i][j])){
                    gridComponents[i][j].setStatus(GridStatus.Covered);
                    gridComponents[i][j].repaint();
                }
            }
        }
        MainFrame.controller.getGamePanel().setMineField(gridComponents);
    }

    public void stickyfinger(){
        GridComponent[][] gridComponents=MainFrame.controller.getGamePanel().getMineField();
        ArrayList<Integer> freeRow = new ArrayList<>();
        ArrayList<Integer> freeCol = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < gridComponents.length; i++) {
            for (int j = 0; j < gridComponents[0].length; j++) {
                if (gridComponents[i][j].getStatus()==GridStatus.Covered){
                    freeRow.add(i);
                    freeCol.add(j);
                }
            }
        }
        int targetIndex1 = random.nextInt(freeCol.size());
        int targetIndex2 = random.nextInt(freeCol.size());
        int targetIndex3 = random.nextInt(freeCol.size());
        gridComponents[freeRow.get(targetIndex1)][freeCol.get(targetIndex1)].setStatus(GridStatus.Visible);
        gridComponents[freeRow.get(targetIndex1)][freeCol.get(targetIndex1)].repaint();
        gridComponents[freeRow.get(targetIndex2)][freeCol.get(targetIndex2)].setStatus(GridStatus.Visible);
        gridComponents[freeRow.get(targetIndex2)][freeCol.get(targetIndex2)].repaint();
        gridComponents[freeRow.get(targetIndex3)][freeCol.get(targetIndex3)].setStatus(GridStatus.Visible);
        gridComponents[freeRow.get(targetIndex3)][freeCol.get(targetIndex3)].repaint();
        MainFrame.controller.getGamePanel().setMineField(gridComponents);
    }


    public void StarPlatinum(){ //给自己多一次步数
        MainFrame.controller.getOnTurnPlayer().minusStep();
        MainFrame.controller.getOnTurnPlayer().minusStep();
    }




    public CharacterSkill getCharacterSkill() {
        return characterSkill;
    }

    public void setCharacterSkill(CharacterSkill characterSkill) {
        this.characterSkill = characterSkill;
    }
}
