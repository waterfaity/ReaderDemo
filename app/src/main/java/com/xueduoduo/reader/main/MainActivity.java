package com.xueduoduo.reader.main;

import android.graphics.Color;
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
    private List<ImageView> mTabImgList;
    private ImageView mCurrentTabImg;
    private List<TextView> mTabTextList;
    private TextView mCurrentTabText;


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
        mCurrentTabImg = mIVTabFirst;
        mTabImgList = new ArrayList<>();
        mTabImgList.add(mIVTabFirst);
        mTabImgList.add(mIVTabBookShelf);
        mTabImgList.add(mIVTabMy);

        mTVTabFirst = (TextView) mLLTabFirst.findViewById(R.id.tab_text);
        mTVTabBookShelf = (TextView) mLLTabBookShelf.findViewById(R.id.tab_text);
        mTVTabMy = (TextView) mLLTabMy.findViewById(R.id.tab_text);

        mTVTabFirst.setText("首页");
        mTVTabBookShelf.setText("书架");
        mTVTabMy.setText("我的");
        mTabTextList = new ArrayList<>();
        mTabTextList.add(mTVTabFirst);
        mTabTextList.add(mTVTabBookShelf);
        mTabTextList.add(mTVTabMy);

        mCurrentTabText = mTVTabFirst;
    }

    private void initView() {

        mIVTabFirst.setImageResource(R.mipmap.first_sel);
        mTVTabFirst.setTextColor(getResources().getColor(R.color.colorAccentLogin));
        mIVTabBookShelf.setImageResource(R.mipmap.book_shelf);
        mIVTabMy.setImageResource(R.mipmap.my);

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
        switchButton(pos);
        getSupportFragmentManager().beginTransaction().attach(tempFragment)
                .detach(mCurrentFragment).commitAllowingStateLoss();
        mCurrentFragment = tempFragment;
    }

    private int lastSel;

    private void switchButton(int pos) {
        ImageView imageView = mTabImgList.get(pos);
        int lastRes = 0;
        if (lastSel == 0) {
            lastRes = R.mipmap.first;
        } else if (lastSel == 1) {
            lastRes = R.mipmap.book_shelf;
        } else {
            lastRes = R.mipmap.my;
        }
        int curRes = 0;
        if (pos == 0) {
            curRes = R.mipmap.first_sel;
        } else if (pos == 1) {
            curRes = R.mipmap.book_shelf_sel;
        } else {
            curRes = R.mipmap.my_sel;
        }
        imageView.setImageResource(curRes);
        mCurrentTabImg.setImageResource(lastRes);
        mCurrentTabImg = imageView;

        mCurrentTabText.setTextColor(Color.WHITE);
        mCurrentTabText = mTabTextList.get(pos);
        mCurrentTabText.setTextColor(getResources().getColor(R.color.colorAccentLogin));
        lastSel = pos;

    }
}
