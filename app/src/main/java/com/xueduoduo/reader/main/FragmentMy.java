package com.xueduoduo.reader.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.waterfairy.glide.translateform.BitmapCircleTransformation;
import com.xueduoduo.application.MyApp;
import com.xueduoduo.http.bean.UserBean;
import com.xueduoduo.reader.R;
import com.xueduoduo.reader.login.LoginActivity;
import com.xueduoduo.reader.utils.ShareUtil;

/**
 * Created by water_fairy on 2017/8/9.
 * 995637517@qq.com
 */

public class FragmentMy extends Fragment {
    private View mRootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_my, container, false);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        UserBean userInfo = MyApp.getInstance().getUserInfo();
        ImageView imageView = (ImageView) mRootView.findViewById(R.id.head);
        Glide.with(getActivity()).load(userInfo.getLogoUrl())
                .transform(new BitmapCircleTransformation(getActivity()))
                .into(imageView);
        TextView mName = (TextView) mRootView.findViewById(R.id.name);
        mName.setText(userInfo.getUserName());

        mRootView.findViewById(R.id.setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("退出?");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ShareUtil.removeUserBeanJson();
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void initData() {

    }

    public static FragmentMy newInstance() {
        return new FragmentMy();
    }
}
