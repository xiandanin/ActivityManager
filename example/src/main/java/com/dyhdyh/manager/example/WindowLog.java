package com.dyhdyh.manager.example;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.dyhdyh.manager.ActivityManager;

/**
 * @author dengyuhan
 *         created 2017/12/8 14:45
 */
public class WindowLog {
    private static TextView tv;


    public static void create(Context context) {
        //赋值WindowManager&LayoutParam.
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //设置type.系统提示型窗口，一般都在应用程序窗口之上.
        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        //设置效果为背景透明.
        params.format = PixelFormat.RGBA_8888;
        //设置flags.不可聚焦及不可使用按钮对悬浮窗进行操控.
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        //设置窗口初始停靠位置.
        params.gravity = Gravity.LEFT | Gravity.TOP;

        LayoutInflater inflater = LayoutInflater.from(context);
        //获取浮动窗口视图所在布局.
        View layout = inflater.inflate(R.layout.window_log, null);
        tv = layout.findViewById(R.id.tv_log);
        //添加toucherlayout
        windowManager.addView(layout, params);
    }

    public static void updateLog() {
        StringBuffer sb = new StringBuffer();
        sb.append("当前打开Activity：");
        sb.append(ActivityManager.getActivityCount());
        sb.append("\n");
        sb.append("栈底Activity：");
        sb.append(ActivityManager.getActivityCount());
        sb.append("栈顶Activity：");
        sb.append(ActivityManager.getActivityCount());
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
        tv.setText(sb.toString());
    }

}
