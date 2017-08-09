package com.xueduoduo.reader.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xueduoduo.reader.R;

/**
 * Created by water_fairy on 2017/8/9.
 * 995637517@qq.com
 */

public class FragmentMy extends Fragment {
    private View mRootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_my, container,false);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {

    }

    private void initData() {

    }
    public static FragmentMy newInstance() {
        return new FragmentMy();
    }
}
