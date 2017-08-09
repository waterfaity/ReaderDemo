package com.xueduoduo.reader.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.waterfairy.dialog.BaseDialog;
import com.xueduoduo.reader.R;

/**
 * Created by water_fairy on 2017/8/8.
 * 995637517@qq.com
 */

public class LoadingDialog extends BaseDialog {
    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.dialog, R.layout.layout_loading);
        setCancelable(false);
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
    }
}
