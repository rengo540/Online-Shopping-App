package com.example.onlineshopping.ui.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;

import com.example.onlineshopping.AdminHome;
import com.example.onlineshopping.AdminOrdersDetails;
import com.example.onlineshopping.MainActivity;
import com.example.onlineshopping.R;
import com.example.onlineshopping.database.ShoppingDBHelper;
import com.example.onlineshopping.database.models.Order;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrdersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrdersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrdersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrdersFragment newInstance(String param1, String param2) {
        OrdersFragment fragment = new OrdersFragment();
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
    //private static final String TAG = "OrdersFragment";
   // FloatingActionButton dateFAB;
    ShoppingDBHelper dbHelper;
    ListView ordersListView;
    DatePickerDialog.OnDateSetListener dateSetListener;
    List <Order>ordersList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_orders,container,false);
        FloatingActionButton dateFAB =(FloatingActionButton) rootView.findViewById(R.id.dateFAB);
        ordersListView = rootView.findViewById(R.id.ordersListView);
        dbHelper = new ShoppingDBHelper(getActivity());

        ordersList = new ArrayList();
        ordersList = dbHelper.getAllOrders();


        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1,android.R.id.text1,ordersList);
        ordersListView.setAdapter(arrayAdapter);
        ordersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //System.out.println(ordersList.get(i).getLocation());
                Intent intent = new Intent(getActivity(), AdminOrdersDetails.class);
                intent.putExtra("myOrder",ordersList.get(i));
                startActivity(intent);
            }
        });
        dateFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("button");

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                        android.R.style.Theme_Dialog,dateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                //month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                //String date = day + "-" + month + "-" + year;
                Date date = new GregorianCalendar(year, month, day).getTime();
                //System.out.println(date);
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                String formatedDate = df.format(date);
                //System.out.println(formatedDate);
                //ordersList.stream().filter(x->x.getOrderDate()==formatedDate).collect(Collectors.toList());
                List<Order>filteredList = new ArrayList();
                for(Order order:ordersList){
                     if(order.getOrderDate().contains(formatedDate)){
                        filteredList.add(order);
                        //System.out.println(order.getLocation());
                    }
                }

                ArrayAdapter newArrayAdapter = new ArrayAdapter(getActivity(),
                        android.R.layout.simple_list_item_1,android.R.id.text1,filteredList);
                ordersListView.setAdapter(newArrayAdapter);
            }
        };
        return rootView;/*inflater.inflate(R.layout.fragment_orders, container, false);*/
    }


}