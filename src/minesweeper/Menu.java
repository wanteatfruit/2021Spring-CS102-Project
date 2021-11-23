package minesweeper;

import controller.CharacterSkill;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Enumeration;

public class Menu extends JFrame {


    public Menu(){
        //InitGlobalFont(new Font("Lucida Sans",Font.BOLD,18));
        this.setTitle("MS Main Menu");
        this.setSize(900, 630);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel=new JPanel(null);

        //开始简单难度游戏按钮
        JButton startEasyGameBtn= new JButton();
        startEasyGameBtn.setBounds(100,500,170,50);
        startEasyGameBtn.setOpaque(false);
        startEasyGameBtn.setContentAreaFilled(false);
        startEasyGameBtn.setBorderPainted(false);
        startEasyGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CharacterSelection characterSelection=new CharacterSelection();
                } catch (IOException | FontFormatException ioException) {
                    ioException.printStackTrace();
                }
                MainFrame.setxCount(9); MainFrame.setyCount(9);  MainFrame.setMineCount(10);
            }
        });

        //start medium game
        JButton startMediumGameBtn= new JButton();
        startMediumGameBtn.setBounds(340,420,250,50);
        startMediumGameBtn.setOpaque(false);
        startMediumGameBtn.setContentAreaFilled(false);
        startMediumGameBtn.setBorderPainted(false);
        startMediumGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CharacterSelection characterSelection=new CharacterSelection();
                } catch (IOException | FontFormatException ioException) {
                    ioException.printStackTrace();
                }
                MainFrame.setxCount(16); MainFrame.setyCount(16);  MainFrame.setMineCount(40);
            }
        });

        //start diffi game
        JButton startDiffiGameBtn= new JButton();
        startDiffiGameBtn.setBounds(655,345,200,50);
        startDiffiGameBtn.setOpaque(false);
        startDiffiGameBtn.setContentAreaFilled(false);
        //startDiffiGameBtn.setBorderPainted(false);

        startDiffiGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CharacterSelection characterSelection=new CharacterSelection();
                } catch (IOException | FontFormatException ioException) {
                    ioException.printStackTrace();
                }
                MainFrame.setxCount(16); MainFrame.setyCount(30);  MainFrame.setMineCount(99);

            }
        });

        //set customized game
        //first show dialogue, then after set the mines, start game from dialogue window
        JButton setCustomized = new JButton();
        setCustomized.setBounds(315,280,300,40);
        setCustomized.setContentAreaFilled(false);
        setCustomized.setBorderPainted(false);
        setCustomized.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customize();
            }
        });

        panel.add(startEasyGameBtn);
        panel.add(startMediumGameBtn);
        panel.add(startDiffiGameBtn);
        panel.add(setCustomized);

        //set background
        ImageIcon back=new ImageIcon("images/menu back.png");
        JLabel backlable=new JLabel(back);
        backlable.setBounds(0,0,900,600);

        this.setContentPane(panel);
        JPanel j=(JPanel)this.getContentPane();
        j.setOpaque(false);


        //this.add(panel);
        this.add(backlable);

        this.setVisible(true);

        //this.setContentPane(panel);

        //play music
        Sound sound =new Sound();
        if(this.isVisible()) {
            sound.playMusic();
        }

    }



    public static JComponent createPanel(CharacterSkill characterSkill){
        JPanel jPanel=new JPanel();
        switch (characterSkill){
            case Star_Platinum:
                JLabel jLabel=new JLabel();
                jLabel.setText("Stand name: Star Platinum\n Stand ability: Gain one more move in a turn, can be used twice");

                ImageIcon imageIcon=new ImageIcon("447-4476937_ssr-jotaro-kujopart-4-star-platinum-the-world.png");
                Image img = imageIcon.getImage();// 获得此图标的Image
                img = img.getScaledInstance(300, 300, Image.SCALE_AREA_AVERAGING);// 将image压缩后得到压缩后的img
                imageIcon.setImage(img);// 将图标设置为压缩后的图像
                jLabel.setIcon(imageIcon);

                jLabel.setHorizontalTextPosition(SwingConstants.CENTER);
                jLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
                jLabel.setSize(400,400);
                jPanel.add(jLabel);

                break;
            case Killer_Queen:
                JLabel jLabel2=new JLabel();

                ImageIcon imageIcon2=new ImageIcon("5959432_yoshikage-kira-kira-yoshikage-and-killer-queen-png.png");
                Image img2 = imageIcon2.getImage();// 获得此图标的Image
                img2 = img2.getScaledInstance(250, 300, Image.SCALE_AREA_AVERAGING);// 将image压缩后得到压缩后的img
                imageIcon2.setImage(img2);// 将图标设置为压缩后的图像
                jLabel2.setIcon(imageIcon2);

                jLabel2.setText("Stand name: Killer Queen\n Stand ability: Randomly generate a bomb. Nearby numbers won't be changed. Can use only once.");
               jLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
                jLabel2.setVerticalTextPosition(SwingConstants.BOTTOM);
                jPanel.add(jLabel2);
                break;
        }
        return jPanel;


    }

    public static void showEasyGameWindow() throws IOException, FontFormatException {
        MainFrame mainFrame=new MainFrame(9,9,10);
    }

    public static void showMediumGameWindow() throws IOException, FontFormatException {
        MainFrame mainFrame=new MainFrame(16,16,40);
    }

    public static void showDiffiGameWindow() throws IOException, FontFormatException {
        MainFrame mainFrame=new MainFrame(16,30,99);

    }

    public static void customize(){
        final JDialog dialog=new JDialog();
        dialog.setSize(400,200);
        dialog.setLocationRelativeTo(null);
        GridLayout gridLayout=new GridLayout(4,2);
        JPanel panel=new JPanel(gridLayout);

        JLabel labelRow=new JLabel();
        labelRow.setText("      Set Row");
        JLabel labelColumn=new JLabel();
        labelColumn.setText("      Set Column");
        JLabel labelMine=new JLabel();
        labelMine.setText("      Set Number of Mines");

        final JTextField rowNum=new JTextField(6);
        final JTextField columnNum=new JTextField(6);
        final JTextField mineNum=new JTextField(6);

        panel.add(labelRow);
        panel.add(rowNum);
        panel.add(labelColumn);
        panel.add(columnNum);
        panel.add(labelMine);
        panel.add(mineNum);

        JButton okBtn=new JButton("Start Game");
        JButton cancelBtn=new JButton("Cancel");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean canStart = true;
                    if (rowNum.getText().equals("") || columnNum.getText().equals("") || mineNum.getText().equals("")) {
                        JOptionPane.showMessageDialog(dialog, "Please enter a number first.");
                    } else {
                        int row = Integer.parseInt(rowNum.getText());
                        int col = Integer.parseInt(columnNum.getText());
                        int mine = Integer.parseInt(mineNum.getText());
                        if (row > 24 || col > 30 ) {
                            JOptionPane.showMessageDialog(dialog, "Oops! The size should be smaller than 24*30", "oops", JOptionPane.WARNING_MESSAGE);
                            canStart = false;
                        } else if (mine > row * col / 2) {
                            JOptionPane.showMessageDialog(dialog, "Minecount should be smaller", "oops", JOptionPane.WARNING_MESSAGE);
                            canStart = false;
                        }
                        if (canStart) {
                            CharacterSelection characterSelection = new CharacterSelection();
                            MainFrame.setxCount(row);
                            MainFrame.setyCount(col);
                            MainFrame.setMineCount(mine);
                            dialog.dispose();
                        }
                    }
                }
                catch (NumberFormatException exception){
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Wrong number");
                } catch (IOException | FontFormatException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        cancelBtn.addActionListener(e -> dialog.dispose());

        panel.add(okBtn);
        panel.add(cancelBtn);

        dialog.setContentPane(panel);
        dialog.setVisible(true);


    }





}
