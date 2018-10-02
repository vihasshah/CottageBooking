package com.khushi.win10.cottagebooking.Model;

import java.util.List;

/**
 * Created by Win10 on 14/09/2018.
 */

public class NewsModel {

    /**
     * success : 1
     * message : News Found
     * data : [{"id":"1","image":"https://im.proptiger.com/1/502090/80/safal-builder-rethal-greens-swimming-pool-760667.jpeg?width=800&height=620","title":"news title","details":"this is description part of property new.this is multiline para."}]
     */

    private int success;
    private String message;
    private List<DataBean> data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * image : https://im.proptiger.com/1/502090/80/safal-builder-rethal-greens-swimming-pool-760667.jpeg?width=800&height=620
         * title : news title
         * details : this is description part of property new.this is multiline para.
         */

        private String id;
        private String image;
        private String title;
        private String details;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }
    }
}
