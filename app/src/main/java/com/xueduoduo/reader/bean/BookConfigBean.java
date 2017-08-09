package com.xueduoduo.reader.bean;

import java.util.List;

/**
 * Created by water_fairy on 2017/8/9.
 * 995637517@qq.com
 * 书本配置信息 章节
 */

public class BookConfigBean {
    private long bookId;
    private List<Chapter> chapterList;
    private List<PageInfo> pageInfoList;

    public static class PageInfo {
        public PageInfo(int pos, String audioUrl, String imgUrl) {
            this.audioUrl = audioUrl;
            this.imgUrl = imgUrl;
            this.pos = pos;
        }

        private String audioUrl;
        private String imgUrl;
        private int pos;
    }

    public static class Chapter {
        private String chapterName;
        private int page;

        public Chapter(String chapterName, int page) {
            this.chapterName = chapterName;
            this.page = page;
        }
    }
}
