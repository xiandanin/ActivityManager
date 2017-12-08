package com.dyhdyh.manager;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * @author dengyuhan
 *         created 2017/12/8 10:47
 */
public class ActivityManagerLifecycleCallbackImpl implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        ActivityManager.addActivity(activity);
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
        ActivityManager.removeActivity(activity);
    }
}