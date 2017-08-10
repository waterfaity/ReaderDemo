package com.xueduoduo.reader.utils;

import com.xueduoduo.reader.bean.BookConfigBean;

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
}
