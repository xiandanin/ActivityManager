package com.dyhdyh.manager.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.dyhdyh.manager.ActivityManager;

import java.util.Random;

/**
 * @author dengyuhan
 *         created 2017/12/8 14:21
 */
public abstract class ExampleActivity extends AppCompatActivity {

    private final Class[] cls = new Class[]{Activity.class, BActivity.class, CActivity.class, DActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        TextView tv = findViewById(R.id.tv);
        tv.setText(getTag());
    }

    protected abstract String getTag();


    public void clickRandomActivity(MenuItem menuItem) {
        int index = new Random().nextInt(cls.length);
        startActivity(new Intent(this, cls[index]));
    }

    public void clickFinishAllA(MenuItem menuItem) {
        ActivityManager.finishActivity(Activity.class);
    }

    public void clickFinishAllCD(MenuItem menuItem) {
        ActivityManager.finishActivity(BActivity.class, CActivity.class);
    }

    public void clickFinishByWhitelist(MenuItem menuItem) {
        ActivityManager.finishAllActivityByWhitelist(DActivity.class);
    }

    public void clickFinishAll(MenuItem menuItem) {
        ActivityManager.finishAllActivity();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}