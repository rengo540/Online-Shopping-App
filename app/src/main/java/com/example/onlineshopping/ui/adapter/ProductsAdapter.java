package com.example.onlineshopping.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshopping.R;
import com.example.onlineshopping.database.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.productVH>  {

    private List<Product> products=new ArrayList();
    Context context;
    RecyclerViewClickListener listener ;

    public ProductsAdapter(Context context)
    {this.context=context;
    }

    public void setList(List<Product> products,RecyclerViewClickListener listener ) {
        this.products = products;
        this.listener=listener;
        notifyDataSetChanged();
    }

//View Holder
    class productVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView productTitle ;
        TextView productPrice ;
        TextView productCategory ;


        public productVH(@NonNull View itemView) {

            super(itemView);
            productTitle=itemView.findViewById(R.id.productTitle);
            productPrice=itemView.findViewById(R.id.productPrice);
            productCategory=itemView.findViewById(R.id.productCategory);

             itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v,getAdapterPosition());
        }
    }


    @NonNull
    @Override
    public productVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);

        productVH b = new productVH(v);

        return b;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.productVH productVH, int position) {

        productVH.productTitle.setText(products.get(position).getProductName());
        productVH.productPrice.setText(Integer.toString( products.get(position).getPrice()));
        productVH.productCategory.setText(products.get(position).getCategory().getCategoryName());


    }




    @Override
    public int getItemCount() {
        return products.size();
    }


    public interface RecyclerViewClickListener{
        void onClick (View view,int position);
    }
}
