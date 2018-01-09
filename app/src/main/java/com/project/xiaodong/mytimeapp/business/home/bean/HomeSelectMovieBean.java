package com.project.xiaodong.mytimeapp.business.home.bean;

import com.project.xiaodong.mytimeapp.frame.bean.TimeBasenBean;

import java.util.List;

/**
 * Created by xiaodong.jin on 2018/1/8.
 */

public class HomeSelectMovieBean extends TimeBasenBean<HomeSelectMovieBean> {

    public GoodMovieBean goodMovie;
    public List<CategoryBean> category;
    public List<HotTopicBean> hotTopic;


    public static class GoodMovieBean {


        public int gmId;
        public int gmType;
        public String image;
        public String title;
        public String url;


    }

    public static class CategoryBean {
        /**
         * areas : 全部地区
         * genreTypes : 全部类型
         * name : 全部
         * years : 全部年代
         */

        public  String areas;
        public  String genreTypes;
        public  String name;
        public  String years;


    }

    public static class HotTopicBean {
        /**
         * bgImage : http://img5.mtime.cn/mg/2017/06/14/112247.13704558.jpg
         * introduction :
         * movieCount : 100
         * name : 《帝国》杂志新百大佳片
         * topId : 1483
         */

       public  String bgImage;
       public  String introduction;
       public  int movieCount;
       public  String name;
       public  int topId;


    }
}
