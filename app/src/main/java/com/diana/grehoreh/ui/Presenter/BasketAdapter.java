package com.diana.grehoreh.ui.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.diana.grehoreh.EditActivity;
import com.diana.grehoreh.R;
import com.diana.grehoreh.ui.Model.Purchase;

import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {
    private final Context context;
    private List<Purchase> purchases;
    private final LayoutInflater inflater;
    private static final int REQUEST_CODE_EDIT_ACTIVITY = 123;
    private final MyDataBaseHelper myDB;

    public BasketAdapter(Context context) {
        this.context = context;
        this.myDB = new MyDataBaseHelper(context);
        this.purchases = myDB.getAllPurchases();
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BasketAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_basket, parent, false);
        return new BasketAdapter.ViewHolder(view);
    }

    public void updateData() {
        purchases.clear();
        purchases=myDB.getAllPurchases();
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(BasketAdapter.ViewHolder holder, int position) {
        Purchase purchase = purchases.get(position);
        holder.pictureBasket.setImageResource(purchase.getPictureResource());
        holder.nameBasket.setText(purchase.getName());
        holder.weightBasket.setText(String.format("%.2f кг", purchase.getWeight()));
        holder.sellBasket.setText(String.format("%.2f руб", purchase.getSell()));
        holder.basketLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("purchase", purchase);
                ((Activity) context).startActivityForResult(intent, REQUEST_CODE_EDIT_ACTIVITY);
            }
        });
    }



    @Override
    public int getItemCount() {
        return purchases.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView pictureBasket;
        final TextView nameBasket, weightBasket, sellBasket;
        final LinearLayout basketLayout;

        ViewHolder(View view) {
            super(view);
            pictureBasket = view.findViewById(R.id.pictureBasket);
            nameBasket = view.findViewById(R.id.nameBasket);
            weightBasket = view.findViewById(R.id.weightBasket);
            sellBasket = view.findViewById(R.id.sellBasket);
            basketLayout = view.findViewById(R.id.basketLayout);
        }
    }
}
