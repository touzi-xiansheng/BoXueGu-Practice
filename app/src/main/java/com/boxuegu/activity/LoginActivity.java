package com.boxuegu.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.boxuegu.R;
import com.boxuegu.utils.MD5Utils;

public class LoginActivity extends AppCompatActivity {

    private TextView tv_back;
    private TextView tv_main_title;
    private TextView tv_register;
    private TextView tv_find_psw;
    private Button btn_login;
    private EditText et_user_name;
    private EditText et_psw;
    private String userName;
    private String psw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    /**
     * 获取页面控件
     */
    private void init() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("登录");
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_find_psw = (TextView) findViewById(R.id.tv_find_psw);
        btn_login = (Button) findViewById(R.id.btn_login);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_psw = (EditText) findViewById(R.id.et_psw);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });

        /*立即注册按钮的点击事件*/
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        /*找回密码控件的点击事件*/
        tv_find_psw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*跳转到找回密码页面（暂未创建）*/
            }
        });

        /*登录按钮的点击事件*/
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = et_user_name.getText().toString().trim();
                psw = et_psw.getText().toString().trim();
                String md5Psw = MD5Utils.md5(psw);
                String spPsw = readPsw(userName);
                /*判断用户名和密码是否为空，如果为空有提示信息*/
                if(TextUtils.isEmpty(userName)) {
                    Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(psw)) {
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if (md5Psw.equals(spPsw)) {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    saveLoginStatus(true, userName);
                    Intent data = new Intent();
                    data.putExtra("isLogin", data);
                    setResult(RESULT_OK, data);
//                    LoginActivity.this.finish();
                    //跳转到注意
                    return;
                }else if (!TextUtils.isEmpty(spPsw) && !md5Psw.equals(spPsw)) {
                    Toast.makeText(LoginActivity.this, "用户名和密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Toast.makeText(LoginActivity.this, "此用户名不存在", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    private void saveLoginStatus(boolean status, String userName) {
        /*loginInfo表示文件名*/
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit(); //获取编辑器
        editor.putBoolean("isLogin", status); //存入boolean类型登录状态
        editor.putString("loginUserName", userName); //存入登录时的用户名
        editor.commit(); //提交修改
    }

    private String readPsw(String userName) {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        return sp.getString(userName, "");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null ) {
            //从注册界面传递过来的用户名
            String userName = data.getStringExtra("userName");
            if (!TextUtils.isEmpty(userName)) {
                et_user_name.setText(userName);
                //设置光标位置
                et_user_name.setSelection(userName.length());
            }
        }
    }
}
