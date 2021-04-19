package com.ashunevich.mvp_android_example;

public class User {

    private String fullName = "";


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    private String telephone = "";



    @Override
    public String toString() {
        return "Email : " + telephone + "\nFullName : " + fullName;
    }

}
