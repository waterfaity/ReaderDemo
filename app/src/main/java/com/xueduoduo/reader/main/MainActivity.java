package com.xueduoduo.reader.main;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xueduoduo.reader.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mIVTabFirst, mIVTabBookShelf, mIVTabMy;
    private TextView mTVTabFirst, mTVTabBookShelf, mTVTabMy;
    private LinearLayout mLLTabFirst, mLLTabBookShelf, mLLTabMy;
    private List<Fragment> mFragmentList;
    private Fragment mCurrentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initView();
        initData();
    }

    private void initData() {

    }

    private void findView() {
        mLLTabFirst = (LinearLayout) findViewById(R.id.tab_first).findViewById(R.id.tab);
        mLLTabBookShelf = (LinearLayout) findViewById(R.id.tab_book_shelf).findViewById(R.id.tab);
        mLLTabMy = (LinearLayout) findViewById(R.id.tab_my).findViewById(R.id.tab);

        mIVTabFirst = (ImageView) mLLTabFirst.findViewById(R.id.tab_img);
        mIVTabBookShelf = (ImageView) mLLTabBookShelf.findViewById(R.id.tab_img);
        mIVTabMy = (ImageView) mLLTabMy.findViewById(R.id.tab_img);

        mTVTabFirst = (TextView) mLLTabFirst.findViewById(R.id.tab_text);
        mTVTabBookShelf = (TextView) mLLTabBookShelf.findViewById(R.id.tab_text);
        mTVTabMy = (TextView) mLLTabMy.findViewById(R.id.tab_text);
    }

    private void initView() {
        mLLTabFirst.setOnClickListener(this);
        mLLTabBookShelf.setOnClickListener(this);
        mLLTabMy.setOnClickListener(this);

        mFragmentList = new ArrayList<>();
        mCurrentFragment = new FragmentFirst();
        mFragmentList.add(mCurrentFragment);
        FragmentBookShelf fragmentBookShelf = new FragmentBookShelf();
        mFragmentList.add(fragmentBookShelf);
        FragmentMy fragmentMy = new FragmentMy();
        mFragmentList.add(fragmentMy);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout, mCurrentFragment)
                .add(R.id.frame_layout, fragmentBookShelf)
                .add(R.id.frame_layout, fragmentMy)
                .attach(mCurrentFragment)
                .detach(fragmentBookShelf)
                .detach(fragmentMy).commitAllowingStateLoss();

    }

    @Override
    public void onClick(View v) {
        switch (((View) (v.getParent())).getId()) {
            case R.id.tab_first:
                switchFragment(0);
                break;
            case R.id.tab_book_shelf:
                switchFragment(1);
                break;
            case R.id.tab_my:
                switchFragment(2);
                break;
        }
    }

    private void switchFragment(int pos) {
        Fragment tempFragment = mFragmentList.get(pos);
        if (tempFragment == mCurrentFragment) return;
        getSupportFragmentManager().beginTransaction().attach(tempFragment)
                .detach(mCurrentFragment).commitAllowingStateLoss();
        mCurrentFragment = tempFragment;
    }
}
