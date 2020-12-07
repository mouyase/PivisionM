package tech.yojigen.pivisionR;

import android.app.Application;

import com.xuexiang.xui.XUI;

import tech.yojigen.utils.YUtil;

public class PixiuApplicaiton extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        YUtil.init(this);
        XUI.init(this); //初始化UI框架
        XUI.debug(true);  //开启UI框架调试日志
    }
}
