package com.xueduoduo.reader.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by water_fairy on 2017/8/9.
 * 995637517@qq.com
 * 代替网络数据
 */
@Entity
public class BookDB implements Serializable {
    private final static long serialVersionUID = 201708091841l;

    public BookDB(String bookName, String introduce, String dynasty, String author, int grade) {
        this.bookName = bookName;
        this.dynasty = dynasty;
        this.introduce = introduce;
        this.author = author;
        this.grade = grade;
    }

    @Id(autoincrement = true)
    private Long id;
    private long bookId;//书本id
    private String bookName;//书名
    private String dynasty;//年代
    private String introduce;//简介
    private String author;//作者

    private int grade;//年级

    private int totalPage;//书本页数
    private String configInfo;//书本配置信息
    private String extraData;

    @Generated(hash = 1561443417)
    public BookDB(Long id, long bookId, String bookName, String dynasty, String introduce,
            String author, int grade, int totalPage, String configInfo, String extraData) {
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
    }

    @Generated(hash = 1163213190)
    public BookDB() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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

    public String getIntroduce() {
        return this.introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getDynasty() {
        return this.dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
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

    public long getBookId() {
        return this.bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }
}
