package com.project.xiaodong.mytimeapp.business.home.bean;

import com.project.xiaodong.mytimeapp.frame.bean.TimeBasenBean;

import java.util.List;

/**
 * Created by xiaodong.jin on 2017/11/28.
 */

public class HomeAdvanceBean extends TimeBasenBean<HomeAdvanceBean> {


    public int count;
    public List<ListBean> list;


    public static class ListBean {

        public int articleId;
        public String image;
        public String introduction;
        public String publicHeadImage;
        public int publicId;
        public String publicName;
        public String recommendID;
        public String recommendType;
        public String tag;
        public String title;
        public TopListBean topList;
        public int type;
    }

    public static class TopListBean {
    }
}
