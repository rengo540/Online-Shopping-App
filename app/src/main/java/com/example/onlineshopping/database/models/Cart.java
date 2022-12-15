package com.example.onlineshopping.database.models;

import com.example.onlineshopping.ui.ProductDetails;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    List<Product> productList ;
List<OrderDetials> orderDetialsList;

    //singleton pattern

    private static Cart instance=null ;

    private Cart(){
        productList =new ArrayList<>();
        orderDetialsList=new ArrayList<>();
    }

    public static Cart getInstance(){
        if(instance == null){
            instance = new Cart();
        }
        return instance ;
    }

    public void addProduct(Product p ){
        productList.add(p);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<OrderDetials> getOrderDetialsList() {
        return orderDetialsList;
    }

    public void setOrderDetialsList(List<OrderDetials> orderDetialsList) {
        this.orderDetialsList = orderDetialsList;
    }
}
