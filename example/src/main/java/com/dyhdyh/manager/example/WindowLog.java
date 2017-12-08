package com.dyhdyh.manager.example;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.dyhdyh.manager.ActivityManager;

import java.util.Stack;

/**
 * @author dengyuhan
 *         created 2017/12/8 14:45
 */
public class WindowLog {
    private static View layout;


    public static void create(Context context) {
        //赋值WindowManager&LayoutParam.
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        int actionBarSize = 0;
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, typedValue, true)) {
            actionBarSize = TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        }
        params.y = actionBarSize;

        params.gravity = Gravity.LEFT | Gravity.TOP;
        LayoutInflater inflater = LayoutInflater.from(context);
        layout = inflater.inflate(R.layout.window_log, null);
        windowManager.addView(layout, params);
    }

    public static void setVisibility(boolean visible) {
        if (layout == null) {
            return;
        }
        layout.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public static void updateLog() {
        TextView tv = layout.findViewById(R.id.tv_log);
        StringBuffer sb = new StringBuffer();
        sb.append("当前打开Activity：");
        sb.append(ActivityManager.getInstance().getActivityCount());
        sb.append("个");
        sb.append("\n");
        sb.append("栈顶Activity：");
        Activity peek = ActivityManager.getInstance().getActivityStack().peek();
        sb.append(peek.getClass().getSimpleName() + "@" + Integer.toHexString(peek.hashCode()));
        sb.append("\n");
        sb.append("\n");

        Stack<Activity> stack = ActivityManager.getInstance().getActivityStack();
        for (int i = stack.size() - 1; i >= 0; i--) {
            Activity activity = stack.get(i);
            sb.append(i);
            sb.append(" - ");
            sb.append(activity.getClass().getSimpleName() + "@" + Integer.toHexString(activity.hashCode()));
            sb.append("\n");
        }
        tv.setText(sb.toString());
    }

}
