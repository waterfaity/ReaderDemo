package com.xueduoduo.reader.read;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.waterfairy.utils.ToastUtils;
import com.xueduoduo.application.MyApp;
import com.xueduoduo.reader.R;
import com.xueduoduo.reader.database.BookDB;
import com.xueduoduo.reader.database.BookShelfDB;
import com.xueduoduo.reader.database.BookShelfDBDao;
import com.xueduoduo.reader.database.DaoMaster;
import com.xueduoduo.reader.utils.DataTransUtils;

import java.util.List;


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
        mTVIntroduceInfo.setText("简介:\n" + mBookDB.getIntroduce());
    }

    public void close(View view) {
        finish();
    }

    public void startRead(View view) {
        mBookDB.getConfigInfo();
        if (TextUtils.isEmpty(mBookDB.getConfigInfo())) {
            ToastUtils.show("暂未收录该书本");
            return;
        }
        BookShelfDBDao bookShelfDBDao = MyApp.getInstance().getDaoSession().getBookShelfDBDao();
        List<BookShelfDB> list = bookShelfDBDao.queryBuilder().where(BookShelfDBDao.Properties.BookId.eq(mBookDB.getBookId())).list();
        int readPage = 0;
        if (!(list != null && list.size() >= 1)) {
            bookShelfDBDao.save(DataTransUtils.getBookShelfDBFromBookDB(mBookDB));
        } else {
            readPage = list.get(0).getReadPage();
        }
        Intent intent = getIntent();
        intent.putExtra("readPage", readPage);
        intent.putExtra("bookName", mBookDB.getBookName());
        intent.setClass(this, ReadActivity.class);
        startActivity(intent);
    }
}
