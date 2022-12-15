package com.example.onlineshopping.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshopping.R;
import com.example.onlineshopping.database.models.OrderDetials;

import java.util.ArrayList;
import java.util.List;

public class ProductOrderAdapter  extends RecyclerView.Adapter<ProductOrderAdapter.OrderVH>  {

    private List<OrderDetials> orderDetials=new ArrayList();
    Context context;
    ProductOrderAdapter.RecyclerViewClickListener listener ;

    public ProductOrderAdapter(Context context)
    {this.context=context;
    }

    public void setList(List<OrderDetials> orderDetials, ProductOrderAdapter.RecyclerViewClickListener listener ) {
        this.orderDetials = orderDetials;
        this.listener=listener;
        notifyDataSetChanged();
    }


    class OrderVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView productTitle ;
        TextView productTotalPrice ;
        TextView quantity ;




        private OrderVH(@NonNull View itemView) {

            super(itemView);
            productTitle=itemView.findViewById(R.id.productOrderTitle);
            productTotalPrice=itemView.findViewById(R.id.productTotalPrice);
            quantity=itemView.findViewById(R.id.productQuantity);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v,getAdapterPosition());
        }
    }


    @NonNull
    @Override
    public ProductOrderAdapter.OrderVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_confirm_card, parent, false);

        ProductOrderAdapter.OrderVH b = new ProductOrderAdapter.OrderVH(v);

        return b;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductOrderAdapter.OrderVH productVH,  int position) {
        productVH.productTitle.setText(orderDetials.get(position).getProduct().getProductName());
        productVH.productTotalPrice.append(Integer.toString( orderDetials.get(position).getTotalPrice()));
        productVH.quantity.append(Integer.toString(orderDetials.get(position).getQuantity()));

    }




    @Override
    public int getItemCount() {
        return orderDetials.size();
    }


    public interface RecyclerViewClickListener{
        void onClick (View view,int position);
    }

}
