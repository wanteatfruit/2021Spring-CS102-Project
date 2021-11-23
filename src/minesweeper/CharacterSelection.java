package minesweeper;

import controller.Character;
import controller.CharacterSkill;
import entity.Player;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class CharacterSelection extends JFrame {

    private static Player p1;
    private static Player p2;



    public CharacterSelection() throws IOException, FontFormatException {
        showSelectionMenu();
    }

    public void showSelectionMenu() throws IOException, FontFormatException {
        JFrame jFrame=new JFrame("Select your character!");
        jFrame.setSize(900,630);
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
        JPanel panel=new JPanel(null);
        jFrame.setContentPane(panel);
        jFrame.getContentPane().setBackground(Color.WHITE);


        //bg
        /*JLabel bg=new JLabel(new ImageIcon("ch bg.png"));
        bg.setBounds(0,0,900,630);
        jFrame.add(bg);*/

        //des
        JLabel[] descripts=new JLabel[7];
        descripts[0]=createDescription(CharacterSkill.Killer_Queen);
        descripts[1]=createDescription(CharacterSkill.Star_Platinum);
        descripts[2]=createDescription(CharacterSkill.Golden_Experience);
        descripts[3]=createDescription(CharacterSkill.Bites_the_Dust);
        descripts[4]=createDescription(CharacterSkill.The_World);
        descripts[5]=createDescription(CharacterSkill.Crazy_Diamond);
        descripts[6]=createDescription(CharacterSkill.Sticky_Fingers);
        for (JLabel jLabel : descripts) {
            panel.add(jLabel);
            jLabel.setVisible(false);
            jLabel.setLocation(480,325);
            jLabel.setSize(390,110);
        }
        descripts[0].setVisible(true);


        //labels
        JLabel[] jLabels=new JLabel[7];
        jLabels[0]=createLabel(CharacterSkill.Killer_Queen);
        jLabels[1]=createLabel(CharacterSkill.Star_Platinum);
        jLabels[2]=createLabel(CharacterSkill.Golden_Experience);
        jLabels[3]=createLabel(CharacterSkill.Bites_the_Dust);
        jLabels[4]=createLabel(CharacterSkill.The_World);
        jLabels[5]=createLabel(CharacterSkill.Crazy_Diamond);
        jLabels[6]=createLabel(CharacterSkill.Sticky_Fingers);
        for (JLabel jLabel : jLabels) {
            panel.add(jLabel);
            jLabel.setVisible(false);
            jLabel.setLocation(475,25);
            jLabel.setSize(400,450);
        }
        jLabels[0].setVisible(true);



        //list
        ImageIcon btd=new ImageIcon("images/ch btd.png");
        ImageIcon ge=new ImageIcon("images/ch ge.png");
        ImageIcon tw=new ImageIcon("images/ch tw.png");
        ImageIcon sf=new ImageIcon("images/ch sf.png");
        ImageIcon cd=new ImageIcon("images/ch cd.png");
        ImageIcon kq=new ImageIcon("images/ch kq.png");
        ImageIcon sp=new ImageIcon("images/ch sp.png");
        JList<ImageIcon> imageIconJList=new JList<>();
        imageIconJList.setListData(new ImageIcon[]{kq,sp,ge,btd,tw,cd,sf});
        imageIconJList.setLocation(0,40);
        imageIconJList.setSize(425,500);
        imageIconJList.setFixedCellHeight(60);
        imageIconJList.setSelectedIndex(0);
        imageIconJList.setOpaque(false);
        imageIconJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index=imageIconJList.getSelectedIndex();
                for(int i=0;i< jLabels.length; i++){
                    if(index==i){
                        jLabels[i].setVisible(true);
                        descripts[i].setVisible(true);
                    }
                    else {
                        jLabels[i].setVisible(false);
                        descripts[i].setVisible(false);
                    }
                }

            }
        });

        final JList<String> characterList=new JList<>(); //create a list of characters
        characterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        characterList.setListData(new String[]{"吉良吉影", "空条承太郎","乔鲁诺","吉良吉影：败者食尘","DIO","东方仗助","布加拉提"}); //character names
        characterList.setLocation(100,10);
        characterList.setSize(300,500);
        characterList.setSelectedIndex(0);
        characterList.setFixedCellHeight(70);
        characterList.setSelectionBackground(Color.white);
        characterList.setFont(new Font("等线",Font.BOLD,19));
        //characterList.setFont(new Font("Final Fantasy VII", Font.BOLD,20));
        characterList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index=imageIconJList.getSelectedIndex();
                for(int i=0;i< jLabels.length; i++){
                    if(index==i){
                        jLabels[i].setVisible(true);
                        descripts[i].setVisible(true);
                    }
                    else {
                        jLabels[i].setVisible(false);
                        descripts[i].setVisible(false);
                    }
                }
            }
        });


        //buttons
        JButton p1Btn= new JButton(new ImageIcon("images/ch p1.png"));
        JButton p2Btn= new JButton(new ImageIcon("images/ch p2.png"));
        JButton cancelBtn= new JButton(new ImageIcon("images/ch cancel.png"));
        JButton startBtn = new JButton(new ImageIcon("images/ch start.png"));
        startBtn.setBounds(85,500,159,41);
        p1Btn.setBounds(285,500,159,41);
        p2Btn.setBounds(486,500,159,41);
        cancelBtn.setBounds(685,500,159,41);
        startBtn.setContentAreaFilled(false);
        startBtn.setBorderPainted(false);
        p1Btn.setBorderPainted(false); p1Btn.setContentAreaFilled(false); p2Btn.setContentAreaFilled(false); p2Btn.setBorderPainted(false);
        cancelBtn.setBorderPainted(false); cancelBtn.setContentAreaFilled(false);


        p1Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index=imageIconJList.getSelectedIndex();
                switch (index) {
                    case 0:
                        p1 = new Player("KillerQueen", new Character(CharacterSkill.Killer_Queen));
                        setP1(p1);
                        break;
                    case 1:
                        p1 = new Player("StarPlatinum", new Character(CharacterSkill.Star_Platinum));
                        setP1(p1);
                        break;
                    case 2:
                        p1= new Player("GoldExperience", new Character(CharacterSkill.Golden_Experience));
                        setP1(p1);
                        break;
                    case 3:
                        p1 = new Player("BitesTheDust", new Character(CharacterSkill.Bites_the_Dust));
                        setP1(p1);
                        break;
                    case 4:
                        p1=new Player("TheWorld",new Character(CharacterSkill.The_World));
                        setP1(p1);
                        break;
                    case 5:
                        p1=new Player("CrazyDiamond", new Character(CharacterSkill.Crazy_Diamond));
                        setP1(p1);
                        break;
                    case 6:
                        p1=new Player("StickyFingers", new Character(CharacterSkill.Sticky_Fingers));
                        break;
                }
                Sound.playVOSkillWhenChoosingP1();
            }
        });

        p2Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index=imageIconJList.getSelectedIndex();
                switch (index) {
                    case 0:
                        p2 = new Player("KillerQueen", new Character(CharacterSkill.Killer_Queen));
                        setP2(p2);
                        break;
                    case 1:
                        p2 = new Player("StarPlatinum", new Character(CharacterSkill.Star_Platinum));
                        setP2(p2);
                        break;
                    case 2:
                        p2= new Player("GoldExperience", new Character(CharacterSkill.Golden_Experience));
                        setP2(p2);
                        break;
                    case 3:
                        p2 = new Player("BitesTheDust", new Character(CharacterSkill.Bites_the_Dust));
                        setP2(p2);
                        break;
                    case 4:
                        p2=new Player("TheWorld",new Character(CharacterSkill.The_World));
                        setP2(p2);
                        break;
                    case 5:
                        p2=new Player("CrazyDiamond",new Character(CharacterSkill.Crazy_Diamond));
                        setP2(p2);
                        break;
                    case 6:
                        p2=new Player("StickyFingers", new Character(CharacterSkill.Sticky_Fingers));
                        setP2(p2);
                        break;
                }
                Sound.playVOSkillWhenChoosingP2();
            }
        });

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (p1 == null) {
                    JOptionPane.showMessageDialog(null, "P1 Please choose a character first.");
                } else {
                    if (p2 == null) {
                        JOptionPane.showMessageDialog(null, "P2 Please choose a character first.");
                    } else {
                        resetPlayer(p1,p2);
                        try {
                            MainFrame mainFrame = new MainFrame(MainFrame.getxCount(), MainFrame.getyCount(), MainFrame.getMineCount());
                        } catch (IOException | FontFormatException ioException) {
                            ioException.printStackTrace();
                        }
                        jFrame.dispose();
                    }
                }
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
            }
        });


        panel.add(startBtn);
        panel.add(p1Btn);
        panel.add(p2Btn);
        panel.add(cancelBtn);
        panel.add(imageIconJList);
        //panel.add(characterList);



    }
    public static JLabel createDescription(CharacterSkill characterSkill) throws IOException, FontFormatException {
        JLabel description=new JLabel();
        switch (characterSkill){
            case Star_Platinum:
                description.setIcon(new ImageIcon("images/sp des.png"));
                break;
            case Killer_Queen:
                description.setIcon(new ImageIcon("images/kq des.png"));

                break;
            case Golden_Experience:
                description.setIcon(new ImageIcon("images/ge des.png"));
                break;
            case Bites_the_Dust:
                description.setIcon(new ImageIcon("images/btd des.png"));
                break;

            case The_World:
                description.setIcon(new ImageIcon("images/tw des.png"));
                break;
            case Crazy_Diamond:
                description.setIcon(new ImageIcon("images/cd des.png"));
                break;
            case Sticky_Fingers:
                description.setIcon(new ImageIcon("images/sf des.png"));
                break;
        }
        return description;
    }


        public static JLabel createLabel(CharacterSkill characterSkill) throws IOException, FontFormatException {
        JLabel jLabel=new JLabel();
        switch (characterSkill){
            case Star_Platinum:
                jLabel.setText("<html><body>Skill name: Star Platinum<br> Ability: Gain one more move in a turn, can be used twice.<body></html>");
                jLabel.setForeground(Color.WHITE);
                ImageIcon imageIcon=new ImageIcon("images/splatinum.png");
                Image img = imageIcon.getImage();// 获得此图标的Image
                img = img.getScaledInstance(300, 300, Image.SCALE_AREA_AVERAGING);// 将image压缩后得到压缩后的img
                imageIcon.setImage(img);
                imageIcon.setImage(img);// 将图标设置为压缩后的图像
                jLabel.setIcon(imageIcon);

                jLabel.setHorizontalTextPosition(SwingConstants.CENTER);
                jLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
                jLabel.setSize(400,400);



                break;
            case Killer_Queen:

                ImageIcon imageIcon2=new ImageIcon("images/killerqueen.png");
                Image img2 = imageIcon2.getImage();// 获得此图标的Image
                img2 = img2.getScaledInstance(250, 300, Image.SCALE_AREA_AVERAGING);// 将image压缩后得到压缩后的img
                imageIcon2.setImage(img2);// 将图标设置为压缩后的图像
                jLabel.setIcon(imageIcon2);

                jLabel.setText("<html><body>Skill name: Killer Queen<br> Ability: Randomly generate a bomb in a cell. Nearby numbers won't be changed.<body></html>");
                jLabel.setHorizontalTextPosition(SwingConstants.CENTER);
                jLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
                jLabel.setFont(new Font("Lucida", Font.PLAIN,15));
                jLabel.setForeground(Color.WHITE);

                break;
            case Golden_Experience:
                ImageIcon imageIcon3=new ImageIcon("images/gexperiecne.png");
                Image img3 = imageIcon3.getImage();// 获得此图标的Image
                img3 = img3.getScaledInstance(300, 300, Image.SCALE_AREA_AVERAGING);// 将image压缩后得到压缩后的img
                imageIcon3.setImage(img3);// 将图标设置为压缩后的图像
                jLabel.setIcon(imageIcon3);

                jLabel.setText("<html><body>Skill name: Golden Experience<br> Ability: Randomly delete a bomb. The cell will be set to empty, nearby numbers won't be changed.<body></html>");
                jLabel.setHorizontalTextPosition(SwingConstants.CENTER);
                jLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
                jLabel.setFont(new Font("Lucida", Font.PLAIN,15));
                jLabel.setForeground(Color.WHITE);

                break;
            case Bites_the_Dust:
                ImageIcon imageIcon4=new ImageIcon("images/kira bites the dust.png");
                Image img4 = imageIcon4.getImage();// 获得此图标的Image
                img4 = img4.getScaledInstance(350, 300, Image.SCALE_AREA_AVERAGING);// 将image压缩后得到压缩后的img
                imageIcon4.setImage(img4);// 将图标设置为压缩后的图像
                jLabel.setIcon(imageIcon4);
                jLabel.setText("<html><body>Skill name: Bites the Dust<br> Ability: Randomly generate a special bomb. Left clicking on this bomb will reset your score and mistakes to 0. Nearby numbers won't be changed.<body></html>");
                jLabel.setHorizontalTextPosition(SwingConstants.CENTER);
                jLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
                jLabel.setFont(new Font("Lucida", Font.PLAIN,15));
                jLabel.setForeground(Color.WHITE);

                break;

            case The_World:
                ImageIcon imageIcon5=new ImageIcon("images/theworld.png");
                jLabel.setIcon(imageIcon5);
                jLabel.setText("<html><body>Skill name: Za Warudo<br> Ability: Stop timer. Can be used every round.<body></html>");
                jLabel.setHorizontalTextPosition(SwingConstants.CENTER);
                jLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
                jLabel.setFont(new Font("Lucida", Font.PLAIN,15));
                jLabel.setForeground(Color.WHITE);

                break;
            case Crazy_Diamond:
                ImageIcon imageIcon6=new ImageIcon("images/crazydiamond.png");
                jLabel.setIcon(imageIcon6);
                jLabel.setText("<html><body>Ability name: Crazy Diamond<br> Ability: Reset the grid you opened this round. Scores won't be changed.<body></html>");
                jLabel.setHorizontalTextPosition(SwingConstants.CENTER);
                jLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
                jLabel.setFont(new Font("Lucida", Font.PLAIN,15));
                jLabel.setForeground(Color.WHITE);

                break;
            case Sticky_Fingers:
                ImageIcon imageIcon7=new ImageIcon("images/stickyfingers.png");
                jLabel.setIcon(imageIcon7);
                jLabel.setText("<html><body>Ability name: Sticky Fingers<br> Ability: See through 3 grids.<body></html>");
                jLabel.setHorizontalTextPosition(SwingConstants.CENTER);
                jLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
                jLabel.setFont(new Font("Lucida", Font.PLAIN,15));
                jLabel.setForeground(Color.WHITE);

                break;

        }
        jLabel.setFont(new Font("Arial",Font.BOLD,16));

        return jLabel;


    }


    public static Player getP1() {
        return p1;
    }

    public static Player getP2() {
        return p2;
    }

    public static void setP1(Player p1) {
        CharacterSelection.p1 = p1;
    }

    public static void setP2(Player p2) {
        CharacterSelection.p2 = p2;
    }

    public static void resetPlayer(Player p1, Player p2){
        p1.setMistake(0); p2.setMistake(0); p1.setScore(0); p2.setScore(0);
    }
}
