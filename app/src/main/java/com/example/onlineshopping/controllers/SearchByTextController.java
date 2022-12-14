package com.example.onlineshopping.controllers;

import android.content.Context;

import com.example.onlineshopping.database.ShoppingDBHelper;
import com.example.onlineshopping.database.models.Product;

import java.util.ArrayList;
import java.util.List;

public class SearchByTextController {
    //Task 1 : Search by Text
    //Product product = new Product();
    Context context;
    private String query;
    ShoppingDBHelper shoppingDBHelper;

    public SearchByTextController(Context context)
    {
        this.context = context;
        shoppingDBHelper= new ShoppingDBHelper(context);
    }
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    //method to get query matchers
    public List<Product> text_search_in_DB()
    {
      return shoppingDBHelper.getSimilarProducts(query);
    }















}
