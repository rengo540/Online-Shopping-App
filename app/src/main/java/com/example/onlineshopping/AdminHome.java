package com.example.onlineshopping;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.onlineshopping.database.ShoppingDBHelper;
import com.example.onlineshopping.ui.AddActivity;
import com.example.onlineshopping.ui.adapter.CustomAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AdminHome extends AppCompatActivity {

    RecyclerView recyclerView;

    ShoppingDBHelper dbHelper;
    //ArrayList<Integer> productID, productQuantity, productPrice,productSalesNumber;
    ArrayList<String> productID, productQuantity, productPrice,productSalesNumber,productName, productBarcode,productCategory;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);


        recyclerView = findViewById(R.id.recycleViewID);
        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.addBtn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHome.this, AddActivity.class);
                startActivity(intent);
            }
        });
        dbHelper = new ShoppingDBHelper(AdminHome.this);
        productID = new ArrayList<>();
        productName = new ArrayList<>();
        productPrice = new ArrayList<>();
        productQuantity = new ArrayList<>();
        productBarcode = new ArrayList<>();
        productSalesNumber = new ArrayList<>();
        productCategory = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(AdminHome.this,this,productID,productName,productPrice,
                productQuantity,productBarcode,productSalesNumber,productCategory);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminHome.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode ==1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = dbHelper.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No Data", Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                productID.add(cursor.getString(0));
                productName.add(cursor.getString(1));
                productPrice.add(cursor.getString(2));
                productQuantity.add(cursor.getString(3));
                productBarcode.add(cursor.getString(4));
                productSalesNumber.add(cursor.getString(5));
                productCategory.add(cursor.getString(6));
            }
        }
    }
}