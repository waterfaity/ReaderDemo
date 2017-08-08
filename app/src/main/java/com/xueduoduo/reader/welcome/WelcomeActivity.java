package com.xueduoduo.reader.welcome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xueduoduo.reader.R;
import com.xueduoduo.reader.login.LoginActivity;
import com.xueduoduo.reader.main.MainActivity;
import com.xueduoduo.reader.utils.ShareUtil;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
//        if (ShareUtil.getUserBean().isLogin) {
//            startActivity(new Intent(this, MainActivity.class));
//        } else {
        startActivity(new Intent(this, LoginActivity.class));
//        }
        finish();
    }
}
