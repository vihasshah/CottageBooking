package com.khushi.win10.cottagebooking.Model;

import java.util.List;

/**
 * Created by Win10 on 13/09/2018.
 */

public class RentListModel {

    /**
     * success : 1
     * message : List Found
     * data : [{"id":"2","name":"Harmonic Holidays","place":"Baroda","images":"https://im.proptiger.com/1/502090/80/safal-builder-rethal-greens-swimming-pool-760667.jpeg?width=800&height=620, https://media.gobymobile.com/mediaresources/venue/2012/gallery/large/753fb8a1-bfd5-44e5-87ca-673847145e32.jpg","available":"1","price":"3000","amenities":"Pool, Wifi","contact":"987654321","ratings":"5","category_id":"1","category":"3 Star","reviews":[{"review":"this is review for harmonic holidays","ratings":"4.5","date":"01-Sep-2018"},{"review":"this is review for harmonic holidays","ratings":"3","date":"01-Sep-2018"}]},{"id":"1","name":"Rethal Greens","place":"AHMEdabad","images":"https://im.proptiger.com/1/502090/80/safal-builder-rethal-greens-swimming-pool-760667.jpeg?width=800&height=620, https://media.gobymobile.com/mediaresources/venue/2012/gallery/large/753fb8a1-bfd5-44e5-87ca-673847145e32.jpg","available":"1","price":"3500","amenities":"Pool, Wifi, Outdoor Games, Indoor Games","contact":"1233465567","ratings":"5","category_id":"2","category":"5 Star","reviews":[{"review":"test is test review for rethel greens","ratings":"4","date":"01-Sep-2018"},{"review":"this is review for old rethel greens","ratings":"3","date":"01-Mar-2018"},{"review":"this is review for old rethel greens","ratings":"4.5","date":"01-May-2016"},{"review":"test review","ratings":"2","date":"26-Oct-2018"}]}]
     */

    private int success;
    private String message;
    private List<DataBean> data = null;

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
         * id : 2
         * name : Harmonic Holidays
         * place : Baroda
         * images : https://im.proptiger.com/1/502090/80/safal-builder-rethal-greens-swimming-pool-760667.jpeg?width=800&height=620, https://media.gobymobile.com/mediaresources/venue/2012/gallery/large/753fb8a1-bfd5-44e5-87ca-673847145e32.jpg
         * available : 1
         * price : 3000
         * amenities : Pool, Wifi
         * contact : 987654321
         * ratings : 5
         * category_id : 1
         * category : 3 Star
         * reviews : [{"review":"this is review for harmonic holidays","ratings":"4.5","date":"01-Sep-2018"},{"review":"this is review for harmonic holidays","ratings":"3","date":"01-Sep-2018"}]
         */

        private String id;
        private String name;
        private String place;
        private String images;
        private String available;
        private String price;
        private String amenities;
        private String contact;
        private String ratings;
        private String category_id;
        private String category;
        private List<ReviewsBean> reviews;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getAvailable() {
            return available;
        }

        public void setAvailable(String available) {
            this.available = available;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getAmenities() {
            return amenities;
        }

        public void setAmenities(String amenities) {
            this.amenities = amenities;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getRatings() {
            return ratings;
        }

        public void setRatings(String ratings) {
            this.ratings = ratings;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public List<ReviewsBean> getReviews() {
            return reviews;
        }

        public void setReviews(List<ReviewsBean> reviews) {
            this.reviews = reviews;
        }

        public static class ReviewsBean {
            /**
             * review : this is review for harmonic holidays
             * ratings : 4.5
             * date : 01-Sep-2018
             */

            private String review;
            private String ratings;
            private String date;

            public String getReview() {
                return review;
            }

            public void setReview(String review) {
                this.review = review;
            }

            public String getRatings() {
                return ratings;
            }

            public void setRatings(String ratings) {
                this.ratings = ratings;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
