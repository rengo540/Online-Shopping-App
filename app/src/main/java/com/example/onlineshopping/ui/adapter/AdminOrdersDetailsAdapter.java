/*
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

import com.example.onlineshopping.AdminOrdersDetails;
import com.example.onlineshopping.R;
import com.example.onlineshopping.ui.UpdateActivity;

import java.util.ArrayList;

public class AdminOrdersDetailsAdapter extends RecyclerView.Adapter<AdminOrdersDetailsAdapter.MyViewHolder> {
    Context context;
    Activity activity;
    ArrayList orderId,orderDate,location,rate,feedback;

    public AdminOrdersDetailsAdapter(Context context, Activity activity, */
/*ArrayList orderId,*//*
 ArrayList orderDate,
                                     ArrayList location, ArrayList rate, ArrayList feedback) {
        this.context = context;
        this.activity = activity;
//        this.orderId = orderId;
        this.orderDate = orderDate;
        this.location = location;
        this.rate = rate;
        this.feedback = feedback;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_details_card, parent, false);
        return new AdminOrdersDetailsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.orderDate.setText(String.valueOf(orderDate.get(position)));
        holder.location.setText(String.valueOf(location.get(position)));
        holder.rate.setText(String.valueOf(rate.get(position)));
        holder.feedback.setText(String.valueOf(feedback.get(position)));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("ORDER DTEAILS");
                Intent intent = new Intent(context, AdminOrdersDetails.class);
                intent.putExtra("orderDate",String.valueOf(orderDate.get(position)));
                intent.putExtra("location",String.valueOf(location.get(position)));
                intent.putExtra("rate",String.valueOf(rate.get(position)));
                intent.putExtra("feedback",String.valueOf(feedback.get(position)));
                activity.startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderDate.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView orderDate,location,rate,feedback;
        LinearLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            orderDate = itemView.findViewById(R.id.orderDate);
            location = itemView.findViewById(R.id.location);
            rate = itemView.findViewById(R.id.rate);
            feedback = itemView.findViewById(R.id.feedback);
            layout = itemView.findViewById(R.id.orderDetailsCardLayout);
        }
    }
}
*/
