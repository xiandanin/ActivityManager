package io.xiandan.am.example;

import android.app.ActivityManager;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import io.xiandan.utils.ActivityLifecycleManager;

/**
 * @author xiandanin
 *         created 2017/12/8 17:08
 */
public class MenuActivity extends AppCompatActivity {
    private final Class[] cls = new Class[]{AActivity.class, BActivity.class, CActivity.class, DActivity.class};

    public void clickRandomActivity(MenuItem menuItem) {
        int index = new Random().nextInt(cls.length);
        startActivity(new Intent(this, cls[index]));
    }

    public void clickFinishTop(MenuItem menuItem) {
        //关闭栈顶Activity
        ActivityLifecycleManager.getInstance().finishTopActivity();
    }

    public void clickFinishAllA(MenuItem menuItem) {
        ActivityLifecycleManager.getInstance().finishActivityByClass(AActivity.class);
    }

    public void clickFinishAllCD(MenuItem menuItem) {
        //关闭所有BActivity,CActivity
        ActivityLifecycleManager.getInstance().finishActivityByClass(BActivity.class, CActivity.class);
    }

    public void clickFinishByWhitelist(MenuItem menuItem) {
        //保留DActivity,其余全部关闭
        ActivityLifecycleManager.getInstance().finishOutsideActivityTheClass(DActivity.class);
    }

    public void clickFinishAll(MenuItem menuItem) {
        //关闭所有Activity
        ActivityLifecycleManager.getInstance().finishAllActivity();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
