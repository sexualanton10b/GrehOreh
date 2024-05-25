package com.diana.grehoreh.ui.Presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diana.grehoreh.R;
import com.diana.grehoreh.ui.Model.Basket;
import com.diana.grehoreh.ui.Model.Purchase;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder>{
    private final Context context;
    private Basket basket;
    private final LayoutInflater inflater;
    private final MyDataBaseHelper myDB;
    public AccountAdapter(Context context) {
        this.context = context;
        this.myDB = new MyDataBaseHelper(context);
        this.basket = myDB.getBasket();
        this.inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public AccountAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_purchase, parent, false);
        return new AccountAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Purchase purchase = basket.purchases.get(position);
        holder.picturePurchase.setImageResource(purchase.getPictureResource());
        holder.namePurchase.setText(purchase.getName());
        holder.weightPurchase.setText(String.format("%.2f кг", purchase.getWeight()));
        holder.sellPurchase.setText(String.format("%.2f руб", purchase.getSell()));
        holder.datePurchase.setText(purchase.getDate());
    }

    @Override
    public int getItemCount() {
        return basket.purchases.size();
    }
    public void updateData() {
            this.basket = myDB.getBasket();
            notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView picturePurchase;
        final TextView namePurchase, weightPurchase, sellPurchase, datePurchase;
        final LinearLayout purchaseLayout;

        ViewHolder(View view) {
            super(view);
            picturePurchase = view.findViewById(R.id.picturePurchase);
            namePurchase = view.findViewById(R.id.namePurchase);
            weightPurchase = view.findViewById(R.id.weightPurchase);
            sellPurchase = view.findViewById(R.id.sellPurchase);
            datePurchase = view.findViewById(R.id.datePurchase);
            purchaseLayout = view.findViewById(R.id.purchaseLayout);
        }
    }
}
