package com.example.onlineshopping.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.example.onlineshopping.R;
import com.example.onlineshopping.database.ShoppingDBHelper;
import com.example.onlineshopping.database.models.Product;
import com.example.onlineshopping.ui.adapter.ProductsAdapter;

import java.util.List;

public class ProductsCategory extends AppCompatActivity {

    RecyclerView productsRecyclerView ;
    static ProductsAdapter adapter ;
    static ShoppingDBHelper shoppingDBHelper ;
    static   ProductsAdapter.RecyclerViewClickListener listener;
    static List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_category);

        int id = getIntent().getIntExtra("idCat",1);


        productsRecyclerView = findViewById(R.id.productCatRecycler);
        adapter =new ProductsAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);

        productsRecyclerView.setLayoutManager( mLayoutManager);
        productsRecyclerView.setAdapter(adapter);

        shoppingDBHelper = new ShoppingDBHelper(this);
        //   shoppingDBHelper.staticData();


        products = shoppingDBHelper.getProductsInCateogry(id);

        listener=new ProductsAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                int idProduct = products.get(position).getProductId();

                Intent intent =new Intent(getApplicationContext(), ProductDetails.class);
                intent.putExtra("idProduct",idProduct);
                startActivity(intent);

            }
        };

        adapter.setList(products,listener);

    }
}