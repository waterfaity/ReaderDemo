package com.xueduoduo.reader.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.waterfairy.utils.ToastUtils;
import com.xueduoduo.application.MyApp;
import com.xueduoduo.reader.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by water_fairy on 2017/8/9.
 * 995637517@qq.com
 */

public class FragmentFirst extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener, TextView.OnEditorActionListener {
    private View mRootView;
    private ViewPager mVPGrade;
    private LinearLayout mLLGrade;
    private String[] mGradeArray;
    private RelativeLayout mLastGrade;
    private List<RelativeLayout> mGradeList;
    private List<FragmentMainList> mBookFragmentList;
    private ImageView mIVSearch;
    private EditText mEtSearch;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_first, container, false);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        mEtSearch = (EditText) mRootView.findViewById(R.id.search_book);
        mIVSearch = (ImageView) mRootView.findViewById(R.id.search_img);
        mVPGrade = (ViewPager) mRootView.findViewById(R.id.view_pager);
        mLLGrade = (LinearLayout) mRootView.findViewById(R.id.grad_content);
        mIVSearch.setOnClickListener(this);
        mEtSearch.setOnEditorActionListener(this);
        initGradeLin();
        initViewPager();
    }

    private void search() {
        mEtSearch.getText().toString();
        ToastUtils.show("搜索");
    }

    private void initGradeLin() {
        mGradeArray = getResources().getStringArray(R.array.grade);
        mGradeList = new ArrayList<>();
        mBookFragmentList = new ArrayList<>();
        for (int i = 0; i < mGradeArray.length; i++) {
            RelativeLayout tempGrade = (RelativeLayout) LayoutInflater.from(getActivity()).inflate(R.layout.item_grade, mLastGrade, false);
            TextView gradName = (TextView) tempGrade.findViewById(R.id.grade_text);
            gradName.setText(mGradeArray[i]);
            if (i == 0) {
                mLastGrade = tempGrade;
            } else {
                ImageView gradLine = (ImageView) tempGrade.findViewById(R.id.grade_line);
                gradLine.setBackgroundColor(Color.WHITE);
                gradName.setTextColor(getResources().getColor(R.color.color_text_normal));
            }
            mLLGrade.addView(tempGrade);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tempGrade.getLayoutParams();
            float leftMargin = MyApp.getInstance().getDensity() * 10;
            layoutParams.leftMargin = (int) leftMargin;
            layoutParams.rightMargin = (int) leftMargin;
            mGradeList.add(tempGrade);
            tempGrade.setTag(i);
            tempGrade.setOnClickListener(onGradeClickListener);
            FragmentMainList mainBookListFragment = new FragmentMainList();
            Bundle bundle = new Bundle();
            bundle.putInt("grade", i);
            mainBookListFragment.setArguments(bundle);
            mBookFragmentList.add(mainBookListFragment);
        }
    }

    private void initViewPager() {
        mVPGrade.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mBookFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mBookFragmentList.size();
            }
        });
        mVPGrade.addOnPageChangeListener(this);
    }

    private View.OnClickListener onGradeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Integer page = (Integer) v.getTag();
            mVPGrade.setCurrentItem(page);
        }
    };

    private void initData() {


    }

    private void switchGrade(int pos) {
        RelativeLayout tempGrade = mGradeList.get(pos);
        if (mLastGrade == tempGrade) return;
        ImageView gradLine = (ImageView) tempGrade.findViewById(R.id.grade_line);
        TextView gradName = (TextView) tempGrade.findViewById(R.id.grade_text);
        gradLine.setBackgroundResource(R.drawable.style_bg_grade_scroll);
        gradName.setTextColor(getResources().getColor(R.color.colorAccentLogin));

        ImageView gradLineLast = (ImageView) mLastGrade.findViewById(R.id.grade_line);
        TextView gradNameLast = (TextView) mLastGrade.findViewById(R.id.grade_text);
        gradLineLast.setBackgroundColor(Color.WHITE);
        gradNameLast.setTextColor(getResources().getColor(R.color.color_text_normal));

        mLastGrade = tempGrade;
    }

    public static FragmentFirst newInstance() {
        return new FragmentFirst();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switchGrade(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        search();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if ((actionId == R.id.action_login || actionId == EditorInfo.IME_NULL) && event != null) {
            if (event.getAction() == KeyEvent.ACTION_UP) {
                search();
            }
            return true;
        }
        return false;
    }
}
