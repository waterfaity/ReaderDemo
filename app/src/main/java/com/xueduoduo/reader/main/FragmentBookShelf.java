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
import com.xueduoduo.reader.database.BookShelfDB;
import com.xueduoduo.reader.R;
import com.xueduoduo.reader.database.BookShelfDBDao;
import com.xueduoduo.reader.read.IntroduceActivity;
import com.xueduoduo.reader.utils.DataTransUtils;

import java.util.List;

/**
 * Created by water_fairy on 2017/8/9.
 * 995637517@qq.com
 */

public class FragmentBookShelf extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener, BaseQuickAdapter.OnItemChildClickListener, TextWatcher {
    private View mRootView;
    private RecyclerView mRecyclerView;
    private ImageView mIVSearch, mIVDeleteSearchContent;
    private EditText mEtSearch;
    private List<BookShelfDB> bookShelfDBs;
    private BookRecyclerAdapter mAdapter;
    private TextView mTVEmpty;
    private boolean isSearch;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_book_shelf, container, false);
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
        mEtSearch.addTextChangedListener(this);
    }

    private void initData() {
        if (!MyApp.getInstance().getNewSave()) {
            initRecycler();
        }
    }

    private void initRecycler() {
        String searchText = mEtSearch.getText().toString();
        if (TextUtils.isEmpty(searchText)) {
            bookShelfDBs = MyApp.getInstance().getDaoSession().getBookShelfDBDao().loadAll();
        } else {
            bookShelfDBs = MyApp.getInstance().getDaoSession().getBookShelfDBDao().queryBuilder()
                    .where(BookShelfDBDao.Properties.BookName.like(searchText)).list();
        }
        if (bookShelfDBs != null && bookShelfDBs.size() > 0) {
            mTVEmpty.setVisibility(View.GONE);
        } else {
            if (isSearch) {
                mTVEmpty.setText("书架中没有查到该书本");
            } else {
                mTVEmpty.setText("书架中暂无书本");
            }
            mTVEmpty.setVisibility(View.VISIBLE);
        }
        mAdapter = new BookRecyclerAdapter(getActivity(), 0, DataTransUtils.getBookDBListFromBookShelfDB(bookShelfDBs));
        mAdapter.setOnItemChildClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        isSearch = false;
    }

    public static FragmentBookShelf newInstance() {
        return new FragmentBookShelf();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.search_img) {
            search();
        } else if (v.getId() == R.id.delete_text) {
            mEtSearch.setText("");
            mIVDeleteSearchContent.setVisibility(View.INVISIBLE);
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
            isSearch = true;
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
        if (MyApp.getInstance().getNewSave()) {
            initRecycler();
            MyApp.getInstance().setNewSave(false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String searchText = s.toString();
        if (TextUtils.isEmpty(searchText)) {
            mIVDeleteSearchContent.setVisibility(View.INVISIBLE);
            initRecycler();
        } else {
            mIVDeleteSearchContent.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
