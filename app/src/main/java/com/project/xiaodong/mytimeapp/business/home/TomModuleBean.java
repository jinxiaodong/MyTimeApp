package com.project.xiaodong.mytimeapp.business.home;

import com.project.xiaodong.mytimeapp.frame.network.TimeBaseEntity;

import java.util.List;

/**
 * Created by xiaodong.jin on 2017/9/27.
 */

public class TomModuleBean extends TimeBaseEntity<TomModuleBean> {

    public String searchBarDescribe;
    public List<CategoryListBean> categoryList;
    public List<GalleryListBean> galleryList;


    public static class CategoryListBean {


        public GotoPageBean gotoPage;
        public String img;
        public String name;
        public String selectColor;



    }

    public static class GalleryListBean {


        public String advTag;
        public String applinkData;
        public GotoPageBean gotoPage;
        public String img;
        public boolean isOpenByBrowser;


    }

    public static class GotoPageBean {

        public String gotoType;
        public boolean isGoH5;
        public ParametersBean parameters;
        public Parameters1Bean parameters1;
        public int relatedId;
        public String relatedTypeUrl;
        public String url;



        public static class ParametersBean {
            public String newId;
        }

        public static class Parameters1Bean {
            public String newId;
        }
    }
}
