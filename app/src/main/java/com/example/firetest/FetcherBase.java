package com.example.firetest;

public class FetcherBase {

    String email,phone,text;

    public FetcherBase(){

    }
    public FetcherBase(String email,String phone , String text){
        this.email = email;
        this.phone = phone;
        this.text = text;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
