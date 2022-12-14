package com.example.onlineshopping.database.models;

import android.icu.lang.UProperty;

import java.security.PrivateKey;

public class Customer {

    private int customerId ;
    private String Email ;
    private  String userName ;
    private String password ;
    private String gender ;
    private String job ;
    private String birthdate ;
    private String phoneNumber ;
    //1 admin - 0 customer
    private int isAdmin ;

    public Customer(int customerId,String Email, String userName, String password, String gender, String job, String birthdate,int isAdmin,String phoneNumber) {
        this.customerId=customerId;
        this.Email = Email;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.job = job;
        this.birthdate = birthdate;
        this.isAdmin =isAdmin;
        this.phoneNumber=phoneNumber;
    }



    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return Email;
    }

    public void setCustomerName(String Email) {
        this.Email = Email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
