package minesweeper;


import components.GridComponent;
import controller.Character;
import controller.CharacterSkill;
import controller.GameController;
import entity.GridStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    public static GameController controller;
    private static int xCount;
    private static int yCount;
    private static int mineCount;

    private static int skillCount1=0; //使用次数
    private static int skillCount2=0;

    private static JLabel skillp1=new JLabel();
    private static JLabel skillp2 =new JLabel();

    private static JButton skillBtn1=new JButton();
    private static JButton skillBtn2=new JButton();

    public MainFrame(int xCount, int yCount, int mineCount) throws IOException, FontFormatException {
        showMainFrame(xCount,yCount,mineCount);
    }
    public void showMainFrame(int xCount, int yCount, int mineCount) throws IOException, FontFormatException { //此处为”自定义雷区大小“  对于预设的，也可以将其预设的数值传到此构造器中

        MainFrame.xCount =xCount;
        MainFrame.yCount =yCount;
        MainFrame.mineCount =mineCount;

        JFrame jFrame=new JFrame();
        jFrame.setTitle("The MineSweeper");

        jFrame.setLayout(null );
        jFrame.setSize(yCount*GridComponent.gridSize+250,xCount*GridComponent.gridSize+300);
        jFrame.setLocationRelativeTo(null);

        controller = new GameController(CharacterSelection.getP1(), CharacterSelection.getP2());
        GamePanel gamePanel = new GamePanel(xCount, yCount, mineCount);
        controller.setGamePanel(gamePanel);
        ScoreBoard scoreBoard = new ScoreBoard(CharacterSelection.getP1(), CharacterSelection.getP2(), xCount, yCount, mineCount);
        controller.setScoreBoard(scoreBoard);
        //this.setSize(Math.max(yCount * GridComponent.gridSize, scoreBoard.getWidth()), xCount * GridComponent.gridSize + scoreBoard.getHeight());
        gamePanel.setLocation((yCount*GridComponent.gridSize+200-yCount*GridComponent.gridSize)/2+10,0);
        scoreBoard.setLocation((yCount*GridComponent.gridSize+200-250)/2,xCount * GridComponent.gridSize);
        scoreBoard.setOpaque(false);
        jFrame.add(gamePanel);
        jFrame.add(scoreBoard);
        //menu bar
        JMenuBar menuBar= new JMenuBar();
        JMenu controlMenu= new JMenu("Game controls");
        JMenu settingsMenu= new JMenu("Settings");
        menuBar.add(controlMenu);        menuBar.add(settingsMenu);
        JMenuItem load=new JMenuItem("Load Game");
        JMenuItem save=new JMenuItem("Save Game");
        JMenuItem resetBtn=new JMenuItem("Reset");
        JMenuItem restartBtn=new JMenuItem("Restart");
        JMenuItem reveal=new JMenuItem("Reveal");
        JMenuItem cancelReveal=new JMenuItem("Cancel Reveal");
        controlMenu.add(save);controlMenu.add(load);controlMenu.add(resetBtn);controlMenu.add(restartBtn);controlMenu.addSeparator();controlMenu.add(reveal);controlMenu.add(cancelReveal);

        //透视
        reveal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//翻开grid的content为-1的格子
                MainFrame.controller.reveal();
            }
        });
        cancelReveal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.controller.cancelReveal();
            }
        });
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.controller.reset();
            }
        });

        restartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CharacterSelection.resetPlayer(MainFrame.controller.getP1(),MainFrame.controller.getP2());
                MainFrame.controller.getScoreBoard().update();
                jFrame.dispose();
                MainFrame.controller.getScoreBoard().getTime().getTimer().stop();

                MainFrame.controller.getScoreBoard().getTime().timerReset();
                MainFrame.controller.getScoreBoard().getTime().getTimer().stop();
                MainFrame.controller.getScoreBoard().update();
                try {
                    MainFrame mainFrame=new MainFrame(MainFrame.getxCount(),MainFrame.getyCount(),MainFrame.getMineCount());
                } catch (IOException | FontFormatException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String fileName = JOptionPane.showInputDialog(jFrame, "input here");
                    System.out.println("fileName :" + fileName);
                    //MainFrame.controller.writeDataToFile(fileName);

                    ArrayList<String> strings = new ArrayList<>();
                    if (MainFrame.controller.getOnTurnPlayer() == controller.getP1()) {
                        strings.add(MainFrame.controller.getP1().toString());
                        strings.add(MainFrame.controller.getP2().toString());
                    } else {
                        strings.add(MainFrame.controller.getP2().toString());
                        strings.add(MainFrame.controller.getP1().toString());
                    }

                    strings.add(String.valueOf(MainFrame.getxCount()));
                    strings.add(String.valueOf(MainFrame.getyCount()));
                    strings.add(String.valueOf(MainFrame.getMineCount()));
                    strings.add(String.valueOf(MainFrame.controller.getMinesLeft()));
                    strings.add(String.valueOf(MainFrame.controller.getRoundCount()));

                    GridComponent[][] gridComponents = MainFrame.controller.getGamePanel().getMineField();
                    for (int i = 0; i < gridComponents.length; i++) {
                        for (int j = 0; j < gridComponents[0].length; j++) {
                            strings.add(gridComponents[i][j].toString());
                        }
                    }
                    MainFrame.controller.writeFile(fileName +".txt", strings);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null,"Save failed");
                }
            }
        });

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String fileName = JOptionPane.showInputDialog(jFrame, "input here");
                System.out.println("fileName :" + fileName);

                ArrayList<String> strings = null;
                try {
                    strings = (ArrayList<String>) MainFrame.controller.readFile(fileName+".txt");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                if(strings!=null) {
                    String[] playerOnTurn = strings.get(0).split(" ");
                    String[] playerNotTurn = strings.get(1).split(" ");
                    if (playerOnTurn[5].equals(String.valueOf(1))) {
                        for (int i = 0; i < playerOnTurn.length; i++) {
                            MainFrame.controller.getP1().setUserName(playerOnTurn[0]);
                            MainFrame.controller.getP1().setScore(Integer.parseInt(playerOnTurn[1]));
                            MainFrame.controller.getP1().setMistake(Integer.parseInt(playerOnTurn[2]));
                            MainFrame.controller.getP1().setStepsInRound(Integer.parseInt(playerOnTurn[3]));
                            MainFrame.controller.getP1().setCharacter(new Character(CharacterSkill.valueOf(playerOnTurn[4])));
                            MainFrame.controller.getP2().setUserName(playerNotTurn[0]);
                            MainFrame.controller.getP2().setScore(Integer.parseInt(playerNotTurn[1]));
                            MainFrame.controller.getP2().setMistake(Integer.parseInt(playerNotTurn[2]));
                            MainFrame.controller.getP2().setStepsInRound(Integer.parseInt(playerNotTurn[3]));
                            MainFrame.controller.getP2().setCharacter(new Character(CharacterSkill.valueOf(playerNotTurn[4])));
                            MainFrame.controller.setOnTurn(controller.getP1());
                            MainFrame.controller.getScoreBoard().update();
                        }
                    } else if(playerOnTurn[5].equals(String.valueOf(2))){
                        for (int i = 0; i < playerOnTurn.length; i++) {
                            MainFrame.controller.getP2().setUserName(playerOnTurn[0]);
                            MainFrame.controller.getP2().setScore(Integer.parseInt(playerOnTurn[1]));
                            MainFrame.controller.getP2().setMistake(Integer.parseInt(playerOnTurn[2]));
                            MainFrame.controller.getP2().setStepsInRound(Integer.parseInt(playerOnTurn[3]));
                            MainFrame.controller.getP2().setCharacter(new Character(CharacterSkill.valueOf(playerOnTurn[4])));
                            MainFrame.controller.getP1().setUserName(playerNotTurn[0]);
                            MainFrame.controller.getP1().setScore(Integer.parseInt(playerNotTurn[1]));
                            MainFrame.controller.getP1().setMistake(Integer.parseInt(playerNotTurn[2]));
                            MainFrame.controller.getP1().setStepsInRound(Integer.parseInt(playerNotTurn[3]));
                            MainFrame.controller.getP1().setCharacter(new Character(CharacterSkill.valueOf(playerNotTurn[4])));
                            MainFrame.controller.setOnTurn(controller.getP2());
                            MainFrame.controller.getScoreBoard().update();
                        }
                    }
                    MainFrame.controller.setMinesLeft(Integer.parseInt(strings.get(5)));
                    MainFrame.controller.setRoundCount(Integer.parseInt(strings.get(6)));
                }


                try {
                    MainFrame mainFrame = new MainFrame(Integer.parseInt(strings.get(2)), Integer.parseInt(strings.get(3)), Integer.parseInt(strings.get(4)));
                    MainFrame.controller.getScoreBoard().getTime().timerReset();

                    GridComponent[][] gridComponent = MainFrame.controller.getGamePanel().getMineField();
                    for (int i = 7; i < strings.size(); i++) {
                        String[] grid = strings.get(i).split(" ");
                        for (int j = 0; j < grid.length; j++) {
                            gridComponent[Integer.parseInt(grid[0])][Integer.parseInt(grid[1])].setContent(Integer.parseInt(grid[2]));
                            gridComponent[Integer.parseInt(grid[0])][Integer.parseInt(grid[1])].setStatus(GridStatus.valueOf(grid[3]));
                        }
                    }
                    for (int i = 0; i < gridComponent.length; i++) {
                        for (int j = 0; j < gridComponent[0].length; j++) {
                            gridComponent[i][j].repaint();
                        }
                    }
                    String[] playerOnTurn = strings.get(0).split(" ");
                    if(playerOnTurn[5].equals(String.valueOf(1))) MainFrame.controller.setOnTurn(controller.getP1());
                    if(playerOnTurn[5].equals(String.valueOf(2))) MainFrame.controller.setOnTurn(controller.getP2());
                    MainFrame.controller.getGamePanel().setMineField(gridComponent);
                    MainFrame.controller.setMinesLeft(Integer.parseInt(strings.get(5)));
                    MainFrame.controller.setRoundCount(Integer.parseInt(strings.get(6)));
                    MainFrame.controller.getScoreBoard().update();
                    jFrame.dispose();
                } catch (FileNotFoundException fileNotFoundException) {
                    JOptionPane.showMessageDialog(null, "Load Failed");
                } catch (IOException | FontFormatException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        jFrame.setJMenuBar(menuBar);

        //background
        ImageIcon icon=new ImageIcon("images/ingame back.jpg");
        JLabel backg=new JLabel(icon);
        backg.setLocation(0,xCount * GridComponent.gridSize);
        backg.setSize(icon.getIconWidth(), icon.getIconHeight());



        //使用技能 PLAYER 1
        skillp1=new JLabel(new ImageIcon("images/p1 skill.png"));
        skillp1.setBounds((yCount*GridComponent.gridSize+200-250)/2-100, gamePanel.getHeight() + 100,110,20);
        skillBtn1= new JButton();
        skillBtn1.setSize(110,20);
        skillBtn1.setLocation((yCount*GridComponent.gridSize+200-250)/2-85, gamePanel.getHeight() + 100);
        skillBtn1.setContentAreaFilled(false);
        skillBtn1.setBorderPainted(false);
        //btnPanel.add(skillBtn1,BorderLayout.EAST);
        skillBtn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(MainFrame.controller.getOnTurnPlayer()!= controller.getP1()){
                    JOptionPane.showMessageDialog(null,"You clicked the wrong button");
                }
                else {
                    switch (MainFrame.controller.getOnTurnPlayer().getCharacter().getCharacterSkill()) {
                        case Killer_Queen:
                            MainFrame.controller.getOnTurnPlayer().getCharacter().KillerQueen();
                            Sound.playVOSkillUsedInGame();
                            Sound.playSkillSEUsedInGame();
                            skillCount1++;
                            if(skillCount1==1) {
                                skillBtn1.setVisible(false);
                                skillp1.setVisible(false);
                                skillCount1=0;
                            }
                            break;
                        case Star_Platinum:
                            MainFrame.controller.getOnTurnPlayer().getCharacter().StarPlatinum();
                            skillCount1++;
                            Sound.playVOSkillUsedInGame();
                            Sound.playSkillSEUsedInGame();
                            if(skillCount1==1) {
                                skillBtn1.setVisible(false);
                                skillp1.setVisible(false);
                                skillCount1=0;
                            }


                            break;
                        case Golden_Experience:
                            MainFrame.controller.getOnTurnPlayer().getCharacter().GoldenExperience();
                            Sound.playVOSkillUsedInGame();
                            Sound.playSkillSEUsedInGame();
                            skillCount1++;
                            if(skillCount1==1) {
                                skillBtn1.setVisible(false);
                                skillp1.setVisible(false);
                                skillCount1=0;
                            }
                            break;
                        case Bites_the_Dust:
                            MainFrame.controller.getOnTurnPlayer().getCharacter().bitesTheDust();
                            Sound.playVOSkillUsedInGame();
                            Sound.playSkillSEUsedInGame();
                            skillBtn1.setVisible(false);
                            skillp1.setVisible(false);

                            break;
                        case The_World:
                            MainFrame.controller.getOnTurnPlayer().getCharacter().theWorld();
                            Sound.playVOSkillUsedInGame();
                            Sound.playSkillSEUsedInGame();
                            break;
                        case Crazy_Diamond:
                            MainFrame.controller.getOnTurnPlayer().getCharacter().crazyDia();
                            Sound.playVOSkillUsedInGame();
                            Sound.playSkillSEUsedInGame();
                            skillCount1++;
                            if(skillCount1==1) {
                                skillBtn1.setVisible(false);
                                skillp1.setVisible(false);
                                skillCount1=0;
                            }


                            break;
                        case Sticky_Fingers:
                            MainFrame.controller.getOnTurnPlayer().getCharacter().stickyfinger();
                            Sound.playVOSkillUsedInGame();
                            Sound.playSkillSEUsedInGame();
                            skillCount1++;
                            if(skillCount1==1) {
                                skillBtn1.setVisible(false);
                                skillp1.setVisible(false);
                                skillCount1=0;
                            }
                            break;
                    }
                }
            }
        });


        //使用技能 PLAYER 2
        skillp2 =new JLabel(new ImageIcon("images/p2 skill.png"));
        skillp2.setBounds(jFrame.getWidth()/2+115, gamePanel.getHeight() + 100,110,20);
       skillBtn2= new JButton();
        skillBtn2.setSize(110,20);
        skillBtn2.setLocation(jFrame.getWidth()/2+130, gamePanel.getHeight() + 100);
        skillBtn2.setContentAreaFilled(false);
        skillBtn2.setBorderPainted(false);
        //btnPanel.add(skillBtn2,BorderLayout.WEST);
        skillBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(MainFrame.controller.getOnTurnPlayer()!= controller.getP2()){
                    JOptionPane.showMessageDialog(null,"You clicked the wrong button");
                }
                else {
                    switch (MainFrame.controller.getOnTurnPlayer().getCharacter().getCharacterSkill()) {
                        case Killer_Queen:
                            skillCount2++;
                            MainFrame.controller.getOnTurnPlayer().getCharacter().KillerQueen();
                            Sound.playVOSkillUsedInGame();
                            Sound.playSkillSEUsedInGame();
                            if(skillCount2==1) {
                                skillBtn2.setVisible(false);
                                skillp2.setVisible(false);
                                skillCount2=0;
                            }

                            break;
                        case Star_Platinum:
                            MainFrame.controller.getOnTurnPlayer().getCharacter().StarPlatinum();
                            Sound.playVOSkillUsedInGame();
                            Sound.playSkillSEUsedInGame();
                            skillCount2++;
                            if(skillCount2==1) {
                                skillBtn2.setVisible(false);
                                skillp2.setVisible(false);
                                skillCount2=0;
                            }



                            break;
                        case Golden_Experience:
                            MainFrame.controller.getOnTurnPlayer().getCharacter().GoldenExperience();
                            Sound.playVOSkillUsedInGame();
                            Sound.playSkillSEUsedInGame();
                            skillCount2++;
                            if(skillCount2==1) {
                                skillBtn2.setVisible(false);
                                skillp2.setVisible(false);
                                skillCount2=0;
                            }


                            break;
                        case Bites_the_Dust:
                            MainFrame.controller.getOnTurnPlayer().getCharacter().bitesTheDust();
                            Sound.playVOSkillUsedInGame();
                            Sound.playSkillSEUsedInGame();
                            skillBtn2.setVisible(false);
                            skillp2.setVisible(false);

                            break;
                        case The_World:
                            MainFrame.controller.getOnTurnPlayer().getCharacter().theWorld();
                            Sound.playVOSkillUsedInGame();
                            Sound.playSkillSEUsedInGame();
                            break;
                        case Crazy_Diamond:
                            MainFrame.controller.getOnTurnPlayer().getCharacter().crazyDia();
                            Sound.playVOSkillUsedInGame();
                            Sound.playSkillSEUsedInGame();
                            skillCount2++;
                            if(skillCount2==1) {
                                skillBtn2.setVisible(false);
                                skillp2.setVisible(false);
                                skillCount2=0;
                            }
                            break;
                        case Sticky_Fingers:
                            MainFrame.controller.getOnTurnPlayer().getCharacter().stickyfinger();
                            Sound.playVOSkillUsedInGame();
                            Sound.playSkillSEUsedInGame();
                            skillCount2++;
                            if(skillCount2==1) {
                                skillBtn2.setVisible(false);
                                skillp2.setVisible(false);
                                skillCount2=0;
                            }
                            break;

                    }
                }
            }
        });


        //pic
        switch (MainFrame.controller.getP1().getCharacter().getCharacterSkill()){
            case Killer_Queen:
                JLabel jLabel=new JLabel(new ImageIcon("images/kq wait.png"));
                jLabel.setBounds(0,0,125,xCount*GridComponent.gridSize);
                jFrame.add(jLabel);
                break;
            case Star_Platinum:
                JLabel jLabel1=new JLabel(new ImageIcon("images/sp wait.png"));
                jLabel1.setBounds(0,0,125,xCount*GridComponent.gridSize);
                jFrame.add(jLabel1);
                break;
            case Golden_Experience:
                JLabel jLabel2=new JLabel(new ImageIcon("images/ge wait.png"));
                jLabel2.setBounds(0,0,125,xCount*GridComponent.gridSize);
               jFrame.add(jLabel2);
                break;
            case Bites_the_Dust:
                JLabel jLabel3=new JLabel(new ImageIcon("images/btd wait.png"));
                jLabel3.setBounds(0,0,125,xCount*GridComponent.gridSize);
                jFrame.add(jLabel3);
                break;
            case The_World:
                JLabel jLabel4=new JLabel(new ImageIcon("images/tw wait.png"));
                jLabel4.setBounds(0,0,125,xCount*GridComponent.gridSize);
                jFrame.add(jLabel4);
                break;
            case Crazy_Diamond:
                JLabel jLabel5=new JLabel(new ImageIcon("images/cd wait.png"));
                jLabel5.setBounds(0,0,125,xCount*GridComponent.gridSize);
                jFrame.add(jLabel5);
                break;
            case Sticky_Fingers:
                JLabel jLabel6=new JLabel(new ImageIcon("images/sf wait.png"));
                jLabel6.setBounds(0,0,125,xCount*GridComponent.gridSize);
                jFrame.add(jLabel6);
                break;

        }
        switch (MainFrame.controller.getP2().getCharacter().getCharacterSkill()) {
            case Killer_Queen:
                JLabel jLabel = new JLabel(new ImageIcon("images/kq wait.png"));
                jLabel.setBounds(jFrame.getWidth()/2+gamePanel.getWidth()/2-10,0,125,xCount*GridComponent.gridSize);
                jFrame.add(jLabel);
                break;
            case Star_Platinum:
                JLabel jLabel1 = new JLabel(new ImageIcon("images/sp wait.png"));
                jLabel1.setBounds(jFrame.getWidth()/2+gamePanel.getWidth()/2-10,0,125,xCount*GridComponent.gridSize);
                jFrame.add(jLabel1);
                break;
            case Golden_Experience:
                JLabel jLabel2 = new JLabel(new ImageIcon("images/ge wait.png"));
                jLabel2.setBounds(jFrame.getWidth()/2+gamePanel.getWidth()/2-10,0,125,xCount*GridComponent.gridSize);
                jFrame.add(jLabel2);
                break;
            case Bites_the_Dust:
                JLabel jLabel3 = new JLabel(new ImageIcon("images/btd wait.png"));
                jLabel3.setBounds(jFrame.getWidth()/2+gamePanel.getWidth()/2-10,0,125,xCount*GridComponent.gridSize);
                jFrame.add(jLabel3);
                break;
            case The_World:
                JLabel jLabel4=new JLabel(new ImageIcon("images/tw wait.png"));
                jLabel4.setBounds(jFrame.getWidth()/2+gamePanel.getWidth()/2-10,0,125,xCount*GridComponent.gridSize);
                jFrame.add(jLabel4);
                break;
            case Crazy_Diamond:
                JLabel jLabel5=new JLabel(new ImageIcon("images/cd wait.png"));
                jLabel5.setBounds(jFrame.getWidth()/2+gamePanel.getWidth()/2-10,0,125,xCount*GridComponent.gridSize);
                jFrame.add(jLabel5);
                break;
            case Sticky_Fingers:
                JLabel jLabel6=new JLabel(new ImageIcon("images/sf wait.png"));
                jLabel6.setBounds(jFrame.getWidth()/2+gamePanel.getWidth()/2-10,0,125,xCount*GridComponent.gridSize);
                jFrame.add(jLabel6);
                break;

        }




        //btnPanel.setLocation(0,yCount * GridComponent.gridSize+20);
        //btnPanel.setSize(save_gameBtn.getWidth(), save_gameBtn.getHeight()+skillBtn1.getHeight());

       jFrame.add(skillp1);
        jFrame.add(skillp2);
        jFrame.add(skillBtn1);
        jFrame.add(skillBtn2);

        jFrame.addWindowListener(new WindowAdapter() {
                                     @Override
                                     public void windowClosed(WindowEvent e) {
                                         super.windowClosed(e);
                                         MainFrame.controller.getScoreBoard().getTime().getTimer().stop();
                                     }
                                 });

                jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jFrame.getContentPane().setBackground(Color.WHITE);
       jFrame.setVisible(true);

        controller.getScoreBoard().time.timerReset();


    }




    public static int getMineCount() {
        return mineCount;
    }

    public static int getxCount() {
        return xCount;
    }

    public static int getyCount() {
        return yCount;
    }

    public static void setxCount(int xCount) {
        MainFrame.xCount = xCount;
    }

    public static void setyCount(int yCount) {
        MainFrame.yCount = yCount;
    }

    public static void setMineCount(int mineCount) {
        MainFrame.mineCount = mineCount;
    }

    public void disposeGame(){
        this.dispose();
    }

    public static int getSkillCount1() {
        return skillCount1;
    }

    public static int getSkillCount2() {
        return skillCount2;
    }

    public static JButton getSkillBtn1() {
        return skillBtn1;
    }

    public static JButton getSkillBtn2() {
        return skillBtn2;
    }

    public static JLabel getSkillp2() {
        return skillp2;
    }

    public static JLabel getSkillp1() {
        return skillp1;
    }

}
