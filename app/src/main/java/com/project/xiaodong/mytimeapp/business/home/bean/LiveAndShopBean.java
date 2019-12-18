package com.project.xiaodong.mytimeapp.business.home.bean;

import com.project.xiaodong.mytimeapp.frame.bean.TimeBaseEntity;

import java.util.List;

/**
 * Created by xiaodong.jin on 2017/10/19.
 */

public class LiveAndShopBean extends TimeBaseEntity<LiveAndShopBean> {

    public MallDivBean mallDiv;
    public List<LiveListBean> liveList;

    public static class MallDivBean {


        public String buttonText;
        public GotoPageBean gotoPage;
        public String link;
        public boolean showStatus;
        public List<DivListBean> divList;

        public String getButtonText() {
            return buttonText;
        }

        public void setButtonText(String buttonText) {
            this.buttonText = buttonText;
        }

        public GotoPageBean getGotoPage() {
            return gotoPage;
        }

        public void setGotoPage(GotoPageBean gotoPage) {
            this.gotoPage = gotoPage;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public boolean isShowStatus() {
            return showStatus;
        }

        public void setShowStatus(boolean showStatus) {
            this.showStatus = showStatus;
        }

        public List<DivListBean> getDivList() {
            return divList;
        }

        public void setDivList(List<DivListBean> divList) {
            this.divList = divList;
        }


        public static class DivListBean {


            public String areaName;
            public GotoPageBean gotoPage;
            public String imgSrc;
            public String url;


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
                /**
                 * keyword : 0
                 */

                public int keyword;

                public int getKeyword() {
                    return keyword;
                }

                public void setKeyword(int keyword) {
                    this.keyword = keyword;
                }
            }

            public static class Parameters1Bean {


                public String keyword;

                public String getKeyword() {
                    return keyword;
                }

                public void setKeyword(String keyword) {
                    this.keyword = keyword;
                }
            }
        }
    }

    public static class LiveListBean {
        public String img;
        public int liveId;
        public String playNumTag;
        public String playTag;
        public int status;
        public String title;
        public String url;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getLiveId() {
            return liveId;
        }

        public void setLiveId(int liveId) {
            this.liveId = liveId;
        }

        public String getPlayNumTag() {
            return playNumTag;
        }

        public void setPlayNumTag(String playNumTag) {
            this.playNumTag = playNumTag;
        }

        public String getPlayTag() {
            return playTag;
        }

        public void setPlayTag(String playTag) {
            this.playTag = playTag;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
