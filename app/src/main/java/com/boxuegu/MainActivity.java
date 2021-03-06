package com.boxuegu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_main_title;
    private TextView tv_back;
    private RelativeLayout rl_title_bar;
    private FrameLayout mBodyLayout;
    private LinearLayout mBottomLayout;
    private TextView textView;
    private FrameLayout frameLayout;
    private RelativeLayout mCourseBtn;
    private RelativeLayout mExerciseBtn;
    private RelativeLayout mMyInfoBtn;
    private TextView tv_course;
    private TextView tv_exercise;
    private TextView tv_myInfo;
    private ImageView iv_course;
    private ImageView iv_exercise;
    private ImageView iv_myInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initBottomBar();
        setListener();
        setInitStatus();
    }

    /**
     * 进入页面默认选择课程
     */
    private void setInitStatus() {
        clearBottomImageState();
        setSelectedStatus(0);
        createView(0);
    }

    /**
     * 设置底部三个按钮的点击事件
     */
    private void setListener() {
        for (int i = 0; i < mBottomLayout.getChildCount(); i++) {
            mBottomLayout.getChildAt(i).setOnClickListener(this);
        }
    }

    /**
     *获取所有控件
     */
    private void initBottomBar() {
        mBottomLayout = (LinearLayout) findViewById(R.id.main_bottom_btn);
        mCourseBtn = (RelativeLayout) findViewById(R.id.bottom_bar_course_bth);
        mExerciseBtn = (RelativeLayout) findViewById(R.id.bottom_bar_exercise_bth);
        mMyInfoBtn = (RelativeLayout) findViewById(R.id.bottom_bar_myInfo_bth);

        tv_course = ((TextView) findViewById(R.id.bottom_bar_text_course));
        tv_exercise = (TextView) findViewById(R.id.bottom_bar_text_exercise);
        tv_myInfo = (TextView) findViewById(R.id.bottom_bar_text_myInfo);

        iv_course = (ImageView) findViewById(R.id.bottom_bar_image_course);
        iv_exercise = (ImageView) findViewById(R.id.bottom_bar_image_exercise);
        iv_myInfo = (ImageView) findViewById(R.id.bottom_bar_image_myInfo);
    }


    /**
     * 获取界面UI控件
     */
    private void init() {
        /*找到id，设置标题栏题目*/
        tv_back = ((TextView) findViewById(R.id.tv_back));
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("博学谷课程");
        /*找到id，设置标题栏背景颜色为#30BFF*/
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        /*隐藏返回按钮，因为这是主页*/
        tv_back.setVisibility(View.GONE);
        initBodyLayout();
    }

    private void initBodyLayout() {
        mBodyLayout = (FrameLayout) findViewById(R.id.min_body);
    }

    /**
     * 控件的点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //课程的点击事件
            case R.id.bottom_bar_course_bth:
                clearBottomImageState();
                selectDisplayView(0);
                break;

            //习题的点击事件
            case R.id.bottom_bar_exercise_bth:
                clearBottomImageState();
                selectDisplayView(1);
                break;
            //习题的点击事件
            case R.id.bottom_bar_myInfo_bth:
                clearBottomImageState();
                selectDisplayView(2);
                break;
        }
    }

    /**
     * 显示对应页面
     * @param index
     */
    private void selectDisplayView(int index) {
        /*移除所有的view（实际上视图设置隐藏）*/
        removeAllView();
        /*创建对应view*/
        createView(index);
        /*设置选中状态*/
        setSelectedStatus(index);
    }

    /**
     * 移除不需要的视图（隐藏视图）
     */
    private void removeAllView() {
        for (int i  = 0; i < mBodyLayout.getChildCount(); i++) {
            mBodyLayout.getChildAt(i).setVisibility(View.GONE);
        }
    }

    /**
     * 选择视图
     * @param viewIndex
     */
    private void createView(int viewIndex) {
        switch (viewIndex) {
            case 0:
                //TODO:课程界面
                break;
            case 1:
                //TODO:习题界面
                break;
            case 2:
                //TODO:我的界面
                break;
        }
    }

    /**
     * 设置底部按钮的选中状态
     */
    private void setSelectedStatus(int index) {
        switch (index) {
            case 0:
                mCourseBtn.setSelected(true);
                iv_course.setImageResource(R.drawable.main_course_icon_selected);
                tv_course.setTextColor(Color.parseColor("#0097f7"));
                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("博学谷课程");
                break;
            case 1:
                mExerciseBtn.setSelected(true);
                iv_exercise.setImageResource(R.drawable.main_exercises_icon_selected);
                tv_exercise.setTextColor(Color.parseColor("#0097f7"));
                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("博学谷习题");
                break;
            case 2:
                mMyInfoBtn.setSelected(true);
                iv_myInfo.setImageResource(R.drawable.main_my_icon_selected);
                tv_myInfo.setTextColor(Color.parseColor("#0097f7"));
                rl_title_bar.setVisibility(View.VISIBLE);
                break;
        }
   }

    /**
     * 清空之前的状态（设置为初始化状态）
     */
    private void clearBottomImageState() {
        tv_course.setTextColor(Color.parseColor("#666666"));
        tv_exercise.setTextColor(Color.parseColor("#666666"));
        tv_myInfo.setTextColor(Color.parseColor("#666666"));

        iv_course.setImageResource(R.drawable.main_course_icon);
        iv_exercise.setImageResource(R.drawable.main_exercises_icon);


        /*状态设置为未选中*/
        for (int i = 0; i < mBottomLayout.getChildCount(); i++) {
            mBottomLayout.getChildAt(i).setSelected(false);
        }
    }

    /**
     *
     */
    protected long exitTime; //记录一次点击时间
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if(System.currentTimeMillis() - exitTime > 2000){
                Toast.makeText(this,"再按一次退出博学谷", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                MainActivity.this.finish();
                /*检测登录状态*/
                if(readLoginStatus()){
                    //如果是已登录状态，则清除登录状态，清除登录时的用户名
                    clearLoginStatus();
                }
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 清除sharedpreference中的登录状态
     */
    private void clearLoginStatus() {
        SharedPreferences sp = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin", false);  //清除登录状态
        /*清除用户名*/
        editor.putString("loginUserName", ""); //清除登录时的用户名
        editor.commit();//提交修改
    }

    /**
     * 获取sharedpreference中的登录状态
     * @return
     */
    private boolean readLoginStatus() {
        SharedPreferences sp = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin", false);
        return isLogin;
    }
}