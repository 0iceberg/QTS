/**
 * Copyright (C), 2017-2023, 张瀚艺、靳明雨
 *
 * @Author: 张瀚艺、靳明雨
 * @Date: 2023/12/18 10:21
 * @FileName: ${NAME}
 * @Description: ${DESCRIPTION}
 */
import com.qts.basepage.BasePage;
import com.qts.sound.Bgm;
public class Main
{
    public static void main (String[] args)
    {
        //主页面启动
        new BasePage().runPage();
        //BGM启动
        Bgm bgm = new Bgm();
        bgm.bgm_Start();
    }

}