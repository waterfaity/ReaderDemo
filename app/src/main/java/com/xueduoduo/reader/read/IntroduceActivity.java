package com.xueduoduo.reader.read;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xueduoduo.reader.R;
import com.xueduoduo.reader.database.BookDB;


public class IntroduceActivity extends AppCompatActivity {
    private BookDB mBookDB;
    private TextView mTVIntroduceInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        initView();
    }

    private void initView() {
        mBookDB = (BookDB) getIntent().getSerializableExtra("book");
        mTVIntroduceInfo = (TextView) findViewById(R.id.introduce_info);
        mTVIntroduceInfo.setText("简介:\n"+mBookDB.getIntroduce());
    }

    public void close(View view) {
        finish();
    }
}
