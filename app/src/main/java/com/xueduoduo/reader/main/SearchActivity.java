package com.xueduoduo.reader.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xueduoduo.reader.R;


public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        FragmentSearch fragmentSearch = FragmentSearch.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString("searchContent", getIntent().getStringExtra("searchContent"));
        fragmentSearch.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, fragmentSearch).commitAllowingStateLoss();
    }
}
