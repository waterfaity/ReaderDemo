package com.xueduoduo.reader.welcome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xueduoduo.application.MyApp;
import com.xueduoduo.reader.database.BookDB;
import com.xueduoduo.reader.database.BookDBDao;
import com.xueduoduo.reader.database.DaoSession;
import com.xueduoduo.reader.database.EvaluationDBDao;
import com.xueduoduo.reader.R;
import com.xueduoduo.reader.login.LoginActivity;
import com.xueduoduo.reader.main.MainActivity;
import com.xueduoduo.reader.utils.ShareUtil;

import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initBookData();
        if (ShareUtil.getUserBean().isLogin) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }

    private void initBookData() {
        DaoSession daoSession = MyApp.getInstance().getDaoSession();
        BookDBDao bookDBDao = daoSession.getBookDBDao();
        List<BookDB> bookDBs = bookDBDao.queryBuilder().where(BookDBDao.Properties.BookName.eq("弟子规")).list();
        if (bookDBs != null && bookDBs.size() != 0) return;
        BookDB bookDB = new BookDB("弟子规", "　　《弟子规》原名《训蒙文》，为清朝康熙年间秀才李毓秀所作。\n\n　　" +
                "一般的人听到“弟子”，会有错误的认知，觉得是小孩学的。其实这个“弟子”不是指小孩，圣贤人的学生都叫弟子。其主要内容也是《论语》学而篇的解读，所以，《弟子规》是源于《论语》，是随顺圣贤教诲，也就是人生的真理，来做事、来处事待人，并不局限于幼儿教育，也适用于成年人。\n\n　　" +
                "其内容采用《论语》“学而篇”第六条：“弟子入则孝，出则悌，谨而信，泛爱众而亲仁。行有余力，则以学文”的文义，以三字一句、两句一韵编撰而成。全文共360句、1080个字，分为七个部分：即孝、悌、谨、信、爱众、亲仁、学文，前六项属于德育修养，后一项属于智育修养，列述弟子在家、出外、待人、接物与学习上应该恪守的守则规范，特别讲求家庭教育与生活教育的践行，是童蒙养正、敦伦尽分，闲邪存诚，养成忠厚家风规范和对照自我的经典。\n\n　　" +
                "《训蒙文》在成书之初受到清朝政府大力推广而广泛流传，后经清朝贾存仁修订改编，并改名为《弟子规》。\n\n　　" +
                "《弟子规》在大陆的流行，归功于净空法师。读《弟子规》不是为了比别人，就是为了自我提升和宁静。1、弟子规告诉你人生获得幸福的基本修为是什么。2、通过反复读诵弟子规，可以进行自我反省。3、通过反复读诵弟子规，让自己静心。", "清", "李毓秀", 1);
        BookDB bookDB2 = new BookDB("三字经 ", "　　《三字经》，是中国的传统启蒙教材。在中国古代经典当中，《三字经》是最浅显易懂的读本之一。《三字经》取材典范，包括中国传统文化的文学、历史、哲学、天文地理、人伦义理、忠孝节义等等，而核心思想又包括了“仁，义，诚，敬，孝。”背诵《三字经》的同时，就了解了常识、传统国学及历史故事，以及故事内涵中的做人做事道理。\n\n　　" +
                "在格式上，三字一句朗朗上口，因其文通俗、顺口、易记等特点，使其与《百家姓》、《千字文》并称为中国传统蒙学三大读物，合称“三百千”。[1]  《三字经》与《百家姓》、《千字文》并称为三大国学启蒙读物。《三字经》是中华民族珍贵的文化遗产，它短小精悍、琅琅上口，千百年来，家喻户晓。其内容涵盖了历史、天文、地理、道德以及一些民间传说，所谓“熟读《三字经》，可知千古事”。基于历史原因，《三字经》难免含有一些精神糟粕、艺术瑕疵，但其独特的思想价值和文化魅力仍然为世人所公认，被历代中国人奉为经典并不断流传",
                "宋", "王应麟", 1);
        BookDB bookDB3 = new BookDB("论语 ",
                "　　《论语》由孔子弟子及再传弟子编写而成，至汉代成书。主要记录孔子及其弟子的言行，较为集中地反映了孔子的思想，是儒家学派的经典著作之一。以语录体为主，叙事体为辅，集中体现了孔子的政治主张、伦理思想、道德观念及教育原则等。与《大学》、《中庸》、《孟子》并称“四书”，与《诗》、《书》、《礼》、《易》、《春秋》等“五经”，总称“四书五经”。全书共20篇、492章，首创 “语录体” 。是中国现传扬并学习的古代著作之一。",
                "东周春秋", "孔子", 2);
        bookDBDao.save(bookDB);
        bookDBDao.save(bookDB2);
        bookDBDao.save(bookDB3);
    }
}
