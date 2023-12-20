/**
 * Copyright (C), 2017-2023, 张瀚艺、靳明雨
 * @Author: 张瀚艺、靳明雨
 * @Date: 2023/12/18 10:55
 * @FileName: BasePage
 * @Description: 主要显示类
 */
package com.qts.basepage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.qts.controller.Controller;

import java.awt.Desktop;
import com.qts.sound.Audio;

public class Page extends JFrame implements Display{
    Audio audio = new Audio();
    JLabel topic;
    String result;
    List<JRadioButton> option=new ArrayList<>() ;
    JButton buttonBack;
    JButton buttonNext;
    JButton submit;
    JButton redo;
    Controller controller;
    ButtonGroup t1;

    public Page() throws HeadlessException{
        controller=new Controller();
        //controller.initialization();
        //图标更改
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/icon.png")));
        this.setIconImage(imageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        //标题，大小等设置
        this.setTitle("试题训练系统");
        this.setSize(800,600);
        this.setResizable(false);


        //关闭绑定
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //题目部分
        initialization();
        Color c = new Color(226, 238, 177);
        //布局
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();
        //竖排
        BoxLayout boxLayout = new BoxLayout(jPanel2, BoxLayout.Y_AXIS);
        jPanel2.setLayout(boxLayout);

        t1 = new ButtonGroup();
        //jPanel2.add(option.get(1));
        for(int i=0;i<option.size();i++){
            t1.add(option.get(i));
            jPanel2.add(option.get(i));

        }


        jPanel1.add(topic);

        jPanel3.add(buttonBack);
        jPanel3.add(buttonNext);
        jPanel3.add(submit);
        jPanel3.add(redo);


        this.add(jPanel1,BorderLayout.NORTH);
        this.add(jPanel2,BorderLayout.CENTER);
        this.add(jPanel3,BorderLayout.SOUTH);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosed (WindowEvent e)
            {
                Desktop dk = Desktop.getDesktop();
                try {
                    dk.browse(new URI("https://www.yuanshen.com/"));
                } catch (IOException | URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void initialization(){
        topic=new JLabel();
        topic.setText(controller.getTopic());
        topic.setAutoscrolls(true);
        topic.setFont(new Font("",Font.BOLD,15));
        topic.setPreferredSize(new Dimension(750,250));

        char ch='A';
        for(int i=0;i<8;i++){
            option.add(new JRadioButton());
            option.get(i).setActionCommand(String.valueOf(ch++));
            option.get(i).addActionListener(new AbstractAction()
            {
                @Override
                public void actionPerformed (ActionEvent e)
                {
                    audio.daRun();
                    submit.setEnabled(true);
                }
            });
        }

        setOption(controller.getoption());

        buttonBack=new JButton("上一题");
        buttonBack.setLocation(10,10);
        buttonBack.addActionListener(new AbstractAction()
        {//上题
            @Override
            public void actionPerformed (ActionEvent e)
            {
                controller.back();
                setTopic(controller.getTopic());
                setOption(controller.getoption());
                t1.clearSelection();
                submit.setEnabled(false);
                audio.kedaRun();
                //((JFrame)e.getSource()).repaint();
            }
        });

        buttonNext=new JButton("下一题");
        buttonNext.setLocation(10,10);
        buttonNext.addActionListener(new AbstractAction()
        {//下题
            @Override
            public void actionPerformed (ActionEvent e)
            {
                controller.next();
                setTopic(controller.getTopic());
                System.out.println(controller.getTopic());
                setOption(controller.getoption());
                t1.clearSelection();
                submit.setEnabled(false);
                audio.kedaRun();
                //((JFrame)e.getSource()).repaint();
            }
        });

        submit=new JButton("提交");
        submit.setLocation(10,10);
        submit.setEnabled(false);
        submit.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed (ActionEvent e)
            {
                result = controller.getCorrectOptions();
                if(t1.getSelection().getActionCommand().equals(result)){
                    audio.trueRun();
                    JOptionPane.showMessageDialog(Page.this,"答对了，你个大聪明");
                    controller.next();
                    setTopic(controller.getTopic());
                    System.out.println(controller.getTopic());
                    setOption(controller.getoption());
                    t1.clearSelection();
                    submit.setEnabled(false);

                }else{
                    audio.errorRun();
                    JOptionPane.showMessageDialog(Page.this,"答错了，正确答案是:"+result);

                }
            }
        });

        redo=new JButton("重做");
        redo.setLocation(10,10);
        redo.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed (ActionEvent e)
            {
                controller.setCurrent(1);
                setTopic(controller.getTopic());
                System.out.println(controller.getTopic());
                setOption(controller.getoption());
                t1.clearSelection();
                submit.setEnabled(false);

            }


        });


    }

    public void setTopic(String text){
        if(text.length()<25)
            topic.setText(text);
        else
        {
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append("<html>");
            int i=0;
            int ctl=50;//每行显示最大字符
            for(;(i+ctl)<text.length();i+=ctl){
                stringBuilder.append(text.substring(i,i+ctl)+"<br>");
            }stringBuilder.append(text.substring(i,text.length()));
            stringBuilder.append("</html>");
            topic.setText(stringBuilder.toString());
        }
    }
    public void setOption(List<String> opt){
        int max=opt.size();
        int i=0;
        char ch='A';
        //System.out.println(option.size());
        for(;i<max;i++){
            //System.out.println(i);
            option.get(i).setText(ch+":"+opt.get(i));
            ch++;
            //System.out.println(opt.get(i));
            option.get(i).setMaximumSize(new Dimension(700,50));
        }
        for(;i<option.size();i++){
            //System.out.println(i);
            option.get(i).setText("");
            option.get(i).setMaximumSize(new Dimension(0,0));
        }


    }







}
