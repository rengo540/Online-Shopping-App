package com.example.onlineshopping.database.models;


public class OrderDetials  {

    private int quantity;

    private int totalPrice ;

    Order order ;
    Product product ;

    public OrderDetials(){}
    public OrderDetials(int quantity, Order order, Product product) {
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }

    public void calcTotalPrice (){
        totalPrice = product.getPrice()*quantity ;
    }

    public int getTotalPrice() {
        calcTotalPrice();
        return totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Name: "+product.getProductName()+"\n"+"Price: "+product.getPrice()+"$\n"
                + "Quantity Ordered: "+quantity;
    }
}
