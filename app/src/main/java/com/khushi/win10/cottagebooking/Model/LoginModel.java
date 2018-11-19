package com.khushi.win10.cottagebooking.Model;

/**
 * Created by SONY on 17-03-2017.
 */

public class LoginModel {

    /**
     * success : 1
     * message : Authentication successful
     * data : {"id":"1","firstname":"vihas","lastname":"shah","contact":"123456789","email":"vihas@vihas.com","blocked":"0"}
     */

    private int success;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * firstname : vihas
         * lastname : shah
         * contact : 123456789
         * email : vihas@vihas.com
         * blocked : 0
         */

        private String id;
        private String firstname;
        private String lastname;
        private String contact;
        private String email;
        private String blocked = "1";

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getBlocked() {
            return blocked;
        }

        public void setBlocked(String blocked) {
            this.blocked = blocked;
        }
    }
}
