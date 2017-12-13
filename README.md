# ActivityManager
一个管理所有Activity的库，可以在任意处关闭Activity，操作已经打开的Activity

### __Gradle引入__
```
compile 'com.dyhdyh:activity-manager:1.0.0'
```

### __在Application注册__
```
public class ExampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //注册管理器
        ActivityManager.getInstance().register(this);
}
```

### __示例__
```
//关闭栈顶Activity
ActivityManager.getInstance().finishTopActivity();

//关闭所有BActivity,CActivity
ActivityManager.getInstance().finishActivity(BActivity.class, CActivity.class);

//保留DActivity,其余全部关闭
ActivityManager.getInstance().finishAllActivityByWhitelist(DActivity.class);

//关闭所有Activity
ActivityManager.getInstance().finishAllActivity();
```

### __示例apk__
![](image/qrcode.png)