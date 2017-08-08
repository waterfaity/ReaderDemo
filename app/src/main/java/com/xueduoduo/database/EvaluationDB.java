package com.xueduoduo.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by water_fairy on 2017/7/19.
 * 995637517@qq.com
 */

@Entity
public class EvaluationDB {

    public static int SUBJECT_ENGLISH = 1;
    public static int SUBJECT_CHINESE = 2;
    public static int SUBJECT_MATH = 3;

    public static final int TYPE_LISTEN = 1;
    public static final int TYPE_READ_ALOUD = 2;
    public static final int TYPE_READ = 3;
    public static final int TYPE_SPEAK = 4;

    @Id(autoincrement = true)
    private Long id;
    private long teacherId;
    private long studentId;
    private String subject;
    private long time;
    private int listenScore;
    private int readAloudScore;
    private int readScore;
    private int speakScore;
    @Generated(hash = 878508233)
    public EvaluationDB(Long id, long teacherId, long studentId, String subject,
            long time, int listenScore, int readAloudScore, int readScore,
            int speakScore) {
        this.id = id;
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.subject = subject;
        this.time = time;
        this.listenScore = listenScore;
        this.readAloudScore = readAloudScore;
        this.readScore = readScore;
        this.speakScore = speakScore;
    }
    @Generated(hash = 2012657269)
    public EvaluationDB() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getTeacherId() {
        return this.teacherId;
    }
    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }
    public long getStudentId() {
        return this.studentId;
    }
    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }
    public String getSubject() {
        return this.subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public int getListenScore() {
        return this.listenScore;
    }
    public void setListenScore(int listenScore) {
        this.listenScore = listenScore;
    }
    public int getReadAloudScore() {
        return this.readAloudScore;
    }
    public void setReadAloudScore(int readAloudScore) {
        this.readAloudScore = readAloudScore;
    }
    public int getReadScore() {
        return this.readScore;
    }
    public void setReadScore(int readScore) {
        this.readScore = readScore;
    }
    public int getSpeakScore() {
        return this.speakScore;
    }
    public void setSpeakScore(int speakScore) {
        this.speakScore = speakScore;
    }
    public String getResult() {
        float radio = (listenScore + readScore + speakScore + readAloudScore) / 20f;
//         100~90优90~80良80~70中70~60及格小于60不及格
        if (radio < 0.5) {
            return "差";
        } else if (radio < .6) {
            return "及格";
        } else if (radio < .7) {
            return "中";
        } else if (radio < .8) {
            return "良";
        } else if (radio < 1) {
            return "优";
        }
        return "优";
    }

}
