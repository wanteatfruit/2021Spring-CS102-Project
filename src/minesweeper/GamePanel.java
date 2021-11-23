package minesweeper;

import components.GridComponent;
import entity.GridStatus;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {
    private GridComponent[][] mineField; //格子
    private int[][] chessboard; //坐标
    private final Random random = new Random();

    /**
     * 初始化一个具有指定行列数格子、并埋放了指定雷数的雷区。
     *
     * @param xCount    count of grid in column
     * @param yCount    count of grid in row
     * @param mineCount mine count
     */
    public GamePanel(int xCount, int yCount, int mineCount) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setSize(GridComponent.gridSize * yCount, GridComponent.gridSize * xCount);


        initialGame(xCount, yCount, mineCount);

        repaint();
    }

    public void initialGame(GridComponent[][] mineField){
    }

    public void initialGame(int xCount, int yCount, int mineCount) {
        mineField = new GridComponent[xCount][yCount];


        generateChessBoard(xCount, yCount, mineCount);

        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                GridComponent gridComponent = new GridComponent(i, j);
                gridComponent.setContent(chessboard[i+1][j+1]); //用了gamepanel里的setcontent方法，即为在把content设为chessboard里对应的数字
                gridComponent.setLocation(j * GridComponent.gridSize, i * GridComponent.gridSize);
                mineField[i][j] = gridComponent;
                this.add(mineField[i][j]);
            }
        }

        setMineField(mineField);


    }


    public void generateChessBoard(int xCount, int yCount, int mineCount) { //这里是生成数字，来表示雷或者空或者数字
        chessboard = new int[xCount+2][yCount+2];  //注意边界的雷数，用了扩大棋盘
        int[][] boardWithMines=generateMines(xCount, yCount, mineCount);
        for(int i=0;i<xCount;i++){
            for(int j=0;j<yCount;j++){
                chessboard[i+1][j+1]=boardWithMines[i][j];
            }
        }
        //put numbers in
        for(int i=1;i<xCount+1;i++){
            for(int j=1;j<yCount+1;j++){
                if(chessboard[i][j]!=-1){ //if theres not a mine
                    int countMines=0;
                    if(chessboard[i-1][j]==-1) countMines++;
                    if(chessboard[i+1][j]==-1) countMines++;
                    if(chessboard[i][j+1]==-1) countMines++;
                    if(chessboard[i][j-1]==-1) countMines++;
                    if(chessboard[i+1][j+1]==-1) countMines++;
                    if(chessboard[i+1][j-1]==-1) countMines++;
                    if(chessboard[i-1][j+1]==-1) countMines++;
                    if(chessboard[i-1][j-1]==-1) countMines++;
                    chessboard[i][j]=countMines;
                }
            }
        }

    }

    public int[][] generateMines(int xCount, int yCount, int mineCount){ //x is num of row, y is num of column
        int[][] mineField=new int[xCount][yCount];
        int count=1;
        while(count<=mineCount){
            int row=random.nextInt(xCount);
            int column=random.nextInt(yCount);
            if(mineField[row][column]!=-1){
                mineField[row][column]=-1;
                count++;
            }
        }
        if(if9MineInABox(mineField)){
            generateMines(xCount, yCount, mineCount);
        }
        return mineField;
    }

    public boolean if9MineInABox(int [][] chessboard){
        for(int i=1;i<chessboard.length-1;i++){
            for(int j=1;j<chessboard[i].length-1;j++){
                int countMines=0;
                if(chessboard[i][j]==-1){
                    if(chessboard[i-1][j]==-1) countMines++;
                    if(chessboard[i+1][j]==-1) countMines++;
                    if(chessboard[i][j+1]==-1) countMines++;
                    if(chessboard[i][j-1]==-1) countMines++;
                    if(chessboard[i+1][j+1]==-1) countMines++;
                    if(chessboard[i+1][j-1]==-1) countMines++;
                    if(chessboard[i-1][j+1]==-1) countMines++;
                    if(chessboard[i-1][j-1]==-1) countMines++;
                }
                if(countMines==8) return true;
            }
        }
        return false;
    }

    public  void reinitialize(){
        initialGame(MainFrame.getxCount(),MainFrame.getyCount(),MainFrame.getMineCount());
    }


    /**
     * 获取一个指定坐标的格子。
     * 注意请不要给一个棋盘之外的坐标哦~
     *
     * @param x 第x列
     * @param y 第y行
     * @return 该坐标的格子
     */
    public GridComponent getGrid(int x, int y) {
        try {
            return mineField[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public GridComponent[][] getMineField() {
        return mineField;
    }

    public int[][] getChessboard() {
        return chessboard;
    }

    public void setMineField(GridComponent[][] mineField) {
        this.mineField = mineField;
    }



}
