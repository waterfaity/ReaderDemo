package com.xueduoduo.reader.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xueduoduo.application.MyApp;
import com.xueduoduo.reader.database.BookDB;
import com.xueduoduo.reader.database.BookDBDao;
import com.xueduoduo.reader.R;
import com.xueduoduo.reader.read.IntroduceActivity;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by water_fairy on 2017/8/9.
 * 995637517@qq.com
 */

public class FragmentMainList extends Fragment implements BaseQuickAdapter.OnItemChildClickListener {
    private View mRootView;
    private RecyclerView mRecyclerView;
    private List<BookDB> bookDBs;
    private BookRecyclerAdapter mAdapter;
    private int grade;
    private TextView mTVEmpty;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_book, container, false);
        initData();
        initView();
        return mRootView;
    }

    private void initData() {

        grade = getArguments().getInt("grade");
        QueryBuilder<BookDB> bookDBQueryBuilder = MyApp.getInstance().getDaoSession().getBookDBDao().queryBuilder();
        if (grade == 0) {
            bookDBs = bookDBQueryBuilder.list();
        } else {
            bookDBs = bookDBQueryBuilder.where(BookDBDao.Properties.Grade.eq(grade)).list();
        }

    }

    private void initView() {
        mTVEmpty = (TextView) mRootView.findViewById(R.id.empty);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        if (bookDBs == null || bookDBs.size() == 0) {
            mTVEmpty.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mTVEmpty.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mAdapter = new BookRecyclerAdapter(getActivity(), grade, bookDBs);
            mAdapter.setOnItemChildClickListener(this);
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        BookDB item = mAdapter.getItem(position);
        Intent intent = new Intent(getActivity(), IntroduceActivity.class);
        intent.putExtra("book", item);
        startActivity(intent);
    }
}
