package com.example.onlineshopping.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshopping.R;
import com.example.onlineshopping.ui.UpdateActivity;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    Activity activity;
    ArrayList productID, productName, productPrice, productQuantity, productBarcode, productSalesNumber, productCategory;
   // int position;
   public CustomAdapter(Activity activity, Context context, ArrayList productID, ArrayList productName, ArrayList productPrice
           , ArrayList productQuantity, ArrayList productBarcode,
                        ArrayList productSalesNumber, ArrayList productCategory) {
        this.activity = activity;
        this.context = context;
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productBarcode = productBarcode;
        this.productSalesNumber = productSalesNumber;
        this.productCategory = productCategory;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyle_view_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.productID.setText(String.valueOf(productID.get(position)));
        holder.productName.setText(String.valueOf(productName.get(position)));
        holder.productPrice.setText(String.valueOf(productPrice.get(position))+"$");
        holder.productQuantity.setText("quant: "+String.valueOf(productQuantity.get(position)));
        /*
        holder.productBarcode.setText(String.valueOf(productBarcode.get(position)));
        holder.productSalesNumber.setText(String.valueOf(productSalesNumber.get(position)));*/
        holder.productCategory.setText(String.valueOf(productCategory.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("PRODUCT ID IS "+productID+" !!!!!!!!!!!!!!!!");
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id",String.valueOf(productID.get(position)));
                intent.putExtra("name",String.valueOf(productName.get(position)));
                intent.putExtra("price",String.valueOf(productPrice.get(position)));
                intent.putExtra("quantity",String.valueOf(productQuantity.get(position)));
                intent.putExtra("barcode",String.valueOf(productBarcode.get(position)));
                intent.putExtra("salesNumber",String.valueOf(productSalesNumber.get(position)));
                intent.putExtra("category",String.valueOf(productCategory.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productID, productName, productPrice,
                productQuantity, productBarcode, productSalesNumber, productCategory;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productID = itemView.findViewById(R.id.productID);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.price);
            productQuantity = itemView.findViewById(R.id.quantity);
            /*

            productBarcode = itemView.findViewById(R.id.barcode);
            productSalesNumber = itemView.findViewById(R.id.salesNumber);*/
            productCategory = itemView.findViewById(R.id.category);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
