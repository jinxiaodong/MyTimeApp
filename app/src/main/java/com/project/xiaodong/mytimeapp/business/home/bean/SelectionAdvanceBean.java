package com.project.xiaodong.mytimeapp.business.home.bean;

import java.util.List;

/**
 * Created by xiaodong.jin on 2017/10/19.
 */

public class SelectionAdvanceBean {

    public String code;
    public String msg;
    public String showMsg;
    public AdvanceBean data;

    public static class AdvanceBean {
        public int count;
        public List<DataBean> data;


        public static class DataBean {

            public int imageCount;
            public String publishTime;
            public String recommendID;
            public int dataType;
            public int comSpecialObjId;
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
            public String publicHeadImage;
            public String memo;
            public String pic1Url;
            public String hasShow;
            public String url;
            public RelatedObjBean relatedObj;
            public String userImage;
            public String summaryInfo;
            public String nickname;
            public String rating;
            public String rId;

            public static class RelatedObjBean {
                public String image;
                public String titleEn;
                public int year;
                public int releaseType;
                public String titleCn;
                public String name;
                public int rating;
                public String runtime;
                public String title;
                public String releaseLocation;
                public int relatedId;
                public List<String> type;


            }
        }

    }

}
