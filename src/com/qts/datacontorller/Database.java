package com.qts.datacontorller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2017-2023, 张瀚艺、靳明雨
 *
 * @Author: 张瀚艺、靳明雨
 * @Date: 2023/12/18 10:32
 * @FileName: Database
 * @Description: 数据库操作
 */
public class Database
{
    private Connection con;
    private int id=1;//数据表行号
    private int length;//数据库行数

    /**获取数据库对象
     * @author 张瀚艺、靳明雨
     * @date 2023/12/18 13:01
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Database Instance(){
            return new Database("localhost","sqt","root","include");
    }
    /**
     * 实例化数据库对象
     * @author 张瀚艺、靳明雨
     * @date 2023/12/18 19:41
     * @param ip ip地址
     * @param dataBaseName 数据库名称
     * @param user 用户名
     * @param password 密码
     *
     */
    public Database(String ip, String dataBaseName, String user, String password)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + ip + ":3306/" + dataBaseName + "?"
                + "useSSL=false&serverTimezone=GMT&characterEncoding=utf-8";
            con= DriverManager.getConnection(url, user, password);
            this.id=1;
            ResultSet resultSet = con.createStatement().executeQuery("select count(*) from questionnamebank");
            resultSet.next();
            this.length=Integer.parseInt(resultSet.getString("count(*)"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *获取下一题全部内容
     * 返回列表参数为String类型 [0]存放题目 [1-legth-1]存放选项 [legth]存放答案
     * @author 张瀚艺、靳明雨
     * @date 2023/12/18 19:45
     * @return List
     */
    public List<String> getnext () throws SQLException
    {
        List<String> res=new ArrayList<>();
        Statement st = this.con.createStatement();

        ResultSet last =st.executeQuery("select * from QuestionNameBank where id="+id++);
        last.next();
        res.add(last.getString("text"));//追加题目

        ResultSet cid = st.executeQuery("select * from QuestionOptionBank where cid=" + last.getString("cid"));cid.next();
        String a = cid.getString("text");
        String[] temp;
        String delimiter = "##";// 指定分割字符
        temp = a.split(delimiter);// 分割字符串
        for (String str : temp) {//追加选项
            res.add(str);
        }
        res.add(cid.getString("answer"));//追加答案

        return res;
    }
    /**
     * 获取表长度
     * @author 靳明雨
     * @date 2023/12/18 19:48
     * @return  length
     */
    public int getLength ()
    {
        return length;
    }

    /**
     *设置题目id
     * @author jmy
     * @date 2023/12/18 19:49
     * @param id 题目id
     *
     */
    public void setId (int id)
    {
        if(id>0)
            this.id = id;
    }
}
