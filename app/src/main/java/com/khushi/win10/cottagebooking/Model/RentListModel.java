package com.khushi.win10.cottagebooking.Model;

import java.util.List;

/**
 * Created by Win10 on 13/09/2018.
 */

public class RentListModel{


    /**
     * success : 1
     * message : List Found
     * data : [{"id":"1","name":"Rethal Greens","place":"Ahmedabad","images":"/cottage/assets/img1.jpeg,/cottage/assets/rethal2.jpg,/cottage/assets/img1.jpeg,/cottage/assets/rethal2.jpg,/cottage/assets/img1.jpeg,/cottage/assets/rethal2.jpg,/cottage/assets/img1.jpeg,/cottage/assets/rethal2.jpg","available":"0","price":"3500","amenities":"Pool, Wifi, Outdoor Games, Indoor Games","contact":"1233465567","ratings":"5","blocked":"0","category_id":"2","start_date":"2018-01-01","end_date":"2018-01-05","category":"5 Star","reviews":[{"review":"test is test review for rethel greens","ratings":"4","date":"01 Sep, 2018"},{"review":"this is review for old rethel greens","ratings":"3","date":"01 Mar, 2018"},{"review":"this is review for old rethel greens","ratings":"4.5","date":"01 May, 2016"},{"review":"test review","ratings":"2","date":"26 Oct, 2018"}]},{"id":"2","name":"Harmonic Holidays","place":"Baroda","images":"/cottage/assets/img1.jpeg,/cottage/assets/rethal2.jpg","available":"1","price":"3000","amenities":"Pool, Wifi","contact":"987654321","ratings":"5","blocked":"0","category_id":"1","start_date":null,"end_date":null,"category":"3 Star","reviews":[{"review":"this is review for harmonic holidays","ratings":"4.5","date":"01 Sep, 2018"},{"review":"this is review for harmonic holidays","ratings":"3","date":"01 Sep, 2018"}]},{"id":"12","name":"test","place":"test","images":"/cottage/assets/6823b72e90bc8813c659c5023d13d002.jpg","available":"1","price":"123","amenities":"test","contact":"1231232","ratings":"5","blocked":"0","category_id":"1","start_date":null,"end_date":null,"category":"3 Star","reviews":[]},{"id":"13","name":"test","place":"test","images":"/cottage/assets/eda222fc4a98292188db2d62fd3d923e.jpg","available":"1","price":"123123","amenities":"testt","contact":"13123","ratings":"5","blocked":"0","category_id":"1","start_date":null,"end_date":null,"category":"3 Star","reviews":[]},{"id":"14","name":"etste","place":"setse","images":"/cottage/assets/3c64f491a3a81937332198aac057430e.jpg,/cottage/assets/002afe1affd2151dd69985d9c744a513.jpg","available":"1","price":"1123123","amenities":"dsdfdfg","contact":"123123","ratings":"5","blocked":"0","category_id":"1","start_date":null,"end_date":null,"category":"3 Star","reviews":[]},{"id":"15","name":"ssdf","place":"sdf","images":"/cottage/assets/6823b72e90bc8813c659c5023d13d002.jpg,/cottage/assets/33b1f09b6603bdc074cac54c55c2e53c.jpg,/cottage/assets/3c64f491a3a81937332198aac057430e.jpg,/cottage/assets/002afe1affd2151dd69985d9c744a513.jpg","available":"1","price":"123","amenities":"sadsf","contact":"1233","ratings":"5","blocked":"0","category_id":"1","start_date":null,"end_date":null,"category":"3 Star","reviews":[]},{"id":"16","name":"sadfas","place":"asdfasdf","images":"/cottage/assets/3c64f491a3a81937332198aac057430e.jpg","available":"1","price":"123123","amenities":"sdfasdf","contact":"123123","ratings":"5","blocked":"0","category_id":"2","start_date":null,"end_date":null,"category":"5 Star","reviews":[]},{"id":"17","name":"asdfasdf","place":"asdfasdf","images":"/cottage/assets/6823b72e90bc8813c659c5023d13d002.jpg","available":"1","price":"0","amenities":"asdfasdfd","contact":"0","ratings":"5","blocked":"0","category_id":"1","start_date":null,"end_date":null,"category":"3 Star","reviews":[]},{"id":"18","name":"hjg","place":"dgdfg","images":"/cottage/assets/6823b72e90bc8813c659c5023d13d002.jpg","available":"1","price":"1231223","amenities":"dag","contact":"1231232","ratings":"5","blocked":"0","category_id":"1","start_date":null,"end_date":null,"category":"3 Star","reviews":[]},{"id":"19","name":"asddf","place":"asdf","images":"/cottage/assets/6823b72e90bc8813c659c5023d13d002.jpg","available":"1","price":"123123","amenities":"fdgsdfg","contact":"123123","ratings":"5","blocked":"0","category_id":"1","start_date":null,"end_date":null,"category":"3 Star","reviews":[]},{"id":"20","name":"dfasdff","place":"asdfdasdfd","images":"/cottage/assets/6823b72e90bc8813c659c5023d13d002.jpg","available":"1","price":"123123","amenities":"asdfasdfdasddf","contact":"21233123","ratings":"5","blocked":"0","category_id":"2","start_date":null,"end_date":null,"category":"5 Star","reviews":[]},{"id":"21","name":"sddfasdfd","place":"asdfdsadf","images":"/cottage/assets/eda222fc4a98292188db2d62fd3d923e.jpg","available":"1","price":"1231231232","amenities":"asfasddf","contact":"1231232","ratings":"5","blocked":"0","category_id":"1","start_date":null,"end_date":null,"category":"3 Star","reviews":[]}]
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
         * name : Rethal Greens
         * place : Ahmedabad
         * images : /cottage/assets/img1.jpeg,/cottage/assets/rethal2.jpg,/cottage/assets/img1.jpeg,/cottage/assets/rethal2.jpg,/cottage/assets/img1.jpeg,/cottage/assets/rethal2.jpg,/cottage/assets/img1.jpeg,/cottage/assets/rethal2.jpg
         * available : 0
         * price : 3500
         * amenities : Pool, Wifi, Outdoor Games, Indoor Games
         * contact : 1233465567
         * ratings : 5
         * blocked : 0
         * category_id : 2
         * start_date : 2018-01-01
         * end_date : 2018-01-05
         * category : 5 Star
         * reviews : [{"review":"test is test review for rethel greens","ratings":"4","date":"01 Sep, 2018"},{"review":"this is review for old rethel greens","ratings":"3","date":"01 Mar, 2018"},{"review":"this is review for old rethel greens","ratings":"4.5","date":"01 May, 2016"},{"review":"test review","ratings":"2","date":"26 Oct, 2018"}]
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
        private String blocked;
        private String category_id;
        private String start_date;
        private String end_date;
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

        public String getBlocked() {
            return blocked;
        }

        public void setBlocked(String blocked) {
            this.blocked = blocked;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
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
             * review : test is test review for rethel greens
             * ratings : 4
             * date : 01 Sep, 2018
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
