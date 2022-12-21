package com.example.onlineshopping.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshopping.R;
import com.example.onlineshopping.database.models.OrderDetials;
import com.example.onlineshopping.database.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductCatAdapter extends RecyclerView.Adapter<ProductCatAdapter.productOrderVH>  {

    private List<OrderDetials> orderDetials=new ArrayList();
    Context context;
    ProductCatAdapter.RecyclerViewClickListener listener ;

    public ProductCatAdapter(Context context)
    {this.context=context;
    }

    public void setList(List<OrderDetials> orderDetials, ProductCatAdapter.RecyclerViewClickListener listener ) {
        this.orderDetials = orderDetials;
        this.listener=listener;
        notifyDataSetChanged();
    }


    class productOrderVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView productTitle ;
        TextView productPrice ;
        Button countUp ;
        Button countDown ;
        TextView productCount;


        private productOrderVH(@NonNull View itemView) {

            super(itemView);
            productTitle=itemView.findViewById(R.id.productTitle);
            productPrice=itemView.findViewById(R.id.productPrice);
            countUp=itemView.findViewById(R.id.BtnUp);
            countDown=itemView.findViewById(R.id.BtnDown);
            productCount=itemView.findViewById(R.id.productCount);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v,getAdapterPosition());
        }
    }


    @NonNull
    @Override
    public ProductCatAdapter.productOrderVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_order_card, parent, false);

        ProductCatAdapter.productOrderVH b = new ProductCatAdapter.productOrderVH(v);

        return b;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCatAdapter.productOrderVH productVH, @SuppressLint("RecyclerView") int position) {
        productVH.productTitle.setText(  orderDetials.get(position).getProduct().getProductName());
        productVH.productPrice.setText(Integer.toString(orderDetials.get(position).getProduct().getPrice()));

        final int[] count = {0};
        productVH.productCount.setText(Integer.toString(0));

        productVH.countUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(orderDetials.get(position).getProduct().getStockQuantity() >= count[0] ) {
                    orderDetials.get(position).setQuantity(++count[0]);
                    productVH.productCount.setText(Integer.toString(orderDetials.get(position).getQuantity()));
                }else{
                    Toast.makeText(context, "no more items for this product", Toast.LENGTH_SHORT).show();
                }
            }
        });

        productVH.countDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(1 <= count[0] ) {

                    orderDetials.get(position).setQuantity(--count[0]);
                    productVH.productCount.setText(Integer.toString(orderDetials.get(position).getQuantity()));
                }else{
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }

    public List<OrderDetials> getOrderDetials() {
        return orderDetials;
    }

    @Override
    public int getItemCount() {
        return orderDetials.size();
    }


    public interface RecyclerViewClickListener{
        void onClick (View view,int position);
    }
}
