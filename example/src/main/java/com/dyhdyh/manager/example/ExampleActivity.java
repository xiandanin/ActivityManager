package com.dyhdyh.manager.example;

import android.os.Bundle;
import android.widget.TextView;

/**
 * @author dengyuhan
 *         created 2017/12/8 14:21
 */
public abstract class ExampleActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        TextView tv = findViewById(R.id.tv);
        tv.setText(getTag());
    }

    protected abstract String getTag();


}