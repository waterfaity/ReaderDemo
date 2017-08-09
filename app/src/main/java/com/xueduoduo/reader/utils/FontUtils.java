package com.xueduoduo.reader.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import com.xueduoduo.application.MyApp;

/**
 * Created by water_fairy on 2017/8/9.
 * 995637517@qq.com
 */

public class FontUtils {

    public static void setSongTiType(TextView textView) {
        Context appContext = MyApp.getAppContext();
        Typeface typeFace = Typeface.createFromAsset(appContext.getAssets(), "font/fangzhengxinshusong.ttf");
        textView.setTypeface(typeFace);
    }
}
