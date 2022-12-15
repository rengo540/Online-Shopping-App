package com.example.onlineshopping.ui.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshopping.R;
import com.example.onlineshopping.database.ShoppingDBHelper;
import com.example.onlineshopping.database.models.Cart;
import com.example.onlineshopping.database.models.Customer;
import com.example.onlineshopping.database.models.CustomerLoginHolder;
import com.example.onlineshopping.database.models.Order;
import com.example.onlineshopping.database.models.OrderDetials;
import com.example.onlineshopping.database.models.Product;
import com.example.onlineshopping.databinding.FragmentCartBinding;
import com.example.onlineshopping.ui.OrderConfirm;
import com.example.onlineshopping.ui.ProductDetails;
import com.example.onlineshopping.ui.adapter.ProductCatAdapter;
import com.example.onlineshopping.ui.adapter.ProductsAdapter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    RecyclerView productsOrderRecyclerView ;
    static ProductCatAdapter adapter ;
    static ShoppingDBHelper shoppingDBHelper ;
    static   ProductCatAdapter.RecyclerViewClickListener listener;
    SearchView searchView ;
     List<OrderDetials> productsOrder;
    Button orderButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CartViewModel cartViewModel =
                new ViewModelProvider(this).get(CartViewModel.class);

        binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

       // final TextView textView = binding.textSlideshow;
       // cartViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);




        productsOrderRecyclerView = root.findViewById(R.id.productsOrderRecycler);
        adapter =new ProductCatAdapter(getContext());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);

        productsOrderRecyclerView.setLayoutManager( mLayoutManager);
        productsOrderRecyclerView.setAdapter(adapter);

        shoppingDBHelper = new ShoppingDBHelper(getContext());
        //   shoppingDBHelper.staticData();
        productsOrder = new ArrayList<>();
        List<Product> products = Cart.getInstance().getProductList();
        for(int i=0 ;i< products.size();i++){
            OrderDetials orderDetials = new OrderDetials();
            orderDetials.setProduct(products.get(i));
            //orderDetials.setQuantity(0);
            productsOrder.add(orderDetials);
        }

        adapter.setList(productsOrder,listener);


       // productsOrder= adapter.getOrderDetials();
        orderButton =root.findViewById(R.id.orderBtn);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart.getInstance().setOrderDetialsList(productsOrder);
                Intent intent =new Intent(getActivity(), OrderConfirm.class);
                startActivity(intent);
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}