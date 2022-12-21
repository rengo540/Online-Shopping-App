package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineshopping.database.ShoppingDBHelper;
import com.example.onlineshopping.database.models.Order;
import com.example.onlineshopping.ui.adapter.AdminOrdersDetailsAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdminOrdersDetails extends AppCompatActivity {


    RecyclerView recyclerView;
    ShoppingDBHelper dbHelper;
    TextView orderDate,location,rate,feedback;
    AdminOrdersDetailsAdapter ordersDetailsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_orders_details);
        Order order = (Order)getIntent().getSerializableExtra("myOrder");
        System.out.println("MY ORDER IS !!!"+order.getLocation());
        orderDate = findViewById(R.id.orderDate);
        orderDate.setText(String.valueOf("Order Date: "+order.getOrderDate()));
        location = findViewById(R.id.location);
        location.setText(String.valueOf("Location: "+order.getLocation()));
        rate = findViewById(R.id.rate);
        rate.setText(String.valueOf("Rate: "+order.getFeedbackRate()));
        feedback = findViewById(R.id.feedback);
        feedback.setText(String.valueOf("Feedback: "+order.getFeedbackmessage()));
       /* storeDataInArrays();

        ordersDetailsAdapter = new AdminOrdersDetailsAdapter(this,this,orderDate,location,
                rate,feedback);
        recyclerView.setAdapter(ordersDetailsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/
    }


}