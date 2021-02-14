package com.boxuegu.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.boxuegu.MainActivity;
import com.boxuegu.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        /*设置此界面为竖屏*/
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    /*获取版本号*/
    private void init() {
        /*版本号处理*/
        TextView tv_version = (TextView) findViewById(R.id.tv_version);
        try {
            PackageInfo info =  getPackageManager().getPackageInfo(getPackageName(),0);
            tv_version.setText("V" + info.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            tv_version.setText("V");
        }

        /*设置页面跳转，利用timer让此页面延迟3秒后跳转，timer又一个线程，这个线程不断执行task*/
        Timer timer = new Timer();
        /*timertask实现runnable接口，TimeTask类表示在一个指定时间内执行task*/
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        };

        timer.schedule(task, 3000);  //设置task在延迟三秒后自动换行


    }
}

