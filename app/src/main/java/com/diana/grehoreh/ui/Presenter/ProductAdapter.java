package com.diana.grehoreh.ui.Presenter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.diana.grehoreh.R;
import com.diana.grehoreh.ui.Model.Product;
import com.diana.grehoreh.AddActivity;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<Product> products;
    private final Context context;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.pictureView.setImageResource(product.getPictureResource());
        holder.nameView.setText(product.getName());
        holder.priceView.setText(String.valueOf(product.getPrice()));
        holder.listLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddActivity.class);
                intent.putExtra("product", product);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView pictureView;
        final TextView nameView, priceView;
        final ConstraintLayout listLayout;

        ViewHolder(View view) {
            super(view);
            pictureView = view.findViewById(R.id.picture);
            nameView = view.findViewById(R.id.name);
            priceView = view.findViewById(R.id.price);
            listLayout = view.findViewById(R.id.listLayout);
        }
    }
}
