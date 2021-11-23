package controller;

import components.GridComponent;
import entity.GridStatus;
import minesweeper.GamePanel;
import entity.Player;
import minesweeper.MainFrame;
import minesweeper.ScoreBoard;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class GameController {

    private Player p1;
    private Player p2;

    private Player onTurn;

    private GamePanel gamePanel;
    private ScoreBoard scoreBoard;

    private Character character;

    private int minesLeft=MainFrame.getMineCount();

    private  int roundCount=0; //记录进行的回合数，并确保第一次不触雷


    public GameController(Player p1, Player p2) {
        this.init(p1, p2);
        this.onTurn = p1;
    }

    /**
     * 初始化游戏。在开始游戏前，应先调用此方法，给予游戏必要的参数。
     *
     * @param p1 玩家1
     * @param p2 玩家2
     */
    public void init(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.onTurn = p1;
        onTurn.setStepsInRound(0);

        //TODO: 在初始化游戏的时候，还需要做什么？
    }
     //

    /**
     * 进行下一个回合时应调用本方法。
     * 在这里执行每个回合结束时需要进行的操作。
     * <p>
     * (目前这里没有每个玩家进行n回合的计数机制的，请自行修改完成哦~）
     */
    public void nextTurn() {
        //在
        if (gameOver()){
            if (p1.getScore() - p2.getScore()>0){
                System.out.printf("Game over! P1 %s is the winner!",p1.getUserName());
                JOptionPane.showMessageDialog(null,"Player 1 wins!");
            }
            else if (p1.getScore() - p2.getScore()<0){
                System.out.printf("Game over! P2 %s is the winner!",p2.getUserName());
                JOptionPane.showMessageDialog(null,"Player 2 wins!");

            }
            else{
                if (p1.getMistake()> p2.getMistake()){
                    System.out.printf("Game over! P2 %s is the winner!",p2.getUserName());
                    JOptionPane.showMessageDialog(null,"Player 2 wins!");
                }
                else if (p1.getMistake()< p2.getMistake()){
                    System.out.printf("Game over! P1 %s is the winner!",p1.getUserName());
                    JOptionPane.showMessageDialog(null,"Player 1 wins!");

                }
                else {
                    System.out.println("Game over! Draw!");
                    JOptionPane.showMessageDialog(null,"It's a draw!");
                }
            }
            GridComponent[][] gridComponents=MainFrame.controller.getGamePanel().getMineField();
            for (GridComponent[] gridComponent : gridComponents) {
                for (int j = 0; j < gridComponents[0].length; j++) {
                    if (gridComponent[j].getStatus() == GridStatus.Covered) {
                        gridComponent[j].setStatus(GridStatus.Clicked);
                        gridComponent[j].repaint();
                    }
                }
            }

        }
        else {
            scoreBoard.getTime().timerReset();
            roundCount++;
            if (onTurn == p1) {
                onTurn = p2;
                if(MainFrame.getSkillCount2()==0){
                    MainFrame.getSkillBtn2().setVisible(true);
                    MainFrame.getSkillp2().setVisible(true);
                }
                onTurn.setStepsInRound(0);
            } else if (onTurn == p2) {
                onTurn = p1;
                if(MainFrame.getSkillCount1()==0){
                    MainFrame.getSkillBtn1().setVisible(true);
                    MainFrame.getSkillp1().setVisible(true);
                }

                onTurn.setStepsInRound(0);
            }
            System.out.println("Now it is " + onTurn.getUserName() + "'s turn.");
            scoreBoard.update();
            //TODO: 在每个回合结束的时候，还需要做什么 (例如...检查游戏是否结束？)
        }
    }

    public boolean gameOver(){
        boolean output = false;
        if (minesLeft > 0) {
            if (Math.abs(p1.getScore() - p2.getScore()) > minesLeft){
                output = true;
            }
        }
        if(minesLeft==0){
            output=true;
        }
        /*else {
            if (p1.getScore() != p2.getScore()){
                output = true;
            }
            else {
                if (p1.getMistake() != p1.getMistake()){
                    output = true;
                }
            }
        }*/
        return output;
    }

    public void reset(){
        GridComponent[][] gridComponents = MainFrame.controller.getGamePanel().getMineField();
        for (int i = 0; i < gridComponents.length; i++) {
            for (int j = 0; j < gridComponents[0].length; j++) {
                if (gridComponents[i][j].getStatus().equals(GridStatus.Clicked) ||gridComponents[i][j].getStatus().equals(GridStatus.FlaggedRight) || gridComponents[i][j].getStatus().equals(GridStatus.FlaggedWrong)){
                    gridComponents[i][j].setStatus(GridStatus.Covered);
                    gridComponents[i][j].repaint();
                }
            }
        }
        MainFrame.controller.getScoreBoard().getTime().timerReset();
        p1.setScore(0);p1.setMistake(0);
        p2.setScore(0);p2.setMistake(0);

        onTurn=p1;
        MainFrame.controller.getOnTurnPlayer().setStepsInRound(0);
        MainFrame.controller.setMinesLeft(MainFrame.getMineCount());
        MainFrame.controller.getScoreBoard().update();


    }

    public void restart(){
        reset();
        GridComponent[][] gridComponents = MainFrame.controller.getGamePanel().getMineField();
        MainFrame.controller.getGamePanel().initialGame(gridComponents[0].length,gridComponents.length,MainFrame.getMineCount());
    }

    public void reveal(){
        GridComponent[][] gridComponents = MainFrame.controller.getGamePanel().getMineField();
        for (int i = 0; i < gridComponents.length; i++) {
            for (int j = 0; j < gridComponents[0].length; j++) {
                if (gridComponents[i][j].getStatus()==GridStatus.Covered){
                    gridComponents[i][j].setStatus(GridStatus.Visible);
                    gridComponents[i][j].repaint();
                }
            }
        }
    }

    public void cancelReveal(){
        GridComponent[][] gridComponents = MainFrame.controller.getGamePanel().getMineField();
        for (int i = 0; i < gridComponents.length; i++) {
            for (int j = 0; j < gridComponents[0].length; j++) {
                if (gridComponents[i][j].getStatus()==GridStatus.Visible){
                    gridComponents[i][j].setStatus(GridStatus.Covered);
                    gridComponents[i][j].repaint();
                }
            }
        }
    }


        /**
         * 获取正在进行当前回合的玩家。
         *
         * @return 正在进行当前回合的玩家
         */
    public Player getOnTurnPlayer() {
        return onTurn;
    }


    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setScoreBoard(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public  int getRoundCount() {
        return roundCount;
    }

    public int getMinesLeft() {
        return minesLeft;
    }

    public void setMinesLeft(int minesLeft) {
        this.minesLeft = minesLeft;
    }

    public void minusMine(){
        minesLeft--;
    }

    public void addMine(){
        minesLeft++;
    }

    public void addStep(){

    }


    public void readFileData(String fileName) {
        //todo: read data from file

    }

    public void writeDataToFile(String fileName){
        //todo: write data into file
        //todo: write data into file
        File f = new File( fileName);
        try {
            FileWriter fw = new FileWriter(f);
            fw.append(MainFrame.controller.getP1().toString()+"\n");
            fw.append(MainFrame.controller.getP2().toString()+"\n");
            fw.append(MainFrame.controller.getMinesLeft()+"\n");

            for (int i = 0; i < MainFrame.controller.getGamePanel().getMineField().length; i++) {
                for (int j = 0; j < MainFrame.controller.getGamePanel().getMineField()[0].length; j++) {
                    fw.append(MainFrame.controller.getGamePanel().getMineField()[i][j].getContent() + " ");
                }
                fw.append("\n");
            }
            fw.append("\n");
            for (int i = 0; i < MainFrame.controller.getGamePanel().getMineField().length; i++) {
                for (int j = 0; j < MainFrame.controller.getGamePanel().getMineField()[0].length; j++) {
                    if (MainFrame.controller.getGamePanel().getMineField()[i][j].getStatus() == GridStatus.Covered) {
                        fw.append("0 ");
                    } else if (MainFrame.controller.getGamePanel().getMineField()[i][j].getStatus() == GridStatus.Clicked) {
                        fw.append("1 ");
                    } else if (MainFrame.controller.getGamePanel().getMineField()[i][j].getStatus() == GridStatus.FlaggedRight)
                        fw.append("2 ");
                    else {
                        fw.append("3");
                    }
                }
                fw.append("\n");
            }
            fw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Save failed.","Oops",JOptionPane.ERROR_MESSAGE);
        }

    }

    public void writeFile(String path, List<String> lines) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(path));
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            for (String line : lines
            ) {
                bufferedWriter.write(line);
                bufferedWriter.write(System.lineSeparator());
            }
            bufferedWriter.close();
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> readFileByNIO(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  List<String> readFile(String path) throws IOException {
        List<String> lines = new ArrayList<>();
        try {
            //read byte from hard dist -> FileInputStream ( obtains input bytes
            // * from a file in a file system.)
            //switch bytes to characters -> InputStreamReader (An InputStreamReader is a bridge from byte streams to character streams)
            //  BufferedReader
            // Reads text from a character-input stream, buffering characters so as to
            // * provide for the efficient reading of characters, arrays, and lines.

            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

//            BufferedReader bufferedReader1= new BufferedReader(new FileReader(path));
            String line;
            //read characters from bufferedReader
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"File not found");
        }
        return lines;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

    public void setOnTurn(Player onTurn) {
        this.onTurn = onTurn;
    }
}
