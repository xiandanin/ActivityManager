package io.xiandan.am.example;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;


import io.xiandan.utils.ActivityLifecycleManager;
import io.xiandan.utils.SimpleActivityLifecycleCallbacks;

/**
 * @author xiandanin
 *         created 2017/12/8 14:48
 */
public class ExampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        WindowLog.create(this);

        //注册管理器
        ActivityLifecycleManager.getInstance().register(this);

        registerActivityLifecycleCallbacks(new SimpleActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                WindowLog.updateLog();
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                WindowLog.updateLog();
            }
        });
    }
}
