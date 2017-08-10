package com.xueduoduo.reader.bean;

import com.google.gson.Gson;
import com.waterfairy.utils.JsonUtils;

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

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<Chapter> chapterList) {
        this.chapterList = chapterList;
    }

    public List<PageInfo> getPageInfoList() {
        return pageInfoList;
    }

    public void setPageInfoList(List<PageInfo> pageInfoList) {
        this.pageInfoList = pageInfoList;
    }

    public static class PageInfo {
        public PageInfo(int pos, String audioUrl, String imgUrl) {
            this.audioUrl = audioUrl;
            this.imgUrl = imgUrl;
            this.pos = pos;
        }

        public String getAudioUrl() {
            return audioUrl;
        }

        public void setAudioUrl(String audioUrl) {
            this.audioUrl = audioUrl;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
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

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }
    }

    public String toJson() {
        return JsonUtils.objectToJson(this);
    }
}
