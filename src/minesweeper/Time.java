package minesweeper;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Time extends JPanel {
    private static final int MIN_PROGRESS=0;
    private static final int MAX_PROGRESS=100;
    private static int currentProgress;
    private static JProgressBar jProgressBar=new JProgressBar();

    private  Timer timer=new Timer(250, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentProgress++;
            if(currentProgress>MAX_PROGRESS){ //时间到了进入下回合
                MainFrame.controller.nextTurn();
                currentProgress=MIN_PROGRESS;
            }
            jProgressBar.setValue(currentProgress);
        }
    });


    public Time(){
        jProgressBar.setMaximum(MAX_PROGRESS);
        jProgressBar.setMinimum(MIN_PROGRESS);
        jProgressBar.setValue(currentProgress);
       // jProgressBar.setOrientation(SwingConstants.VERTICAL);
        // 绘制百分比文本（进度条中间显示的百分数）
        jProgressBar.setForeground(Color.DARK_GRAY);
        jProgressBar.setSize(150,5);
        jProgressBar.setStringPainted(false);
        // 添加进度改变通知
        this.add(jProgressBar);
        this.setSize(100,5);
        this.setOpaque(false);
        timer.start();


    }

    public  JProgressBar getjProgressBar() {
        return jProgressBar;
    }

    public void setjProgressBar(JProgressBar jProgressBar) {
        Time.jProgressBar = jProgressBar;
    }

    public void timerReset(){
        currentProgress=0;
        jProgressBar.setValue(0);
        timer.start();
    }

    public  Timer getTimer() {
        return timer;
    }


}
