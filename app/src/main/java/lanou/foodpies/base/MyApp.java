package lanou.foodpies.base;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;

/**
 * Created by dllo on 16/11/10.
 * !!!!! 写完 MyApp 一定要注册
 * android:name=".tools.MyApp"
 * 跟界面无关的 context 可以用这个
 * 但是, 与界面相关, 如 Inflate()就不可以
 */
public class MyApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this; // 因为本类对象Application 就是Context的子类

        //    第一：默认初始化   建议初始化放到application里
        Bmob.initialize(this, "827b0566258335edfd3669efaa940c45");

    }

    public static Context getContext(){
        return context;
    }
}

