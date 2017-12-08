package com.dyhdyh.manager;

import android.app.Activity;
import android.app.Application;

import java.util.Stack;

/**
 * Activity管理器
 * @author dengyuhan
 *         created 2017/12/7 17:20
 */
public class ActivityManager {

    private static final Stack<Activity> mActivityStack = new Stack<>();

    public static void register(Application application, ActivityManagerLifecycleCallbackImpl callback) {
        application.registerActivityLifecycleCallbacks(callback);
    }

    public static void register(Application application) {
        register(application, new ActivityManagerLifecycleCallbackImpl());
    }

    public static void addActivity(Activity activity) {
        mActivityStack.push(activity);
    }

    public static void removeActivity(Activity activity) {
        mActivityStack.remove(activity);
    }


    /**
     * finish对应class的所有activity
     *
     * @param cls
     */
    public static void finishActivity(Class<? extends Activity>... cls) {
        for (int i = 0; i < mActivityStack.size(); i++) {
            Activity activity = mActivityStack.get(i);
            for (Class<? extends Activity> c : cls) {
                if (c.getName().equals(activity.getClass().getName())) {
                    finishActivity(activity);
                }
            }
        }
    }

    /**
     * finish除白名单以外的所有activity
     *
     * @param activityWhitelist 要保留的activity
     */
    public static void finishAllActivityByWhitelist(Class<? extends Activity>... activityWhitelist) {
        for (int i = 0; i < mActivityStack.size(); i++) {
            Activity activity = mActivityStack.get(i);
            for (Class<? extends Activity> c : activityWhitelist) {
                if (c.getName().equals(activity.getClass().getName())) {
                    break;
                } else {
                    finishActivity(activity);
                }
            }
        }
    }


    /**
     * finish所有activity
     */
    public static void finishAllActivity() {
        for (Activity activity : mActivityStack) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        mActivityStack.clear();
    }


    private static void finishActivity(Activity activity) {
        if (activity != null) {
            if (!activity.isFinishing()) {
                activity.finish();
                mActivityStack.remove(activity);
            }
        }
    }


    /**
     * @param activity
     * @return 是否存在此activity
     */
    public static boolean isContainsActivity(Activity activity) {
        return mActivityStack.contains(activity);
    }

    /**
     * @return 已经打开activity的数量
     */
    public static int getActivityCount() {
        return mActivityStack.size();
    }


}
