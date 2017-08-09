package com.xueduoduo.reader.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xueduoduo.http.BaseCallback;
import com.xueduoduo.http.RetrofitRequest;
import com.xueduoduo.http.RetrofitService;
import com.xueduoduo.http.bean.UserBean;
import com.xueduoduo.http.response.BaseResponse;
import com.xueduoduo.reader.R;
import com.waterfairy.utils.ToastUtils;
import com.xueduoduo.reader.dialog.LoadingDialog;
import com.xueduoduo.reader.main.MainActivity;
import com.xueduoduo.reader.utils.ShareUtil;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    private static final String TAG = "loginAct";
    // UI references.
    private EditText mETAccount;
    private EditText mETPassword;
    private LoadingDialog mDialogLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mETAccount = (EditText) findViewById(R.id.account);
        mETPassword = (EditText) findViewById(R.id.password);
        mETPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if ((id == R.id.action_login || id == EditorInfo.IME_NULL) && keyEvent != null) {
                    if (keyEvent.getAction() == KeyEvent.ACTION_UP) {
                        login();
                    }
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.login);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        String account = ShareUtil.getAccount();
        if (!TextUtils.isEmpty(account)) {
            mETAccount.setText(account);
            mETPassword.setText(ShareUtil.getPassword());
        } else {
            mETAccount.setText("teacher");
            mETPassword.setText("c230415d9f455e93e080d23e7727a1a8");
        }
    }

    private void login() {
        if (mDialogLoading == null) {
            mDialogLoading = new LoadingDialog(this);
        }
        mDialogLoading.show();
        final String account = mETAccount.getText().toString();
        if (TextUtils.isEmpty(account)) {
            ToastUtils.show("请输入帐号");
            return;
        }
        final String password = mETPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            ToastUtils.show("请输入密码");
            return;
        }

        RetrofitService normalRetrofit = RetrofitRequest.getInstance().getNormalRetrofit();
        normalRetrofit.login(account, password).enqueue(new BaseCallback<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse baseResponse) {
                mDialogLoading.dismiss();
                UserBean user = baseResponse.getUser();
                ShareUtil.saveAccountAndPassword(account, password);
                ShareUtil.saveUserBeanJson(user);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                ToastUtils.show("登录成功");
                finish();
            }

            @Override
            public void onFailed(int code, final String message) {
                mDialogLoading.dismiss();
                ToastUtils.show(message);
            }
        });
    }

    public void forgetPassword(View view) {
        ToastUtils.show("忘记密码");
    }
}

