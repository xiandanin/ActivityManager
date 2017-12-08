package com.dyhdyh.manager.example;

import android.Manifest;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Method;

public class MainActivity extends MenuActivity {
    TextView tv;
    View btnSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        btnSetting = findViewById(R.id.btn_setting);

    }

    public void clickStartSetting(View v) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        startActivity(localIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (tv == null) {
            return;
        }

        if (!checkWindowPermission(this)) {
            tv.setText("需要打开“悬浮窗”权限");
            btnSetting.setVisibility(View.VISIBLE);
        } else {
            tv.setText("点击右上角菜单");
            btnSetting.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }

    /**
     * 判断 悬浮窗口权限是否打开
     *
     * @param context
     * @return true 允许  false禁止
     */
    public static boolean checkWindowPermission(Context context) {
        try {
            Object object = context.getSystemService("appops");
            if (object == null) {
                return false;
            }
            Class localClass = object.getClass();
            Class[] arrayOfClass = new Class[3];
            arrayOfClass[0] = Integer.TYPE;
            arrayOfClass[1] = Integer.TYPE;
            arrayOfClass[2] = String.class;
            Method method = localClass.getMethod("checkOp", arrayOfClass);
            if (method == null) {
                return false;
            }
            Object[] arrayOfObject1 = new Object[3];
            arrayOfObject1[0] = Integer.valueOf(24);
            arrayOfObject1[1] = Integer.valueOf(Binder.getCallingUid());
            arrayOfObject1[2] = context.getPackageName();
            int m = ((Integer) method.invoke(object, arrayOfObject1)).intValue();
            return m == AppOpsManager.MODE_ALLOWED;
        } catch (Exception ex) {

        }
        return false;
    }
}
