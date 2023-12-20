/**
 * Copyright (C), 2017-2023, 靳明雨、张瀚艺
 *
 * @Author: 靳明雨、张瀚艺
 * @Date: 2023/12/18 10:27
 * @FileName: Controller
 * @Description: 控制器类
 */
package com.qts.controller;

import com.qts.data.ChoiceQuestion;
import com.qts.datacontorller.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Controller
{
    private ChoiceQuestion currentQuestion;
    Database db;



    private int current=1;

    public Controller ()
    {
        this.db=Database.Instance();
        initialization(current);

    }
    public void next(){//定位到下题
        if(current<db.getLength())
        {
            current++;
            initialization(current);
        }
    }

    public void back(){//定位到上题
        if(current>1){
            current--;
            initialization(current);
        }
    }


    public List<String> getoption(){//获取选项
        return currentQuestion.getoption();
    }
    public String getTopic(){//获取题目
        return currentQuestion.getTopic();
    }

    public boolean determine(String ch){//判断题目正确与否 参数为用户输入答案 返回值为答案是否正确
        return currentQuestion.determine(ch);
    }

    public String getCorrectOptions(){//返回答案
        return currentQuestion.getCorrectOptions();
    }

    private void initialization(int i){//初始化控制器
        try {
                db.setId(i);
                List<String> now=db.getnext();
                currentQuestion=new ChoiceQuestion(now.subList(1,now.size()-1),now.get(0),now.get(now.size()-1));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCurrent (int current)
    {
        this.current = current;
        initialization(current);
    }
}
