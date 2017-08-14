package com.xueduoduo.reader.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.waterfairy.utils.ToastUtils;
import com.xueduoduo.application.MyApp;
import com.xueduoduo.reader.database.BookDB;
import com.xueduoduo.reader.database.BookDBDao;
import com.xueduoduo.reader.database.BookShelfDB;
import com.xueduoduo.reader.R;
import com.xueduoduo.reader.database.BookShelfDBDao;
import com.xueduoduo.reader.read.IntroduceActivity;

import java.util.List;

/**
 * Created by water_fairy on 2017/8/9.
 * 995637517@qq.com
 */

public class FragmentSearch extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener, BaseQuickAdapter.OnItemChildClickListener {
    private View mRootView;
    private RecyclerView mRecyclerView;
    private ImageView mIVSearch, mIVDeleteSearchContent;
    private EditText mEtSearch;
    private List<BookDB> bookDBs;
    private BookRecyclerAdapter mAdapter;
    private TextView mTVEmpty;
    private String searchContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_search, container, false);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        mIVDeleteSearchContent = (ImageView) mRootView.findViewById(R.id.delete_text);
        mIVDeleteSearchContent.setOnClickListener(this);
        mTVEmpty = (TextView) mRootView.findViewById(R.id.empty);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mEtSearch = (EditText) mRootView.findViewById(R.id.search_book);
        mIVSearch = (ImageView) mRootView.findViewById(R.id.search_img);
        mIVSearch.setOnClickListener(this);
        mEtSearch.setOnEditorActionListener(this);
        mRootView.findViewById(R.id.back).setOnClickListener(this);
    }

    private void initData() {
        searchContent = getArguments().getString("searchContent");
    }

    private void initRecycler() {
        if (!TextUtils.isEmpty(searchContent)) {
            mEtSearch.setText(searchContent);
            searchContent = "";
        }
        String searchText = mEtSearch.getText().toString();
        if (TextUtils.isEmpty(searchText)) {
            bookDBs = MyApp.getInstance().getDaoSession().getBookDBDao().loadAll();
        } else {
            bookDBs = MyApp.getInstance().getDaoSession().getBookDBDao().queryBuilder()
                    .where(BookDBDao.Properties.BookName.like(searchText)).list();
        }
        if (bookDBs != null && bookDBs.size() > 0) {
            mTVEmpty.setVisibility(View.GONE);
        } else {
            mTVEmpty.setText("没有查到该书本");
            mTVEmpty.setVisibility(View.VISIBLE);
            ToastUtils.show("没有查到该书本");
        }
        mAdapter = new BookRecyclerAdapter(getActivity(), 0, bookDBs);
        mAdapter.setOnItemChildClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public static FragmentSearch newInstance() {
        return new FragmentSearch();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.search_img) {
            search();
        } else if (v.getId() == R.id.delete_text) {
            mEtSearch.setText("");
            mIVDeleteSearchContent.setVisibility(View.INVISIBLE);
        } else if (v.getId() == R.id.back) {
            getActivity().finish();
        }
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

    private void search() {
        if (TextUtils.isEmpty(mEtSearch.getText().toString())) {
            ToastUtils.show("请输入搜索内容");
        } else {
            initRecycler();
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        BookDB item = mAdapter.getItem(position);
        Intent intent = new Intent(getActivity(), IntroduceActivity.class);
        intent.putExtra("book", item);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        initRecycler();
    }
}
