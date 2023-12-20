/**
 * Copyright (C), 2017-2023, 张瀚艺、靳明雨
 *
 * @Author: 张瀚艺、靳明雨
 * @Date: 2023/12/18 10:54
 * @FileName: Display
 * @Description: 显示接口
 */

package com.qts.basepage;

import java.util.List;

public interface Display
{
    void setTopic(String text);//设置题目
    void setOption(List<String> opt);//设置选项
//    void redisPlay();//重新显示

}
