package io.xiandan.utils;

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

/**
 * Created by xiandanin on 2022-01-20 23:45
 */
public class ActivityLifecycleManager {

    private static class ActivityLifecycleManagerHolder {
        private static final ActivityLifecycleManager instance = new ActivityLifecycleManager();
    }

    private ActivityLifecycleManager() {
    }

    public static ActivityLifecycleManager getInstance() {
        return ActivityLifecycleManagerHolder.instance;
    }

    private final Stack<Activity> stack = new Stack<>();

    public void register(Application application) {
        application.registerActivityLifecycleCallbacks(new SimpleActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity) {
                pushActivity(activity);
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                removeActivity(activity);
            }
        });
    }

    public void pushActivity(Activity activity) {
        synchronized (this) {
            stack.push(activity);
        }
    }

    public void removeActivity(Activity activity) {
        synchronized (this) {
            stack.remove(activity);
        }
    }

    public void removeActivities(Collection<Activity> activities) {
        synchronized (this) {
            stack.removeAll(activities);
        }
    }

    private void finishActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }

    /**
     * 返回栈顶的Activity但不移除
     */
    public Activity peekActivity() {
        synchronized (this) {
            return stack != null && !stack.isEmpty() ? stack.peek() : null;
        }
    }

    /**
     * 返回栈顶的Activity并移除
     */
    public Activity popActivity() {
        synchronized (this) {
            return stack != null && !stack.isEmpty() ? stack.pop() : null;
        }
    }

    /**
     * 关闭栈顶Activity
     */
    public void finishTopActivity() {
        finishActivity(popActivity());
    }

    /**
     * 关闭栈顶多个Activity
     */
    public void finishTopActivity(int count) {
        for (int i = 0; i < count; i++) {
            finishActivity(popActivity());
        }
    }

    /**
     * 根据class关闭activity并移除
     *
     * @param classes
     */
    public final void finishActivityByClass(Class<? extends Activity>... classes) {
        String[] classNames = new String[classes.length];
        for (int i = 0; i < classes.length; i++) {
            classNames[i] = classes[i].getName();
        }
        finishActivityByClassName(classNames);
    }

    public void finishActivityByClassName(String... className) {
        finishActivityByPredicate(new Predicate() {
            @Override
            public boolean test(Activity activity) {
                for (String s : className) {
                    if (activity.getClass().getName().equals(s)) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    /**
     * 自定义条件关闭activity并移除
     *
     * @param predicate
     */
    public void finishActivityByPredicate(Predicate predicate) {
        List<Activity> activities = new ArrayList<>();
        for (int i = 0; i < stack.size(); i++) {
            Activity activity = stack.get(i);
            if (predicate.test(activity)) {
                activities.add(activity);
                finishActivity(activity);
            }
        }
        removeActivities(activities);
    }

    public void finishOutsideActivityTheClass(Class<? extends Activity>... classes) {
        String[] classNames = new String[classes.length];
        for (int i = 0; i < classes.length; i++) {
            classNames[i] = classes[i].getName();
        }
        finishOutsideActivityTheClassName(classNames);
    }

    /**
     * 关闭并移除白名单以外的activity
     */
    public void finishOutsideActivityTheClassName(String... className) {
        finishActivityByPredicate(new Predicate() {
            @Override
            public boolean test(Activity activity) {
                for (String s : className) {
                    if (!activity.getClass().getName().equals(s)) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    /**
     * 关闭并移除所有activity
     */
    public void finishAllActivity() {
        for (Activity activity : stack) {
            finishActivity(activity);
        }
        stack.clear();
    }

    public Stack<Activity> getActivityStack() {
        return stack;
    }

    public List<Activity> getActivities() {
        return new ArrayList<>(stack);
    }

    public interface Predicate {
        boolean test(Activity activity);
    }
}
