package com.xueduoduo.reader.read;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.waterfairy.media.Mp3Player;
import com.waterfairy.utils.ToastUtils;
import com.xueduoduo.application.MyApp;
import com.xueduoduo.reader.R;
import com.xueduoduo.reader.bean.BookConfigBean;
import com.xueduoduo.reader.database.BookDB;

import java.util.HashMap;
import java.util.List;


public class ReadActivity extends AppCompatActivity implements
        View.OnTouchListener,
        ViewPager.OnPageChangeListener, AdapterView.OnItemClickListener, View.OnClickListener {
    private RelativeLayout mProgressBar;
    private float density;
    private int mProgressMaxLen, mProgressCanChangeMaxLen;
    private int mProgressRadio;
    private RelativeLayout mRLCurrentProgress;
    private ViewPager mViewPager;
    private BookConfigBean bookConfigBean;
    private List<BookConfigBean.PageInfo> pageInfoList;
    private List<BookConfigBean.Chapter> chapterList;
    private RelativeLayout mRLHandle;
    private TextView mTVPageNum;
    private int totalPage;
    private boolean isOnTouch;
    private ListView mListView;
    private DrawerLayout drawerLayout;
    private int currentChapter;//
    private BaseAdapter chapterAdapter;
    private Mp3Player mp3Player;
    private ImageView mIVSound;
    private TextView mTVBookName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        initView();
        initData();
    }

    private void initData() {
        mp3Player = new Mp3Player();
        String configInfo = ((BookDB) getIntent().getSerializableExtra("book")).getConfigInfo();
        bookConfigBean = new Gson().fromJson(configInfo, BookConfigBean.class);
        pageInfoList = bookConfigBean.getPageInfoList();
        chapterList = bookConfigBean.getChapterList();
        totalPage = pageInfoList.size();
        setViewPager();
        setDrawListView();
        setPageNum(1, true);
    }

    private void setDrawListView() {
        if (chapterList != null && chapterList.size() > 0) {
            mListView.setAdapter(chapterAdapter = new BaseAdapter() {
                @Override
                public int getCount() {
                    return chapterList.size();
                }

                @Override
                public Object getItem(int position) {
                    return null;
                }

                @Override
                public long getItemId(int position) {
                    return 0;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    convertView = LayoutInflater.from(ReadActivity.this).inflate(R.layout.item_draw_list, parent, false);
                    TextView textView = (TextView) convertView.findViewById(R.id.text);
                    textView.setText(chapterList.get(position).getChapterName());
                    if (position == currentChapter) {
                        textView.setTextColor(getResources().getColor(R.color.colorAccentLogin));
                    } else {
                        textView.setTextColor(getResources().getColor(R.color.color_text_read));
                    }

                    return convertView;
                }
            });
            mListView.setOnItemClickListener(this);
        }
    }

    private void initView() {
        mTVBookName = (TextView) findViewById(R.id.book_name);
        String bookName = getIntent().getStringExtra("bookName");
        mTVBookName.setText(bookName);
        mIVSound = (ImageView) findViewById(R.id.audio);
        mIVSound.setTag(true);
        mIVSound.setOnClickListener(this);
        mListView = (ListView) findViewById(R.id.list_view);
        mTVPageNum = (TextView) findViewById(R.id.page_num);
        mRLHandle = (RelativeLayout) findViewById(R.id.handle_rel);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        DisplayMetrics displayMetrics = MyApp.getInstance().getResources().getDisplayMetrics();
        density = displayMetrics.density;
        mProgressRadio = (int) (12 * density);
        mProgressMaxLen = (int) (displayMetrics.widthPixels - (displayMetrics.density * 121));
        mProgressCanChangeMaxLen = mProgressMaxLen - mProgressRadio;
        mProgressBar = (RelativeLayout) findViewById(R.id.progress_bar);
        mProgressBar.setOnTouchListener(this);
        mRLCurrentProgress = (RelativeLayout) findViewById(R.id.current_progress);
        mViewPager.addOnPageChangeListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        setDrawLayout();
    }

    private void setDrawLayout() {
        drawerLayout.setDrawerListener(new ActionBarDrawerToggle(this, drawerLayout, R.color.color_bg_main, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                int currentItem = mViewPager.getCurrentItem() + 1;
                currentChapter = chapterList.get(chapterList.size() - 1).getPage() - 1;
                for (int i = 0; i < chapterList.size(); i++) {
                    BookConfigBean.Chapter chapter = chapterList.get(i);
                    int page = i + 1;
                    if (page != chapterList.size()) {
                        if (currentItem >= chapter.getPage() && currentItem < chapterList.get(page).getPage()) {
                            int nextPage = chapterList.get(page).getPage();
                            if (currentItem < nextPage) {
                                currentChapter = i;
                                break;
                            } else if (currentItem == nextPage) {
                                currentChapter = nextPage - 1;
                                break;
                            }
                        }
                    }
                }
                chapterAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setViewPager() {
        final HashMap<Integer, RelativeLayout> hashMap = new HashMap<>();
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return pageInfoList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                ((ViewPager) container).removeView(hashMap.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(ReadActivity.this).inflate(R.layout.item_layout_read, container, false);
                ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.img);
                imageView.setImageBitmap(BitmapFactory.decodeFile(pageInfoList.get(position).getImgUrl()));
                hashMap.put(position, relativeLayout);
                container.addView(relativeLayout);
                relativeLayout.findViewById(R.id.touch_lin).setOnClickListener(new OnViewPagerClickListener());
                return relativeLayout;
            }
        });
    }

    public void close(View view) {
        finish();
    }

    public void openMenu(View view) {

    }

    public void openAudio(View view) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == R.id.progress_bar) {
            return onTouchProgressBar(event);
        } else if (v.getId() == R.id.touch_lin) {
            return onTouchContent(event);
        }
        return false;

    }

    private boolean isFirst;

    private boolean onTouchContent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            return true;
        } else {
            if (isFirst) {
                return false;
            } else {
                isFirst = true;
//                event.setAction(MotionEvent.ACTION_DOWN);
                return mViewPager.dispatchTouchEvent(event);
            }


        }
    }

    private boolean onTouchProgressBar(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            isOnTouch = true;
            float len = event.getX();
            float currentLen = len - mProgressRadio;
            if (currentLen < 0) currentLen = 0;
            else if (currentLen > mProgressCanChangeMaxLen) currentLen = mProgressCanChangeMaxLen;
            float radio = currentLen / mProgressCanChangeMaxLen;
            int page = (int) (radio * totalPage);
            mViewPager.setCurrentItem(page);
            setPageNum(page, false);
            setProgress(radio);
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            isOnTouch = false;
        }
        return false;
    }

    private void setProgress(float radio) {
        int len = (int) (mProgressCanChangeMaxLen * radio);
        ViewGroup.LayoutParams layoutParams = mRLCurrentProgress.getLayoutParams();
        layoutParams.width = len + 2 * mProgressRadio;
        mRLCurrentProgress.setLayoutParams(layoutParams);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (!isOnTouch) {
            setPageNum(position + 1, true);
        }
    }

    /**
     * @param pos
     * @param setProgress
     */
    private void setPageNum(int pos, boolean setProgress) {
        mTVPageNum.setText(pos + "/" + totalPage);
        setProgress((pos) / (float) pageInfoList.size());
        playAudio(pos);
    }

    private void playAudio(int pos) {
        if ((Boolean) mIVSound.getTag()) {
            if (mp3Player != null) {
                mp3Player.release();
                String baseSave = getExternalCacheDir().getAbsolutePath() + "/book";
                String audioPath = baseSave + "/book_" + bookConfigBean.getBookId() + "/book_" + bookConfigBean.getBookId() + "_" + pos + ".mp3";
                mp3Player.play(audioPath);
            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void upChapter(View view) {
        int page = mViewPager.getCurrentItem() - 1;
        if (page < 0) {
            ToastUtils.show("已经是第一页了");
        } else {
            mViewPager.setCurrentItem(page);
        }
    }

    public void downChapter(View view) {
        int page = mViewPager.getCurrentItem() + 1;
        if (page >= pageInfoList.size()) {
            ToastUtils.show("已经是最后一页了");
        } else {
            mViewPager.setCurrentItem(page);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BookConfigBean.Chapter chapter = chapterList.get(position);
        mViewPager.setCurrentItem(chapter.getPage() - 1);
        drawerLayout.closeDrawers();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.audio) {
            ImageView sound = (ImageView) v;
            boolean playSound = (boolean) sound.getTag();
            sound.setTag(!(boolean) sound.getTag());
            if (playSound) {
                mp3Player.release();
                sound.setImageResource(R.mipmap.no_sound);
            } else {
                mp3Player.play(bookConfigBean.getPageInfoList().get(mViewPager.getCurrentItem()).getAudioUrl());
                sound.setImageResource(R.mipmap.sound);
            }
        }
    }


    private class OnViewPagerClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int visibility = mRLHandle.getVisibility();
            if (visibility == View.VISIBLE) {
                setAnim(1, 0);
                mRLHandle.setVisibility(View.GONE);
            } else {
                setAnim(0, 1);
                mRLHandle.setVisibility(View.VISIBLE);
            }
        }
    }

    private void setAnim(float startAlpha, float endAlpha) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(startAlpha, endAlpha);
        alphaAnimation.setDuration(300);
        mRLHandle.startAnimation(alphaAnimation);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp3Player.release();
    }
}
