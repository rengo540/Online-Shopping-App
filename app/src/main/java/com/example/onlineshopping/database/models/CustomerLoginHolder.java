package com.example.onlineshopping.database.models;

import java.util.ArrayList;

public class CustomerLoginHolder {


    private static CustomerLoginHolder instance=null ;

    Customer customer;

    private CustomerLoginHolder(){

    }

    public static CustomerLoginHolder getInstance(){
        if(instance == null){
            instance = new CustomerLoginHolder();
        }
        return instance ;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
