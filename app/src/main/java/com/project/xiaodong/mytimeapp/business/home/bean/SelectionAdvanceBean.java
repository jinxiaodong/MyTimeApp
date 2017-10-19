package com.project.xiaodong.mytimeapp.business.home.bean;

import com.project.xiaodong.mytimeapp.frame.bean.TimeBaseEntity;

import java.util.List;

/**
 * Created by xiaodong.jin on 2017/10/19.
 */

public class SelectionAdvanceBean extends TimeBaseEntity<SelectionAdvanceBean> {


    public int count;
    public List<DataBean> data;


    public static class DataBean {

        public int imageCount;
        public String publishTime;
        public String recommendID;
        public int dataType;
        public int comSpecialObjId;
        public String publicHeadImage;
        public String title;
        public String titlesmall;
        public int type;
        public String content;
        public int commentCount;
        public String isShow;
        public String publicName;
        public String recommendType;
        public String tag;
        public String time;
        public String aId;
        public int publicId;
        public String img1;
        public int status;

    }
}
