package com.dyhdyh.manager.example;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.dyhdyh.manager.ActivityManager;

/**
 * @author dengyuhan
 *         created 2017/12/8 14:48
 */
public class ExampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        WindowLog.create(this);

        //注册管理器
        ActivityManager.getInstance().register(this);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                WindowLog.updateLog();
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                WindowLog.updateLog();
            }
        });
    }
}
