package com.xueduoduo.reader.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by water_fairy on 2017/8/9.
 * 995637517@qq.com
 * 本地书架信息
 */
@Entity
public class BookShelfDB {
    @Id(autoincrement = true)
    private Long id;

    private long bookId;
    private String bookName;//书名
    private String dynasty;//年代
    private String introduce;//简介
    private String author;//作者
    private int grade;//年级
    private int totalPage;//书本页数
    private String configInfo;//书本配置信息
    private String extraData;

    private int readPage;//阅读到的页数

    @Generated(hash = 626470682)
    public BookShelfDB(Long id, long bookId, String bookName, String dynasty,
            String introduce, String author, int grade, int totalPage,
            String configInfo, String extraData, int readPage) {
        this.id = id;
        this.bookId = bookId;
        this.bookName = bookName;
        this.dynasty = dynasty;
        this.introduce = introduce;
        this.author = author;
        this.grade = grade;
        this.totalPage = totalPage;
        this.configInfo = configInfo;
        this.extraData = extraData;
        this.readPage = readPage;
    }

    @Generated(hash = 1311276513)
    public BookShelfDB() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getBookId() {
        return this.bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDynasty() {
        return this.dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getIntroduce() {
        return this.introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getConfigInfo() {
        return this.configInfo;
    }

    public void setConfigInfo(String configInfo) {
        this.configInfo = configInfo;
    }

    public String getExtraData() {
        return this.extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    public int getReadPage() {
        return this.readPage;
    }

    public void setReadPage(int readPage) {
        this.readPage = readPage;
    }


}
