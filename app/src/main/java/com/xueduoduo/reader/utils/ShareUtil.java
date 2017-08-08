package com.xueduoduo.reader.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.waterfairy.utils.JsonUtils;
import com.xueduoduo.http.bean.UserBean;

/**
 * Created by water_fairy on 2017/8/8.
 * 995637517@qq.com
 */

public class ShareUtil {
    private static SharedPreferences mSettingShare;
    private static final String IS_LOGIN = "isLogin";
    public static final String SETTING = "setting";
    public static final String ACCOUNT = "account";
    public static final String PASSWORD = "password";
    public static final String USER_JSON = "userJson";

    public static void init(Context appContext) {
        mSettingShare = appContext.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
    }

    public static boolean isLogin() {
        return mSettingShare.getBoolean(IS_LOGIN, false);
    }

    public static void setLogin(boolean isLogin) {
        mSettingShare.edit().putBoolean(IS_LOGIN, isLogin).apply();
    }


    public static void saveAccountAndPassword(String account, String password) {
        mSettingShare.
                edit().
                putString(ACCOUNT, account).
                putString(PASSWORD, password).apply();
    }

    public static String getAccount() {
        return mSettingShare.getString(ACCOUNT, "");
    }

    public static String getPassword() {
        return mSettingShare.getString(PASSWORD, "");
    }

    public static void saveUserBeanJson(UserBean user) {
        mSettingShare.edit().putString(USER_JSON, JsonUtils.objectToJson(user)).apply();
    }

    public static void removeUserBeanJson() {
        mSettingShare.edit().putString(USER_JSON, "").apply();
    }

    public static UserBean getUserBean() {
        String string = mSettingShare.getString(USER_JSON, "");
        if (TextUtils.isEmpty(string)) {
            return new UserBean(false);
        } else {
            UserBean userBean = new Gson().fromJson(string, UserBean.class);
            userBean.isLogin = true;
            return userBean;
        }
    }

}
