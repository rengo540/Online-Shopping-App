package com.example.onlineshopping.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshopping.R;
import com.example.onlineshopping.database.models.Category;
import com.example.onlineshopping.database.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryVH>  {

        private List<Category> categories=new ArrayList();
        Context context;
    CategoriesAdapter.RecyclerViewClickListener listener ;

    public CategoriesAdapter(Context context)
        {this.context=context;
        }

        public void setList(List<Category> categories, CategoriesAdapter.RecyclerViewClickListener listener ) {
            this.categories = categories;
            this.listener=listener;
            notifyDataSetChanged();
        }


        class CategoryVH extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView productCategory ;


            public CategoryVH(@NonNull View itemView) {

                super(itemView);
                productCategory=itemView.findViewById(R.id.categoryTitle);

                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                listener.onClick(v,getAdapterPosition());
            }
        }


        @NonNull
        @Override
        public CategoriesAdapter.CategoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card, parent, false);

            CategoriesAdapter.CategoryVH b = new CategoriesAdapter.CategoryVH(v);

            return b;
        }

        @Override
        public void onBindViewHolder(@NonNull CategoriesAdapter.CategoryVH categoryVH, int position) {


            categoryVH.productCategory.setText(categories.get(position).getCategoryName());

            // Picasso.Builder builder = new Picasso.Builder(context);
            //  builder.downloader(new OkHttp3Downloader(context));
      /*  builder.build().load(ApiClient.getInstance().getImgUrl() + movies.get(position).getPosterPath())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(movieVH.movieImg);*/

        }




        @Override
        public int getItemCount() {
            return categories.size();
        }


        public interface RecyclerViewClickListener{
            void onClick (View view,int position);
        }
}
