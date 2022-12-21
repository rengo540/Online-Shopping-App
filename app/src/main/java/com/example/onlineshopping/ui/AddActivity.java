package com.example.onlineshopping.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.onlineshopping.R;
import com.example.onlineshopping.database.ShoppingDBHelper;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        EditText productNameText = findViewById(R.id.productName);
        EditText productPriceText =  findViewById(R.id.price);
        EditText productStockQuantityText = findViewById(R.id.stockQuantity);
        EditText productBarcodeText =  findViewById(R.id.barcode);
        EditText productSalesNumberText =  findViewById(R.id.salesNumber);
        EditText productCategoryText =  findViewById(R.id.category);
        Button addBtn =  findViewById(R.id.addProductBtn);
        //Product product = new Product(productId,productName,productPrice,productStockQuantity,productBarcode,productCategoryو);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productName = productNameText.getText().toString();
                String productCategory = productCategoryText.getText().toString();
                String productBarcode = productBarcodeText.getText().toString();
                int productStockQuantity = Integer.parseInt(productStockQuantityText.getText().toString());
                int productPrice = Integer.parseInt(productPriceText.getText().toString());
                int productSalesNumber = Integer.parseInt(productSalesNumberText.getText().toString());

                System.out.println("name is"+productName);
                System.out.println("hello");
                ShoppingDBHelper db = new ShoppingDBHelper(AddActivity.this);
                db.addProduct( productName, productPrice, productStockQuantity, productBarcode,
                        productSalesNumber, productCategory);
            }
        });

    }
}