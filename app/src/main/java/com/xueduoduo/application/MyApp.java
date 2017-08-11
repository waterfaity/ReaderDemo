package com.xueduoduo.application;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;

import com.waterfairy.utils.ToastUtils;
import com.xueduoduo.reader.database.DaoMaster;
import com.xueduoduo.reader.database.DaoSession;
import com.xueduoduo.http.bean.ClassInfoBean;
import com.xueduoduo.http.bean.UserBean;
import com.xueduoduo.reader.utils.ShareUtil;

import java.util.ArrayList;

/**
 * Created by water_fairy on 2017/7/18.
 * 995637517@qq.com
 */

public class MyApp extends Application {
    private static MyApp NFC_APP;
    private SQLiteDatabase sqLiteDatabase;
    private DaoSession daoSession;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private DaoMaster daoMaster;
    private ArrayList<ClassInfoBean> classInfo;
    private boolean newSave;//新的书架保存

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        NFC_APP = this;
        ShareUtil.init(this);
        ToastUtils.initToast(this);
        setDatabase();
    }

    //    /**
//     * 设置greenDao
//     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        devOpenHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        sqLiteDatabase = devOpenHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(sqLiteDatabase);
        daoSession = daoMaster.newSession();
    }

    //
    public DaoSession getDaoSession() {
        return daoSession;
    }

    public SQLiteDatabase getDb() {
        return sqLiteDatabase;
    }

    public static MyApp getInstance() {
        return NFC_APP;
    }

    public static Context getAppContext() {
        return NFC_APP.getApplicationContext();
    }

    public static PackageInfo getPackageInfo() throws PackageManager.NameNotFoundException {
        return NFC_APP.getApplicationContext().getPackageManager().getPackageInfo(getAppContext().getPackageName(), 0);
    }

    public static String getAndroidVersion() {
        return android.os.Build.VERSION.SDK_INT + "";
    }

    public UserBean getUserInfo() {
        return ShareUtil.getUserBean();
    }

    public void setClassInfo(ArrayList<ClassInfoBean> classInfo) {
        this.classInfo = classInfo;
    }

    public ArrayList<ClassInfoBean> getClassInfo() {
        return classInfo;
    }

    public float getDensity() {
        return getAppContext().getResources().getDisplayMetrics().density;
    }

    public boolean getNewSave() {
        return newSave;
    }

    public MyApp setNewSave(boolean newSave) {
        this.newSave = newSave;
        return this;
    }
}
