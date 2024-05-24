package com.diana.grehoreh.ui.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diana.grehoreh.EditActivity;
import com.diana.grehoreh.R;
import com.diana.grehoreh.ui.Model.Basket;
import com.diana.grehoreh.ui.Model.Purchase;

import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {
    private final Context context;
    private List<Purchase> purchases;
    private final Basket basket;
    private final LayoutInflater inflater;
    private static final int REQUEST_CODE_ACTIVITY = 123;
    private final MyDataBaseHelper myDB;

    public BasketAdapter(Context context) {
        this.context = context;
        this.myDB = new MyDataBaseHelper(context);
        this.purchases = myDB.getAllPurchases();
        this.basket = new Basket(purchases);
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_basket, parent, false);
        return new ViewHolder(view);
    }
    public void setData(List<Purchase> purchases) {
        this.purchases = purchases;
        notifyDataSetChanged();
    }


    public void updateData() {
        purchases = myDB.getAllPurchases();
        basket.setPurchases(purchases);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Purchase purchase = purchases.get(position);
        holder.pictureBasket.setImageResource(purchase.getPictureResource());
        holder.nameBasket.setText(purchase.getName());
        holder.weightBasket.setText(String.format("%.2f кг", purchase.getWeight()));
        holder.sellBasket.setText(String.format("%.2f руб", purchase.getSell()));

        // Добавляем обработчик клика для кнопки "Удалить"
        holder.deleteButton.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                myDB.deleteProductFromBasket(purchase.getName());
                purchases.remove(adapterPosition);
                notifyItemRemoved(adapterPosition);
                notifyItemRangeChanged(adapterPosition, purchases.size());
            }
        });

        // Добавляем обработчик клика для редактирования
        holder.basketLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditActivity.class);
            intent.putExtra("purchase", purchase);
            ((Activity) context).startActivityForResult(intent, REQUEST_CODE_ACTIVITY);
        });
    }

    @Override
    public int getItemCount() {
        return purchases.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView pictureBasket;
        final TextView nameBasket, weightBasket, sellBasket;
        final Button deleteButton;
        final LinearLayout basketLayout;

        ViewHolder(View view) {
            super(view);
            pictureBasket = view.findViewById(R.id.pictureBasket);
            nameBasket = view.findViewById(R.id.nameBasket);
            weightBasket = view.findViewById(R.id.weightBasket);
            sellBasket = view.findViewById(R.id.sellBasket);
            deleteButton = view.findViewById(R.id.delete_button);
            basketLayout = view.findViewById(R.id.basketLayout);
        }
    }
}
