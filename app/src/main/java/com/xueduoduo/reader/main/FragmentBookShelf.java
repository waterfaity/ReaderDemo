package com.xueduoduo.reader.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xueduoduo.reader.database.BookDB;
import com.xueduoduo.reader.database.BookShelfDB;
import com.xueduoduo.reader.R;

import java.util.List;

/**
 * Created by water_fairy on 2017/8/9.
 * 995637517@qq.com
 */

public class FragmentBookShelf extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener {
    private View mRootView;
    private RecyclerView mRecyclerView;
    private ImageView mIVSearch;
    private EditText mEtSearch;
    private List<BookShelfDB> bookShelfDBs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_book_shelf, container, false);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        mEtSearch = (EditText) mRootView.findViewById(R.id.search_book);
        mIVSearch = (ImageView) mRootView.findViewById(R.id.search_img);
        mIVSearch.setOnClickListener(this);
        mEtSearch.setOnEditorActionListener(this);
    }

    private void initData() {

    }

    public static FragmentBookShelf newInstance() {
        return new FragmentBookShelf();
    }

    private void search() {
        String searchText = mEtSearch.getText().toString();
        if (TextUtils.isEmpty(searchText)){

        }
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
