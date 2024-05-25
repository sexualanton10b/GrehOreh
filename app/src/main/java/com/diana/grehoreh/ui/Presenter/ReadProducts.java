package com.diana.grehoreh.ui.Presenter;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.diana.grehoreh.R;
import com.diana.grehoreh.ui.Model.Product;

public class ReadProducts {
    static ArrayList<Product> products;
    ProductAdapter adapter;
    public static ArrayList<Product> readProductsFromFile(Context context, int rawResourceId) {
        ArrayList<Product> products = new ArrayList<>();

        try (InputStream inputStream = context.getResources().openRawResource(rawResourceId);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    String name = parts[0];
                    String category = parts[1];
                    String country = parts[2];
                    int price = Integer.parseInt(parts[3]);
                    int pictureResource = getResourceId(context, parts[4]);
                    Product product = new Product(name, category, country, price, pictureResource);
                    products.add(product);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    private static int getResourceId(Context context, String resourceName) {
        // Удаляем "R.drawable." из имени ресурса
        String resource = resourceName.replace("R.drawable.", "");
        return context.getResources().getIdentifier(resource, "drawable", context.getPackageName());
    }
    public static ProductAdapter CreateAdapter(Context context){
        products= ReadProducts.readProductsFromFile(context, R.raw.products);
        ProductAdapter adapter=new ProductAdapter(context, products);
        return adapter;
    }
}
