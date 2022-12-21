package com.example.onlineshopping.ui.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.onlineshopping.AdminHome;
import com.example.onlineshopping.R;
import com.example.onlineshopping.database.ShoppingDBHelper;
import com.example.onlineshopping.ui.AddActivity;
import com.example.onlineshopping.ui.adapter.CustomAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminProductsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminProductsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminProductsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminProductsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminProductsFragment newInstance(String param1, String param2) {
        AdminProductsFragment fragment = new AdminProductsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    RecyclerView recyclerView;

    ShoppingDBHelper dbHelper;
    //ArrayList<Integer> productID, productQuantity, productPrice,productSalesNumber;
    ArrayList<String> productID, productQuantity, productPrice,productSalesNumber,productName, productBarcode,productCategory;
    CustomAdapter customAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_admin_products,container,false);

        recyclerView = rootView.findViewById(R.id.recycleViewID);
        FloatingActionButton addButton = (FloatingActionButton) rootView.findViewById(R.id.addBtn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                startActivity(intent);
            }
        });
        dbHelper = new ShoppingDBHelper(getActivity());
        productID = new ArrayList<>();
        productName = new ArrayList<>();
        productPrice = new ArrayList<>();
        productQuantity = new ArrayList<>();
        productBarcode = new ArrayList<>();
        productSalesNumber = new ArrayList<>();
        productCategory = new ArrayList<>();


        storeDataInArrays();

        customAdapter = new CustomAdapter(getActivity(),getActivity(),productID,productName,productPrice,
                productQuantity,productBarcode,productSalesNumber,productCategory);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;/*inflater.inflate(R.layout.fragment_admin_products, container, false);*/
    }

    void storeDataInArrays(){
        Cursor cursor = dbHelper.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(getActivity(),"No Data", Toast.LENGTH_LONG).show();
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