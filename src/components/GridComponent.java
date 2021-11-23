package components;

import entity.GridStatus;
import minesweeper.MainFrame;
import minesweeper.Sound;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GridComponent extends BasicComponent { //这个类对应单个格子
    public static int gridSize = 30; //一个方块大小


    private int row;
    private int col;
    private GridStatus status = GridStatus.Covered;
    private int content = 0;

    private static ArrayList<Integer> Roundrowlist=new ArrayList<>();
    private static ArrayList<Integer> Roundcollist=new ArrayList<>();
    private static ArrayList<GridComponent> gridComponentArrayList=new ArrayList<>();
    //save clicked in every round, reset when new round


    private ArrayList<Integer> newRow = new ArrayList<>();
    private ArrayList<Integer> newCol = new ArrayList<>();


    public GridComponent(int row, int column) {
        this.setSize(gridSize, gridSize);
        this.row = row;
        this.col = column;
    }

    public GridComponent(int row, int column, GridStatus status, int content){

    }

    //todo:cant click a already clicked button
    @Override
    public void onMouseLeftClicked() {
        if (this.status == GridStatus.Mouse) this.status = GridStatus.Covered;
        System.out.printf("Gird (%d,%d) is left-clicked.\n", row, col);
        if (this.status != GridStatus.Covered && this.status != GridStatus.Visible) {
            System.out.println("This grid has been clicked.");
        } else {
            if (this.getContent() == -1 && MainFrame.controller.getRoundCount() == 0 && MainFrame.controller.getOnTurnPlayer().getStepsInRound() == 0) { //如果第一回合点到雷
                GridComponent[][] gridComponents = MainFrame.controller.getGamePanel().getMineField();
                //移走点到格子的雷
                if (row != 0 && row != gridComponents.length && col != 0 && col != gridComponents[0].length) {
                    int mineCountOriginal = 0;
                    if (gridComponents[row - 1][col].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row + 1][col].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row][col - 1].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row][col + 1].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row - 1][col - 1].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row - 1][col + 1].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row + 1][col + 1].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row + 1][col - 1].getContent() == -1) mineCountOriginal++;
                    gridComponents[row][col].setContent(mineCountOriginal);
                    if (gridComponents[row - 1][col].getContent() != -1)
                        gridComponents[row - 1][col].setContent(gridComponents[row - 1][col].getContent() - 1);
                    if (gridComponents[row + 1][col].getContent() != -1)
                        gridComponents[row + 1][col].setContent(gridComponents[row + 1][col].getContent() - 1);
                    if (gridComponents[row][col - 1].getContent() != -1)
                        gridComponents[row][col - 1].setContent(gridComponents[row][col - 1].getContent() - 1);
                    if (gridComponents[row][col + 1].getContent() != -1)
                        gridComponents[row][col + 1].setContent(gridComponents[row][col + 1].getContent() - 1);
                    if (gridComponents[row - 1][col - 1].getContent() != -1)
                        gridComponents[row - 1][col - 1].setContent(gridComponents[row - 1][col - 1].getContent() - 1);
                    if (gridComponents[row - 1][col + 1].getContent() != -1)
                        gridComponents[row - 1][col + 1].setContent(gridComponents[row - 1][col + 1].getContent() - 1);
                    if (gridComponents[row + 1][col + 1].getContent() != -1)
                        gridComponents[row + 1][col + 1].setContent(gridComponents[row + 1][col + 1].getContent() - 1);
                    if (gridComponents[row + 1][col - 1].getContent() != -1)
                        gridComponents[row + 1][col - 1].setContent(gridComponents[row + 1][col - 1].getContent() - 1);
                }


                //点到上边界，不包括角落
                if (col != 0 && col != gridComponents[0].length && row == 0) {
                    int mineCountOriginal = 0;
                    if (gridComponents[row + 1][col].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row][col - 1].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row][col + 1].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row + 1][col + 1].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row + 1][col - 1].getContent() == -1) mineCountOriginal++;
                    gridComponents[row][col].setContent(mineCountOriginal);
                    if (gridComponents[row + 1][col].getContent() != -1)
                        gridComponents[row + 1][col].setContent(gridComponents[row + 1][col].getContent() - 1);
                    if (gridComponents[row][col - 1].getContent() != -1)
                        gridComponents[row][col - 1].setContent(gridComponents[row][col - 1].getContent() - 1);
                    if (gridComponents[row][col + 1].getContent() != -1)
                        gridComponents[row][col + 1].setContent(gridComponents[row][col + 1].getContent() - 1);
                    if (gridComponents[row + 1][col + 1].getContent() != -1)
                        gridComponents[row + 1][col + 1].setContent(gridComponents[row + 1][col + 1].getContent() - 1);
                    if (gridComponents[row + 1][col - 1].getContent() != -1)
                        gridComponents[row + 1][col - 1].setContent(gridComponents[row + 1][col - 1].getContent() - 1);

                }

                //点到左边界，不包括角落
                if (row != 0 && row != gridComponents.length && col == 0) {
                    int mineCountOriginal = 0;
                    if (gridComponents[row - 1][col].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row + 1][col].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row][col + 1].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row - 1][col + 1].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row + 1][col + 1].getContent() == -1) mineCountOriginal++;
                    gridComponents[row][col].setContent(mineCountOriginal);
                    if (gridComponents[row - 1][col].getContent() != -1)
                        gridComponents[row - 1][col].setContent(gridComponents[row - 1][col].getContent() - 1);
                    if (gridComponents[row + 1][col].getContent() != -1)
                        gridComponents[row + 1][col].setContent(gridComponents[row + 1][col].getContent() - 1);
                    if (gridComponents[row][col + 1].getContent() != -1)
                        gridComponents[row][col + 1].setContent(gridComponents[row][col + 1].getContent() - 1);
                    if (gridComponents[row - 1][col + 1].getContent() != -1)
                        gridComponents[row - 1][col + 1].setContent(gridComponents[row - 1][col + 1].getContent() - 1);
                    if (gridComponents[row + 1][col + 1].getContent() != -1)
                        gridComponents[row + 1][col + 1].setContent(gridComponents[row + 1][col + 1].getContent() - 1);

                }
                //bottom row
                if (row == gridComponents.length && col != 0 && col != gridComponents[0].length) {
                    int mineCountOriginal = 0;
                    if (gridComponents[row - 1][col].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row][col - 1].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row][col + 1].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row - 1][col - 1].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row - 1][col + 1].getContent() == -1) mineCountOriginal++;
                    gridComponents[row][col].setContent(mineCountOriginal);
                    if (gridComponents[row - 1][col].getContent() != -1)
                        gridComponents[row - 1][col].setContent(gridComponents[row - 1][col].getContent() - 1);
                    if (gridComponents[row][col - 1].getContent() != -1)
                        gridComponents[row][col - 1].setContent(gridComponents[row][col - 1].getContent() - 1);
                    if (gridComponents[row][col + 1].getContent() != -1)
                        gridComponents[row][col + 1].setContent(gridComponents[row][col + 1].getContent() - 1);
                    if (gridComponents[row - 1][col - 1].getContent() != -1)
                        gridComponents[row - 1][col - 1].setContent(gridComponents[row - 1][col - 1].getContent() - 1);
                    if (gridComponents[row - 1][col + 1].getContent() != -1)
                        gridComponents[row - 1][col + 1].setContent(gridComponents[row - 1][col + 1].getContent() - 1);

                }
                //right col
                if (col == gridComponents[0].length && row != 0 && row != gridComponents.length) {
                    int mineCountOriginal = 0;
                    if (gridComponents[row - 1][col].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row + 1][col].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row][col - 1].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row - 1][col - 1].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row + 1][col - 1].getContent() == -1) mineCountOriginal++;
                    gridComponents[row][col].setContent(mineCountOriginal);
                    if (gridComponents[row - 1][col].getContent() != -1)
                        gridComponents[row - 1][col].setContent(gridComponents[row - 1][col].getContent() - 1);
                    if (gridComponents[row + 1][col].getContent() != -1)
                        gridComponents[row + 1][col].setContent(gridComponents[row + 1][col].getContent() - 1);
                    if (gridComponents[row][col - 1].getContent() != -1)
                        gridComponents[row][col - 1].setContent(gridComponents[row][col - 1].getContent() - 1);
                    if (gridComponents[row - 1][col - 1].getContent() != -1)
                        gridComponents[row - 1][col - 1].setContent(gridComponents[row - 1][col - 1].getContent() - 1);
                    if (gridComponents[row + 1][col - 1].getContent() != -1)
                        gridComponents[row + 1][col - 1].setContent(gridComponents[row + 1][col - 1].getContent() - 1);
                }
                //点到左上
                if (row == 0 && col == 0) {
                    int mineCountOriginal = 0;
                    if (gridComponents[row + 1][col].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row][col + 1].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row + 1][col + 1].getContent() == -1) mineCountOriginal++;
                    gridComponents[row][col].setContent(mineCountOriginal);
                    if (gridComponents[row + 1][col].getContent() != -1)
                        gridComponents[row + 1][col].setContent(gridComponents[row + 1][col].getContent() - 1);
                    if (gridComponents[row][col + 1].getContent() != -1)
                        gridComponents[row][col + 1].setContent(gridComponents[row][col + 1].getContent() - 1);
                    if (gridComponents[row + 1][col + 1].getContent() != -1)
                        gridComponents[row + 1][col + 1].setContent(gridComponents[row + 1][col + 1].getContent() - 1);
                }
                //bottom right
                if (row == gridComponents.length && col == gridComponents[0].length) {
                    int mineCountOriginal = 0;
                    if (gridComponents[row - 1][col].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row][col - 1].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row - 1][col - 1].getContent() == -1) mineCountOriginal++;
                    gridComponents[row][col].setContent(mineCountOriginal);
                    if (gridComponents[row - 1][col].getContent() != -1)
                        gridComponents[row - 1][col].setContent(gridComponents[row - 1][col].getContent() - 1);
                    if (gridComponents[row][col - 1].getContent() != -1)
                        gridComponents[row][col - 1].setContent(gridComponents[row][col - 1].getContent() - 1);
                    if (gridComponents[row - 1][col - 1].getContent() != -1)
                        gridComponents[row - 1][col - 1].setContent(gridComponents[row - 1][col - 1].getContent() - 1);
                }
                //bottom left
                if (row == gridComponents.length && col == 0) {
                    int mineCountOriginal = 0;
                    if (gridComponents[row - 1][col].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row][col + 1].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row - 1][col + 1].getContent() == -1) mineCountOriginal++;
                    gridComponents[row][col].setContent(mineCountOriginal);
                    if (gridComponents[row - 1][col].getContent() != -1)
                        gridComponents[row - 1][col].setContent(gridComponents[row - 1][col].getContent() - 1);
                    if (gridComponents[row][col + 1].getContent() != -1)
                        gridComponents[row][col + 1].setContent(gridComponents[row][col + 1].getContent() - 1);
                    if (gridComponents[row - 1][col + 1].getContent() != -1)
                        gridComponents[row - 1][col + 1].setContent(gridComponents[row - 1][col + 1].getContent() - 1);
                }
                //up right
                if (row == 0 && col == gridComponents[0].length) {
                    int mineCountOriginal = 0;
                    if (gridComponents[row + 1][col].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row][col - 1].getContent() == -1) mineCountOriginal++;
                    if (gridComponents[row + 1][col - 1].getContent() == -1) mineCountOriginal++;
                    gridComponents[row][col].setContent(mineCountOriginal);
                    if (gridComponents[row + 1][col].getContent() != -1)
                        gridComponents[row + 1][col].setContent(gridComponents[row + 1][col].getContent() - 1);
                    if (gridComponents[row][col - 1].getContent() != -1)
                        gridComponents[row][col - 1].setContent(gridComponents[row][col - 1].getContent() - 1);
                    if (gridComponents[row + 1][col - 1].getContent() != -1)
                        gridComponents[row + 1][col - 1].setContent(gridComponents[row + 1][col - 1].getContent() - 1);
                }
                //寻找可以放这个雷的格子
                Random random = new Random();
                ArrayList<Integer> rowList = new ArrayList<>();
                ArrayList<Integer> colList = new ArrayList<>(); //储存可以随机放雷的格子
                for (int i = 0; i < gridComponents.length; i++) {
                    for (int j = 0; j < gridComponents[i].length; j++) {
                        if (gridComponents[i][j].getContent() != -1 && i > 0 && i < gridComponents.length - 1 && j > 0 && j < gridComponents[0].length - 1) {
                            rowList.add(i);
                            colList.add(j);
                        }
                    }
                }
                int targetIndex = random.nextInt(rowList.size());
                int targetRow = 0;
                int targetCol = 0;

                //防越界
                targetIndex = random.nextInt(rowList.size());
                targetRow = rowList.get(targetIndex);
                targetCol = colList.get(targetCol);


                gridComponents[targetRow][targetCol].setContent(-1);

                //改变周围数字
                if (gridComponents[targetRow + 1][targetCol].getContent() != -1) {
                    gridComponents[targetRow + 1][targetCol].setContent(gridComponents[targetRow + 1][targetCol].getContent() + 1);
                }
                if (gridComponents[targetRow - 1][targetCol].getContent() != -1) {
                    gridComponents[targetRow - 1][targetCol].setContent(gridComponents[targetRow - 1][targetCol].getContent() + 1);
                }
                if (gridComponents[targetRow][targetCol + 1].getContent() != -1)
                    gridComponents[targetRow][targetCol + 1].setContent(gridComponents[targetRow][targetCol + 1].getContent() + 1);
                if (gridComponents[targetRow][targetCol - 1].getContent() != -1)
                    gridComponents[targetRow][targetCol - 1].setContent(gridComponents[targetRow][targetCol - 1].getContent() + 1);
                if (gridComponents[targetRow + 1][targetCol + 1].getContent() != -1)
                    gridComponents[targetRow + 1][targetCol + 1].setContent(gridComponents[targetRow + 1][targetCol + 1].getContent() + 1);
                if (gridComponents[targetRow + 1][targetCol - 1].getContent() != -1)
                    gridComponents[targetRow + 1][targetCol - 1].setContent(gridComponents[targetRow + 1][targetCol - 1].getContent() + 1);
                if (gridComponents[targetRow - 1][targetCol + 1].getContent() != -1)
                    gridComponents[targetRow - 1][targetCol + 1].setContent(gridComponents[targetRow - 1][targetCol + 1].getContent() + 1);
                if (gridComponents[targetRow - 1][targetCol - 1].getContent() != -1)
                    gridComponents[targetRow - 1][targetCol - 1].setContent(gridComponents[targetRow - 1][targetCol - 1].getContent() + 1);
                MainFrame.controller.getGamePanel().setMineField(gridComponents);
            }
               /*else if(this.getContent()==0) {
                for (int i = 0; i < newRow.size(); i++) {
                    extend(newRow.get(i), newCol.get(i));
                }

                MainFrame.controller.getScoreBoard().update();
                gridComponentArrayList.add(this);
            }*/

            else if (this.getContent() == -1) { //if its a mine or special mine
                MainFrame.controller.getOnTurnPlayer().costScore();
                System.out.println("You clicked on a mine!");
                this.status = GridStatus.Clicked;
                repaint();
                Sound.playMineSound();
                Sound.playVOClickMine();
                MainFrame.controller.getOnTurnPlayer().addStep();
                int mines = MainFrame.controller.getMinesLeft();
                MainFrame.controller.setMinesLeft(mines - 1);
                MainFrame.controller.getScoreBoard().update();
                gridComponentArrayList.add(this);

            } else if (this.getContent() == -2) {
                MainFrame.controller.getOnTurnPlayer().setScore(0);
                MainFrame.controller.getOnTurnPlayer().setMistake(0);
                System.out.println("Bites the dust.");
                this.status = GridStatus.Clicked;
                repaint();
                Sound.playSpecialBomb();
                MainFrame.controller.getOnTurnPlayer().addStep();
                int mines = MainFrame.controller.getMinesLeft();
                MainFrame.controller.setMinesLeft(mines - 1);
                MainFrame.controller.getScoreBoard().update();
                gridComponentArrayList.add(this);


            } else if (this.getContent() == 0) {
                newRow.add(row);
                newCol.add(col);
                this.status=GridStatus.Clicked;

                for (int i = 0; i < newRow.size(); i++) {
                    extend(newRow.get(i), newCol.get(i));
                }
                repaint();

                MainFrame.controller.getOnTurnPlayer().addStep();
                Sound.playButtonSound();
                MainFrame.controller.getScoreBoard().update();
                gridComponentArrayList.add(this);


            } else {

                this.status = GridStatus.Clicked;
                repaint();
                MainFrame.controller.getOnTurnPlayer().addStep();
                Sound.playButtonSound();
                MainFrame.controller.getScoreBoard().update();
                gridComponentArrayList.add(this);

            }

        }
        if (MainFrame.controller.getOnTurnPlayer().getStepsInRound() == 3) {
            MainFrame.controller.nextTurn();
            Roundrowlist.clear(); Roundcollist.clear();
            gridComponentArrayList.clear();
        }

    }

    @Override
    public void onMouseRightClicked() {
        if (this.status == GridStatus.Mouse) this.status = GridStatus.Covered;

        System.out.printf("Gird (%d,%d) is right-clicked.\n", row, col);
        if (this.status != GridStatus.Covered && this.status != GridStatus.Visible) {
            System.out.println("This grid has been clicked.");
        } else {

            if (this.content == -1 || this.content == -2) { //if a mine

                MainFrame.controller.getOnTurnPlayer().addScore();
                System.out.println("A mine has been discovered!");
                this.status = GridStatus.FlaggedRight;
                int mines = MainFrame.controller.getMinesLeft();
                MainFrame.controller.setMinesLeft(mines - 1);
                MainFrame.controller.getOnTurnPlayer().addStep();
                MainFrame.controller.getScoreBoard().update();
                Sound.playVOFlaggedRight();

                repaint();
            } else {
                //if not a mine
                MainFrame.controller.getOnTurnPlayer().addMistake();
                JOptionPane.showMessageDialog(null, String.format("It's not a mine! It's number is %d", getContent()));
                this.status = GridStatus.FlaggedWrong;

                repaint();
                Sound.playVOFlaggedWrong();
                MainFrame.controller.getOnTurnPlayer().addStep();
                MainFrame.controller.getScoreBoard().update();

                Roundrowlist.add(row); Roundcollist.add(col);
                //gridComponentArrayList.add(this);

            }

            if (MainFrame.controller.getOnTurnPlayer().getStepsInRound() == 3) {
                MainFrame.controller.nextTurn();
                Roundcollist.clear();Roundrowlist.clear();
                gridComponentArrayList.clear();
            }
        }

    }

    @Override
    public void onMouseMiddle() { //彩蛋
        JOptionPane.showMessageDialog(null, "Whoopsie");
    }

    @Override
    public void mouseEnter() {
        if (this.status == GridStatus.Covered || this.status == GridStatus.Visible) {
            this.status = GridStatus.Mouse;
            repaint();
        }
    }

    @Override
    public void mouseExit() {
        if (this.status == GridStatus.Mouse) {
            this.status = GridStatus.Covered;
            repaint();
        }
    }


    public void draw(Graphics g) {

        if (this.status == GridStatus.Mouse) {
            Image image = Toolkit.getDefaultToolkit().getImage("resources/10 mouseon.png");
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }

        else if (this.status == GridStatus.Covered) {
            Image image = Toolkit.getDefaultToolkit().getImage("resources/10.png");
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
        else if (this.status == GridStatus.Clicked) {
            if (getContent() == -2) {
                Image image = Toolkit.getDefaultToolkit().getImage("resources/special bomb.png");
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
            if (getContent() == -1) {
                Image image = Toolkit.getDefaultToolkit().getImage("resources/9.png");
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

            }
            if (getContent() == 0) {
                Image image = Toolkit.getDefaultToolkit().getImage("resources/0.png");
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
            if (getContent() == 1) {
                Image image = Toolkit.getDefaultToolkit().getImage("resources/1.png");
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
            if (getContent() == 2) {
                Image image = Toolkit.getDefaultToolkit().getImage("resources/2.png");
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
            if (getContent() == 3) {
                Image image = Toolkit.getDefaultToolkit().getImage("resources/3.png");
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
            if (getContent() == 4) {
                Image image = Toolkit.getDefaultToolkit().getImage("resources/4.png");
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

            }
            if (getContent() == 5) {
                Image image = Toolkit.getDefaultToolkit().getImage("resources/5.png");
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

            }
            if (getContent() == 6) {
                Image image = Toolkit.getDefaultToolkit().getImage("resources/6.png");
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

            }
            if (getContent() == 7) {
                Image image = Toolkit.getDefaultToolkit().getImage("resources/7.png");
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

            }
            if (getContent() == 8) {
                Image image = Toolkit.getDefaultToolkit().getImage("resources/8.png");
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

            }
        }
        else if (this.status == GridStatus.FlaggedRight) {
            Image image = Toolkit.getDefaultToolkit().getImage("resources/11.png");
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

        }
        else if (this.status == GridStatus.FlaggedWrong) {
            Image image = Toolkit.getDefaultToolkit().getImage("resources/12.png");
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
        else if (this.status == GridStatus.Visible) {
            if (getContent() == -2) {
                Image image = Toolkit.getDefaultToolkit().getImage("resources/special bomb.png");
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
            if (getContent() == -1) {
                Image image = Toolkit.getDefaultToolkit().getImage("resources/9 re.png");
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
            if (getContent() == 0) {
                Image image = Toolkit.getDefaultToolkit().getImage("resources/0.png");
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
            if (getContent() == 1) {
                Image image = Toolkit.getDefaultToolkit().getImage("resources/1 re.png");
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
            if (getContent() == 2) {
                Image image = Toolkit.getDefaultToolkit().getImage("resources/2 re.png");
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
            if (getContent() == 3) {
                Image image = Toolkit.getDefaultToolkit().getImage("resources/3 re.png");
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
            if (getContent() == 4) {
                Image image = Toolkit.getDefaultToolkit().getImage("resources/4 re.png");
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

            }
            if (getContent() == 5) {
                Image image = Toolkit.getDefaultToolkit().getImage("resources/5 re.png");
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

            }
            if (getContent() == 6) {
                Image image = Toolkit.getDefaultToolkit().getImage("resources/6 re.png");
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

            }
            if (getContent() == 7) {
                Image image = Toolkit.getDefaultToolkit().getImage("resources/7 re.png");
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

            }
            if (getContent() == 8) {
                Image image = Toolkit.getDefaultToolkit().getImage("resources/8 re.png");
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

            }
        }
    }

    public void setContent(int content) {
        this.content = content;
    }

    public int getContent() {
        return content;
    }

    public GridStatus getStatus() {
        return status;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        draw(g);
    }

    public void setStatus(GridStatus status) {
        this.status = status;
    }

    public void extend(int row, int col) { //连续打开格子
        GridComponent[][] gridComponents = MainFrame.controller.getGamePanel().getMineField();
        if (row == 0 && col != 0 && col != gridComponents[0].length - 1) {
            if (gridComponents[row][col - 1].getContent() != -1 && gridComponents[row][col - 1].status == GridStatus.Covered) {
                gridComponents[row][col - 1].status = GridStatus.Clicked;
                gridComponents[row][col - 1].repaint();
                if (gridComponents[row][col - 1].getContent() == 0) {
                    newRow.add(row);
                    newCol.add(col - 1);
                }
            }
            if (gridComponents[row][col + 1].getContent() != -1 && gridComponents[row]
                    [col + 1].status == GridStatus.Covered) {
                gridComponents[row][col + 1].status = GridStatus.Clicked;
                gridComponents[row][col + 1].repaint();
                if (gridComponents[row][col + 1].getContent() == 0) {
                    newRow.add(row);
                    newCol.add(col + 1);
                }
            }
            if (gridComponents[row + 1][col - 1].getContent() != -1 && gridComponents[row + 1]
                    [col - 1].status == GridStatus.Covered) {
                gridComponents[row + 1][col - 1].status = GridStatus.Clicked;
                gridComponents[row + 1][col - 1].repaint();
                if (gridComponents[row + 1][col - 1].getContent() == 0) {
                    newRow.add(row + 1);
                    newCol.add(col - 1);
                }
            }
            if (gridComponents[row + 1][col].getContent() != -1 && gridComponents[row + 1]
                    [col].status == GridStatus.Covered) {
                gridComponents[row + 1][col].status = GridStatus.Clicked;
                gridComponents[row + 1][col].repaint();
                if (gridComponents[row + 1][col].getContent() == 0) {
                    newRow.add(row + 1);
                    newCol.add(col);
                }
            }
            if (gridComponents[row + 1][col + 1].getContent() != -1 && gridComponents[row + 1]
                    [col + 1].status == GridStatus.Covered) {
                gridComponents[row + 1][col + 1].status = GridStatus.Clicked;
                gridComponents[row + 1][col + 1].repaint();
                if (gridComponents[row + 1][col + 1].getContent() == 0) {
                    newRow.add(row + 1);
                    newCol.add(col + 1);
                }
            }
        } else if (col == 0 && row != 0 && row != gridComponents.length - 1) {
            if (gridComponents[row - 1][col].getContent() != -1 && gridComponents[row - 1][col].status == GridStatus.Covered) {
                gridComponents[row - 1][col].status = GridStatus.Clicked;
                gridComponents[row - 1][col].repaint();
                if (gridComponents[row - 1][col].getContent() == 0) {
                    newRow.add(row - 1);
                    newCol.add(col);
                }
            }
            if (gridComponents[row + 1][col].getContent() != -1 && gridComponents[row + 1]
                    [col].status == GridStatus.Covered) {
                gridComponents[row + 1][col].status = GridStatus.Clicked;
                gridComponents[row + 1][col].repaint();
                if (gridComponents[row + 1][col].getContent() == 0) {
                    newRow.add(row + 1);
                    newCol.add(col);
                }
            }
            if (gridComponents[row - 1][col + 1].getContent() != -1 && gridComponents[row - 1][col + 1].status == GridStatus.Covered) {
                gridComponents[row - 1][col + 1].status = GridStatus.Clicked;
                gridComponents[row - 1][col + 1].repaint();
                if (gridComponents[row - 1][col + 1].getContent() == 0) {
                    newRow.add(row - 1);
                    newCol.add(col + 1);
                }
            }
            if (gridComponents[row][col + 1].getContent() != -1 && gridComponents[row]
                    [col + 1].status == GridStatus.Covered) {
                gridComponents[row][col + 1].status = GridStatus.Clicked;
                gridComponents[row][col + 1].repaint();
                if (gridComponents[row][col + 1].getContent() == 0) {
                    newRow.add(row);
                    newCol.add(col + 1);
                }
            }
            if (gridComponents[row + 1][col + 1].getContent() != -1 && gridComponents[row + 1]
                    [col + 1].status == GridStatus.Covered) {
                gridComponents[row + 1][col + 1].status = GridStatus.Clicked;
                gridComponents[row + 1][col + 1].repaint();
                if (gridComponents[row + 1][col + 1].getContent() == 0) {
                    newRow.add(row + 1);
                    newCol.add(col + 1);
                }
            }
        } else if (col + row == 0) {
            if (gridComponents[row][col + 1].getContent() != -1 && gridComponents[row]
                    [col + 1].status == GridStatus.Covered) {
                gridComponents[row][col + 1].status = GridStatus.Clicked;
                gridComponents[row][col + 1].repaint();
                if (gridComponents[row][col + 1].getContent() == 0) {
                    newRow.add(row);
                    newCol.add(col + 1);
                }
            }
            if (gridComponents[row + 1][col].getContent() != -1 && gridComponents[row + 1]
                    [col].status == GridStatus.Covered) {
                gridComponents[row + 1][col].status = GridStatus.Clicked;
                gridComponents[row + 1][col].repaint();
                if (gridComponents[row + 1][col].getContent() == 0) {
                    newRow.add(row + 1);
                    newCol.add(col);
                }
            }
            if (gridComponents[row + 1][col + 1].getContent() != -1 && gridComponents[row + 1]
                    [col + 1].status == GridStatus.Covered) {
                gridComponents[row + 1][col + 1].status = GridStatus.Clicked;
                gridComponents[row + 1][col + 1].repaint();
                if (gridComponents[row + 1][col + 1].getContent() == 0) {
                    newRow.add(row + 1);
                    newCol.add(col + 1);
                }
            }
        } else if (row == gridComponents.length - 1 && col < gridComponents[0].length - 1 && col > 0) {
            if (gridComponents[row][col - 1].getContent() != -1 && gridComponents[row][col - 1].status == GridStatus.Covered) {
                gridComponents[row][col - 1].status = GridStatus.Clicked;
                gridComponents[row][col - 1].repaint();
                if (gridComponents[row][col - 1].getContent() == 0) {
                    newRow.add(row);
                    newCol.add(col - 1);
                }
            }
            if (gridComponents[row][col + 1].getContent() != -1 && gridComponents[row]
                    [col + 1].status == GridStatus.Covered) {
                gridComponents[row][col + 1].status = GridStatus.Clicked;
                gridComponents[row][col + 1].repaint();
                if (gridComponents[row][col + 1].getContent() == 0) {
                    newRow.add(row);
                    newCol.add(col + 1);
                }
            }
            if (gridComponents[row - 1][col - 1].getContent() != -1 && gridComponents[row - 1]
                    [col - 1].status == GridStatus.Covered) {
                gridComponents[row - 1][col - 1].status = GridStatus.Clicked;
                gridComponents[row - 1][col - 1].repaint();
                if (gridComponents[row - 1][col - 1].getContent() == 0) {
                    newRow.add(row - 1);
                    newCol.add(col - 1);
                }
            }
            if (gridComponents[row - 1][col].getContent() != -1 && gridComponents[row - 1][col].status == GridStatus.Covered) {
                gridComponents[row - 1][col].status = GridStatus.Clicked;
                gridComponents[row - 1][col].repaint();
                if (gridComponents[row - 1][col].getContent() == 0) {
                    newRow.add(row - 1);
                    newCol.add(col);
                }
            }
            if (gridComponents[row - 1][col + 1].getContent() != -1 && gridComponents[row - 1]
                    [col + 1].status == GridStatus.Covered) {
                gridComponents[row - 1][col + 1].status = GridStatus.Clicked;
                gridComponents[row - 1][col + 1].repaint();
                if (gridComponents[row - 1][col + 1].getContent() == 0) {
                    newRow.add(row - 1);
                    newCol.add(col + 1);
                }
            }
        } else if (row < gridComponents.length - 1 && row > 0 && col == gridComponents[0].length - 1) {
            if (gridComponents[row - 1][col].getContent() != -1 && gridComponents[row - 1][col].status == GridStatus.Covered) {
                gridComponents[row - 1][col].status = GridStatus.Clicked;
                gridComponents[row - 1][col].repaint();
                if (gridComponents[row - 1][col].getContent() == 0) {
                    newRow.add(row - 1);
                    newCol.add(col);
                }
            }
            if (gridComponents[row + 1][col].getContent() != -1 && gridComponents[row + 1]
                    [col].status == GridStatus.Covered) {
                gridComponents[row + 1][col].status = GridStatus.Clicked;
                gridComponents[row + 1][col].repaint();
                if (gridComponents[row + 1][col].getContent() == 0) {
                    newRow.add(row + 1);
                    newCol.add(col);
                }
            }
            if (gridComponents[row - 1][col - 1].getContent() != -1 && gridComponents[row - 1]
                    [col - 1].status == GridStatus.Covered) {
                gridComponents[row - 1][col - 1].status = GridStatus.Clicked;
                gridComponents[row - 1][col - 1].repaint();
                if (gridComponents[row - 1][col - 1].getContent() == 0) {
                    newRow.add(row - 1);
                    newCol.add(col - 1);
                }
            }
            if (gridComponents[row][col - 1].getContent() != -1 && gridComponents[row][col - 1].status == GridStatus.Covered) {
                gridComponents[row][col - 1].status = GridStatus.Clicked;
                gridComponents[row][col - 1].repaint();
                if (gridComponents[row][col - 1].getContent() == 0) {
                    newRow.add(row);
                    newCol.add(col - 1);
                }
            }
            if (gridComponents[row + 1][col - 1].getContent() != -1 && gridComponents[row + 1]
                    [col - 1].status == GridStatus.Covered) {
                gridComponents[row + 1][col - 1].status = GridStatus.Clicked;
                gridComponents[row + 1][col - 1].repaint();
                if (gridComponents[row + 1][col - 1].getContent() == 0) {
                    newRow.add(row + 1);
                    newCol.add(col - 1);
                }
            }
        } else if (row + col == gridComponents.length - 1 + gridComponents[0].length - 1) {
            if (gridComponents[row][col - 1].getContent() != -1 && gridComponents[row]
                    [col - 1].status == GridStatus.Covered) {
                gridComponents[row][col - 1].status = GridStatus.Clicked;
                gridComponents[row][col - 1].repaint();
                if (gridComponents[row][col - 1].getContent() == 0) {
                    newRow.add(row);
                    newCol.add(col - 1);
                }
            }
            if (gridComponents[row - 1][col].getContent() != -1 && gridComponents[row - 1]
                    [col].status == GridStatus.Covered) {
                gridComponents[row - 1][col].status = GridStatus.Clicked;
                gridComponents[row - 1][col].repaint();
                if (gridComponents[row - 1][col].getContent() == 0) {
                    newRow.add(row - 1);
                    newCol.add(col);
                }
            }
            if (gridComponents[row - 1][col - 1].getContent() != -1 && gridComponents[row - 1]
                    [col - 1].status == GridStatus.Covered) {
                gridComponents[row - 1][col - 1].status = GridStatus.Clicked;
                gridComponents[row - 1][col - 1].repaint();
                if (gridComponents[row - 1][col - 1].getContent() == 0) {
                    newRow.add(row - 1);
                    newCol.add(col - 1);
                }
            }
        } else if (row == 0 && col == gridComponents[0].length - 1) {
            if (gridComponents[row][col - 1].getContent() != -1 && gridComponents[row]
                    [col - 1].status == GridStatus.Covered) {
                gridComponents[row][col - 1].status = GridStatus.Clicked;
                gridComponents[row][col - 1].repaint();
                if (gridComponents[row][col - 1].getContent() == 0) {
                    newRow.add(row);
                    newCol.add(col - 1);
                }
            }
            if (gridComponents[row + 1][col].getContent() != -1 && gridComponents[row + 1]
                    [col].status == GridStatus.Covered) {
                gridComponents[row + 1][col].status = GridStatus.Clicked;
                gridComponents[row + 1][col].repaint();
                if (gridComponents[row + 1][col].getContent() == 0) {
                    newRow.add(row + 1);
                    newCol.add(col);
                }
            }
            if (gridComponents[row + 1][col - 1].getContent() != -1 && gridComponents[row + 1]
                    [col - 1].status == GridStatus.Covered) {
                gridComponents[row + 1][col - 1].status = GridStatus.Clicked;
                gridComponents[row + 1][col - 1].repaint();
                if (gridComponents[row + 1][col - 1].getContent() == 0) {
                    newRow.add(row + 1);
                    newCol.add(col - 1);
                }
            }
        } else if (col == 0 && row == gridComponents.length - 1) {
            if (gridComponents[row][col + 1].getContent() != -1 && gridComponents[row]
                    [col + 1].status == GridStatus.Covered) {
                gridComponents[row][col + 1].status = GridStatus.Clicked;
                gridComponents[row][col + 1].repaint();
                if (gridComponents[row][col + 1].getContent() == 0) {
                    newRow.add(row);
                    newCol.add(col + 1);
                }
            }
            if (gridComponents[row - 1][col].getContent() != -1 && gridComponents[row - 1]
                    [col].status == GridStatus.Covered) {
                gridComponents[row - 1][col].status = GridStatus.Clicked;
                gridComponents[row - 1][col].repaint();
                if (gridComponents[row - 1][col].getContent() == 0) {
                    newRow.add(row - 1);
                    newCol.add(col);
                }
            }
            if (gridComponents[row - 1][col + 1].getContent() != -1 && gridComponents[row - 1]
                    [col + 1].status == GridStatus.Covered) {
                gridComponents[row - 1][col + 1].status = GridStatus.Clicked;
                gridComponents[row - 1][col + 1].repaint();
                if (gridComponents[row - 1][col + 1].getContent() == 0) {
                    newRow.add(row - 1);
                    newCol.add(col + 1);
                }
            }
        } else {
            if (gridComponents[row - 1][col - 1].getContent() != -1 && gridComponents[row - 1][col - 1].status ==
                    GridStatus.Covered) {
                gridComponents[row - 1][col - 1].status = GridStatus.Clicked;
                gridComponents[row - 1][col - 1].repaint();
                if (gridComponents[row - 1][col - 1].getContent() == 0) {
                    newRow.add(row - 1);
                    newCol.add(col - 1);
                }
            }
            if (gridComponents[row - 1][col].getContent() != -1 && gridComponents[row - 1][col].status ==
                    GridStatus.Covered) {
                gridComponents[row - 1][col].status = GridStatus.Clicked;
                gridComponents[row - 1][col].repaint();
                if (gridComponents[row - 1][col].getContent() == 0) {
                    newRow.add(row - 1);
                    newCol.add(col);
                }
            }
            if (gridComponents[row - 1][col + 1].getContent() != -1 && gridComponents[row - 1][col + 1].status ==
                    GridStatus.Covered) {
                gridComponents[row - 1][col + 1].status = GridStatus.Clicked;
                gridComponents[row - 1][col + 1].repaint();
                if (gridComponents[row - 1][col + 1].getContent() == 0) {
                    newRow.add(row - 1);
                    newCol.add(col + 1);
                }
            }
            if (gridComponents[row][col - 1].getContent() != -1 && gridComponents[row][col - 1].status ==
                    GridStatus.Covered) {
                gridComponents[row][col - 1].status = GridStatus.Clicked;
                gridComponents[row][col - 1].repaint();
                if (gridComponents[row][col - 1].getContent() == 0) {
                    newRow.add(row);
                    newCol.add(col - 1);
                }
            }
            if (gridComponents[row][col + 1].getContent() != -1 && gridComponents[row][col + 1].status ==
                    GridStatus.Covered) {
                gridComponents[row][col + 1].status = GridStatus.Clicked;
                gridComponents[row][col + 1].repaint();
                if (gridComponents[row][col + 1].getContent() == 0) {
                    newRow.add(row);
                    newCol.add(col + 1);
                }
            }
            if (gridComponents[row + 1][col - 1].getContent() != -1 && gridComponents[row + 1][col - 1].status ==
                    GridStatus.Covered) {
                gridComponents[row + 1][col - 1].status = GridStatus.Clicked;
                gridComponents[row + 1][col - 1].repaint();
                if (gridComponents[row + 1][col - 1].getContent() == 0) {
                    newRow.add(row + 1);
                    newCol.add(col - 1);
                }
            }
            if (gridComponents[row + 1][col].getContent() != -1 && gridComponents[row + 1][col].status ==
                    GridStatus.Covered) {
                gridComponents[row + 1][col].status = GridStatus.Clicked;
                gridComponents[row + 1][col].repaint();
                if (gridComponents[row + 1][col].getContent() == 0) {
                    newRow.add(row + 1);
                    newCol.add(col);
                }
            }
            if (gridComponents[row + 1][col + 1].getContent() != -1 && gridComponents[row + 1][col + 1].status ==
                    GridStatus.Covered) {
                gridComponents[row + 1][col + 1].status = GridStatus.Clicked;
                gridComponents[row + 1][col + 1].repaint();
                if (gridComponents[row + 1][col + 1].getContent() == 0) {
                    newRow.add(row + 1);
                    newCol.add(col + 1);
                }
            }
        }
    }

    public static ArrayList<Integer> getRoundcollist() {
        return Roundcollist;
    }

    public static ArrayList<Integer> getRoundrowlist() {
        return Roundrowlist;
    }

    public static ArrayList<GridComponent> getGridComponentArrayList() {
        return gridComponentArrayList;
    }

    @Override
    public String toString(){
        return row+" "+col+" "+content+" "+status.toString();
    }
}
