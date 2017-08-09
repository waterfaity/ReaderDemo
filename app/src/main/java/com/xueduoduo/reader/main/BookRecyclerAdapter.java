package com.xueduoduo.reader.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xueduoduo.reader.database.BookDB;
import com.xueduoduo.reader.R;
import com.xueduoduo.reader.utils.DataTransUtils;

import java.util.List;

/**
 * Created by water_fairy on 2017/8/9.
 * 995637517@qq.com
 */

public class BookRecyclerAdapter extends BaseQuickAdapter<BookDB, BookRecyclerAdapter.ViewHolder> {
    private int width, height;
    private int grade;

    public BookRecyclerAdapter(Context context, int grade, @Nullable List<BookDB> data) {
        super(R.layout.item_book, data);
        this.grade = grade;
        mContext = context;
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        width = (int) ((displayMetrics.widthPixels - displayMetrics.density * 80) / 2);
        height = (int) (width * 180f / 135);

    }

    @Override
    protected void convert(final ViewHolder helper, final BookDB item) {
        helper.getBookAuthor().setText(item.getDynasty() + "·" + item.getAuthor());
        helper.getBookName().setText(item.getBookName());
        TextView bookGrade = helper.getBookGrade();
        if (grade == 0) {
            bookGrade.setVisibility(View.VISIBLE);
            bookGrade.setText(DataTransUtils.getGradeStr(item.getGrade()));
        } else {
            bookGrade.setVisibility(View.GONE);
        }
        helper.getTouchView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnItemChildClickListener onItemChildClickListener = getOnItemChildClickListener();
                if (onItemChildClickListener != null)
                    onItemChildClickListener.onItemChildClick(BookRecyclerAdapter.this, helper.getBookRel(), helper.getLayoutPosition());
            }
        });

    }

    public class ViewHolder extends BaseViewHolder {
        private TextView mBookName, mBookAuthor, mBookGrade;
        private RelativeLayout bookRel;
        private ImageView touchView;

        public ImageView getTouchView() {
            return touchView;
        }

        public ViewHolder(View view) {
            super(view);
            bookRel = (RelativeLayout) view.findViewById(R.id.book_rel);
            ViewGroup.LayoutParams layoutParams = bookRel.getLayoutParams();
            layoutParams.width = width;
            layoutParams.height = height;
            bookRel.setLayoutParams(layoutParams);

            touchView = (ImageView) view.findViewById(R.id.touch_view);

            mBookName = (TextView) view.findViewById(R.id.book_name);
            mBookAuthor = (TextView) view.findViewById(R.id.book_author);
            mBookGrade = (TextView) view.findViewById(R.id.book_grade);
        }

        public TextView getBookName() {
            return mBookName;
        }

        public TextView getBookAuthor() {
            return mBookAuthor;
        }

        public TextView getBookGrade() {
            return mBookGrade;
        }

        public RelativeLayout getBookRel() {
            return bookRel;
        }
    }
}
