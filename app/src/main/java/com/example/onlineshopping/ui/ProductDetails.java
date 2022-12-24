package com.example.onlineshopping.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineshopping.R;
import com.example.onlineshopping.database.ShoppingDBHelper;
import com.example.onlineshopping.database.models.Cart;
import com.example.onlineshopping.database.models.Product;

public class ProductDetails extends AppCompatActivity {

    ShoppingDBHelper shoppingDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);


       int id = getIntent().getIntExtra("idProduct",0);

         shoppingDBHelper = new ShoppingDBHelper(this);
         Product p = shoppingDBHelper.getProduct(id);
         p.setProductId(id);
        TextView productTitle = findViewById(R.id.productNameDtls);
        TextView productCat = findViewById(R.id.productCatDtls);
        TextView productPrice = findViewById(R.id.productPriceDtls);
        Button addToCart = findViewById(R.id.addCartBtn);

        productTitle.setText(p.getProductName());
        productCat.append(p.getCategory().getCategoryName());
        productPrice.append(Integer.toString(p.getPrice()));


        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Cart c =  Cart.getInstance();
              c.addProduct(p);
                Toast.makeText(ProductDetails.this, "Added to cart", Toast.LENGTH_SHORT).show();
            }
        });



    }
}