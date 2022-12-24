package com.example.onlineshopping.ui.products;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshopping.CustomerHome;
import com.example.onlineshopping.R;
import com.example.onlineshopping.database.ShoppingDBHelper;
import com.example.onlineshopping.database.models.Category;
import com.example.onlineshopping.database.models.Product;
import com.example.onlineshopping.database.models.VoiceHolder;
import com.example.onlineshopping.databinding.FragmentProductsBinding;
import com.example.onlineshopping.ui.ProductDetails;
import com.example.onlineshopping.ui.adapter.ProductsAdapter;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.Locale;

public class ProductsFragment extends Fragment {
    private TextView text_result;
    private FragmentProductsBinding binding;
    RecyclerView productsRecyclerView ;
   static ProductsAdapter adapter ;
   static ShoppingDBHelper shoppingDBHelper ;
  static   ProductsAdapter.RecyclerViewClickListener listener;
    SearchView searchView ;
 static    List<Product> products;
    List<Product> products1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProductsViewModel productsViewModel =
                new ViewModelProvider(this).get(ProductsViewModel.class);

        binding = FragmentProductsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // final TextView textView = binding.textHome;
        // productsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);



        productsRecyclerView = root.findViewById(R.id.ProductRecycleView);
        adapter =new ProductsAdapter(getContext());
        //shape of recycler
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        productsRecyclerView.setLayoutManager( mLayoutManager);

        productsRecyclerView.setAdapter(adapter);



        shoppingDBHelper = new ShoppingDBHelper(getContext());
        //shoppingDBHelper.staticData();


        products = shoppingDBHelper.getAllProducts();
        adapter.setList(products,listener);





       listener=new ProductsAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                int idMovie = products.get(position).getProductId();

                Intent intent =new Intent(getActivity(), ProductDetails.class);
                intent.putExtra("idProduct",idMovie);
                startActivity(intent);

            }
        };

        searchView =root.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.isEmpty()){
                    products = shoppingDBHelper.getAllProducts();
                }else{
                    products = shoppingDBHelper.getSimilarProducts(s);

                }
                adapter.setList(products,listener);
                return false;
            }
        });



        return root;
    }

 public static void changeVoiceList(){
     String voice = VoiceHolder.VoiceQuery;
     if(!voice.isEmpty()){
         products = shoppingDBHelper.getSimilarProducts(voice);
         adapter.setList(products,listener);
     }
 }

 public static  void changeScanList(String barcode ){

     products = shoppingDBHelper.getSimilarBarcodeProducts(barcode);
     adapter.setList(products,listener);
 }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}