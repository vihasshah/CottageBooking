package com.khushi.win10.cottagebooking.Model;

/**
 * Created by SONY on 18-03-2017.
 */

public class UpdateProfileModel {



    private int status;
    private UserUpdatedDetailsBean user_updated_details;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserUpdatedDetailsBean getUser_updated_details() {
        return user_updated_details;
    }

    public void setUser_updated_details(UserUpdatedDetailsBean user_updated_details) {
        this.user_updated_details = user_updated_details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class UserUpdatedDetailsBean {

        private int r_id;
        private String f_name;
        private String l_name;
        private String address;
        private String ph_no;
        private String email;

        public int getR_id() {
            return r_id;
        }

        public void setR_id(int r_id) {
            this.r_id = r_id;
        }

        public String getF_name() {
            return f_name;
        }

        public void setF_name(String f_name) {
            this.f_name = f_name;
        }

        public String getL_name() {
            return l_name;
        }

        public void setL_name(String l_name) {
            this.l_name = l_name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPh_no() {
            return ph_no;
        }

        public void setPh_no(String ph_no) {
            this.ph_no = ph_no;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
