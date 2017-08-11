package com.xueduoduo.reader.utils;

import com.xueduoduo.reader.bean.BookConfigBean;
import com.xueduoduo.reader.database.BookDB;
import com.xueduoduo.reader.database.BookShelfDB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by water_fairy on 2017/8/9.
 * 995637517@qq.com
 */

public class DataTransUtils {
    public static String getGradeStr(int grade) {
        String gradeStr = "";
        switch (grade) {
            case 0:
                return "";
            case 1:
                gradeStr = "一";
                break;
            case 2:
                gradeStr = "二";
                break;
            case 3:
                gradeStr = "三";
                break;
            case 4:
                gradeStr = "四";
                break;
            case 5:
                gradeStr = "五";
                break;
            case 6:
                gradeStr = "六";
                break;
        }
        return gradeStr + "年级";
    }

    public static List getChapterStr(List<BookConfigBean.Chapter> chapterList) {
        List<String> list = new ArrayList<>();
        for (BookConfigBean.Chapter chapter : chapterList) {
            list.add(chapter.getChapterName());
        }
        return list;
    }

    public static BookShelfDB getBookShelfDBFromBookDB(BookDB bookDB) {
        BookShelfDB bookShelfDB = new BookShelfDB();
        bookShelfDB.setAuthor(bookDB.getAuthor());
        bookShelfDB.setBookId(bookDB.getBookId());
        bookShelfDB.setBookName(bookDB.getBookName());
        bookShelfDB.setConfigInfo(bookDB.getConfigInfo());
        bookShelfDB.setDynasty(bookDB.getDynasty());
        bookShelfDB.setGrade(bookDB.getGrade());
        bookShelfDB.setExtraData(bookDB.getExtraData());
        bookShelfDB.setIntroduce(bookDB.getIntroduce());
        bookShelfDB.setTotalPage(bookDB.getTotalPage());
        return bookShelfDB;
    }

    public static BookDB getBookDBFromBookShelfDB(BookShelfDB bookShelfDB) {
        BookDB bookDB = new BookDB();
        bookDB.setAuthor(bookShelfDB.getAuthor());
        bookDB.setBookId(bookShelfDB.getBookId());
        bookDB.setBookName(bookShelfDB.getBookName());
        bookDB.setConfigInfo(bookShelfDB.getConfigInfo());
        bookDB.setDynasty(bookShelfDB.getDynasty());
        bookDB.setGrade(bookShelfDB.getGrade());
        bookDB.setExtraData(bookShelfDB.getExtraData());
        bookDB.setIntroduce(bookShelfDB.getIntroduce());
        bookDB.setTotalPage(bookShelfDB.getTotalPage());
        return bookDB;
    }

    public static List<BookDB> getBookDBListFromBookShelfDB(List<BookShelfDB> bookShelfDBs) {
        List<BookDB> bookDBList = new ArrayList<>();
        if (bookShelfDBs != null && bookShelfDBs.size() > 0) {
            for (int i = 0; i < bookShelfDBs.size(); i++) {
                bookDBList.add(getBookDBFromBookShelfDB(bookShelfDBs.get(i)));
            }
        }
        return bookDBList;
    }
}
