package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineshopping.database.ShoppingDBHelper;
import com.example.onlineshopping.database.models.Order;
import com.example.onlineshopping.database.models.OrderDetials;
import com.example.onlineshopping.database.models.Product;
//import com.example.onlineshopping.ui.adapter.AdminOrdersDetailsAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdminOrdersDetails extends AppCompatActivity {


    //RecyclerView recyclerView;
    ShoppingDBHelper dbHelper;
    TextView orderDate,location,rate,feedback;
    ListView productsListView;
//    AdminOrdersDetailsAdapter ordersDetailsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_orders_details);
        dbHelper = new ShoppingDBHelper(this);
        // get order
        Order order = (Order)getIntent().getSerializableExtra("myOrder");
        productsListView = findViewById(R.id.productDetailsListView);
        List<OrderDetials>orderDetials = dbHelper.getProductsForOrder(order);
        //List<Product> productList = new ArrayList<>();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_2,android.R.id.text1,orderDetials);
        productsListView.setAdapter(arrayAdapter);


        System.out.println("MY ORDER ID IS !!!"+order.getOrderId());
        orderDate = findViewById(R.id.orderDate);
        orderDate.setText(String.valueOf("Order Date: "+order.getOrderDate()));
        location = findViewById(R.id.location);
        location.setText(String.valueOf("Location: "+order.getLocation()));
        rate = findViewById(R.id.rate);
        rate.setText(String.valueOf("Rate: "+order.getFeedbackRate()));
        feedback = findViewById(R.id.feedback);
        feedback.setText(String.valueOf("Feedback: "+order.getFeedbackmessage()));
        //System.out.println(orderDetials.get(1).getProduct());
    }


}