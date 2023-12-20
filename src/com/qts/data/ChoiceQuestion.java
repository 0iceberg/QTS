/**
 * Copyright (C), 2017-2023, 张瀚艺、靳明雨
 *
 * @Author: 张瀚艺、靳明雨
 * @Date: 2023/12/18 10:47
 * @FileName: ChoiceQuestion
 * @Description: 选择题类
 */

package com.qts.data;


import java.util.List;

public class ChoiceQuestion
{
    private List<String> option;//选项
    private String topic;//题目
    private String correctOptions;//正确答案

    public ChoiceQuestion (List<String> option, String topic, String correctOptions)
    {
        this.option = option;
        this.topic = topic;
        this.correctOptions = correctOptions;
    }

    public List<String> getoption ()//获取选项
    {
        return option;
    }

    public String getTopic ()//获取题目
    {
        return topic;
    }

    public String getCorrectOptions ()
    {
        return correctOptions;
    }

    public boolean determine (String ch)
    {

        return ch.equals(correctOptions);
    }

    private void setOption (List<String> option)
    {
        this.option = option;
    }

    private void setTopic (String topic)
    {
        this.topic = topic;
    }

    private void setCorrectOptions (String correctOptions)
    {
        this.correctOptions = correctOptions;
    }

}
