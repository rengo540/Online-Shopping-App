package com.example.onlineshopping.database.models;


public class Product  {


    private int productId ;
    private String productName ;
    private int price ;
    private int stockQuantity ;
    private String barcode ;
    Category category ;
    int noOfSales ;


    public Product(int productId, String productName, int price, int stockQuantity, String barcode, Category category,int noOfSales) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.barcode = barcode;
        this.category = category;
        this.noOfSales = noOfSales ;
    }

    public Product(String productName, int price, int stockQuantity, String barcode, Category category,int noOfSales) {
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.barcode = barcode;
        this.category = category;
        this.noOfSales=noOfSales;
    }

    public Product(int productId, String productName, int price, int stockQuantity, String barcode) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.barcode = barcode;
    }
    public Product(String productName, int noOfSales) {
        this.productName = productName;
        this.noOfSales = noOfSales;
    }

    public Product(){}
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getNoOfSales() {
        return noOfSales;
    }

    public void setNoOfSales(int noOfSales) {
        this.noOfSales = noOfSales;
    }

    @Override
    public String toString() {
        return "Name: "+productName+"\n"+"Price: "+price+"$\n"+"Stock Quantity: "+stockQuantity;
    }
}
