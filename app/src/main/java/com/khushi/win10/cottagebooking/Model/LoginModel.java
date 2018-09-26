package com.khushi.win10.cottagebooking.Model;

/**
 * Created by SONY on 17-03-2017.
 */

public class LoginModel {


    /**
     * success : 1
     * message : Authentication successful
     * data : {"id":"1","firstname":"email","lastname":"email","contact":"123456789","email":"email@email.com"}
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
         * firstname : email
         * lastname : email
         * contact : 123456789
         * email : email@email.com
         */

        private String id;
        private String firstname;
        private String lastname;
        private String contact;
        private String email;

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
    }
}
