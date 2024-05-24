package com.diana.grehoreh.ui.Presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.diana.grehoreh.ui.Model.Basket;
import com.diana.grehoreh.ui.Model.Product;
import com.diana.grehoreh.ui.Model.Purchase;
import com.diana.grehoreh.ui.Model.UserAccount;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    // Имя базы данных
    public static final String DATABASE_NAME = "ProjectDataBase.db";
    // Версия базы данных
    public static final int DATABASE_VERSION = 1;

    // Таблица my_basket
    public static final String TABLE_MY_BASKET = "my_basket";
    public static final String COLUMN_PRODUCT_NAME = "product_name";
    public static final String COLUMN_PRODUCT_CATEGORY = "product_category";
    public static final String COLUMN_PRODUCT_PRICE = "product_price";
    public static final String COLUMN_PRODUCT_WEIGHT = "product_weight";
    public static final String COLUMN_PRODUCT_SELL = "product_sell";
    public static final String COLUMN_PRODUCT_PICTURE = "product_picture";
    public static final String COLUMN_PRODUCT_COUNTRY = "product_country";

    // Таблица user_account
    public static final String TABLE_USER_ACCOUNT = "user_account";
    public static final String COLUMN_USER_DEBET_CARD = "debet_card";  // New column
    public static final String COLUMN_USER_CASH = "cash";              // New column
    public static final String COLUMN_USER_BONUS_CARD = "bonus_card";  // New column

    // Таблица my_purchases
    public static final String TABLE_MY_PURCHASES = "my_purchases";
    public static final String COLUMN_PURCHASE_NAME = "purchase_name";
    public static final String COLUMN_PURCHASE_CATEGORY = "purchase_category";
    public static final String COLUMN_PURCHASE_PRICE = "purchase_price";
    public static final String COLUMN_PURCHASE_WEIGHT = "purchase_weight";
    public static final String COLUMN_PURCHASE_SELL = "purchase_sell";
    public static final String COLUMN_PURCHASE_PICTURE = "purchase_picture";
    public static final String COLUMN_PURCHASE_COUNTRY = "purchase_country";
    public static final String COLUMN_PURCHASE_DATE = "purchase_date";

    // Запросы на создание таблиц
    private static final String SQL_CREATE_MY_BASKET_TABLE =
            "CREATE TABLE " + TABLE_MY_BASKET + " (" +
                    COLUMN_PRODUCT_NAME + " TEXT, " +
                    COLUMN_PRODUCT_CATEGORY + " TEXT, " +
                    COLUMN_PRODUCT_PRICE + " REAL, " +
                    COLUMN_PRODUCT_WEIGHT + " REAL, " +
                    COLUMN_PRODUCT_SELL + " REAL, " +
                    COLUMN_PRODUCT_PICTURE + " INTEGER, " +
                    COLUMN_PRODUCT_COUNTRY + " TEXT)";

    private static final String SQL_CREATE_USER_ACCOUNT_TABLE =
            "CREATE TABLE " + TABLE_USER_ACCOUNT + " (" +
                    COLUMN_USER_DEBET_CARD + " INTEGER, " +
                    COLUMN_USER_CASH + " INTEGER, " +
                    COLUMN_USER_BONUS_CARD + " INTEGER)";

    private static final String SQL_CREATE_MY_PURCHASES_TABLE =
            "CREATE TABLE " + TABLE_MY_PURCHASES + " (" +
                    COLUMN_PURCHASE_NAME + " TEXT, " +
                    COLUMN_PURCHASE_CATEGORY + " TEXT, " +
                    COLUMN_PURCHASE_PRICE + " REAL, " +
                    COLUMN_PURCHASE_WEIGHT + " REAL, " +
                    COLUMN_PURCHASE_SELL + " REAL, " +
                    COLUMN_PURCHASE_PICTURE + " INTEGER, " +
                    COLUMN_PURCHASE_COUNTRY + " TEXT, " +
                    COLUMN_PURCHASE_DATE + " TEXT)";

    public MyDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MY_BASKET_TABLE);
        db.execSQL(SQL_CREATE_MY_PURCHASES_TABLE);
        // Создание таблицы User_Account с начальными значениями
        db.execSQL(SQL_CREATE_USER_ACCOUNT_TABLE);
        // Вставка начальных значений в таблицу User_Account
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_DEBET_CARD, 0);
        contentValues.put(COLUMN_USER_CASH, 0);
        contentValues.put(COLUMN_USER_BONUS_CARD, 0);
        db.insert(TABLE_USER_ACCOUNT, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MY_BASKET);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MY_PURCHASES);
        onCreate(db);
    }

    // Проверка, есть ли продукт в корзине
    public boolean isProductInBasket(String productName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        boolean isProductExists = false;
        try {
            cursor = db.query(TABLE_MY_BASKET, null, "product_name = ?", new String[]{productName}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                isProductExists = true;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return isProductExists;
    }

    // Обновление продукта в корзине
    public void updateProductInBasket(String productName, double newWeight, double newSell) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("product_weight", newWeight);
        cv.put("product_sell", newSell);
        try {
            db.update(TABLE_MY_BASKET, cv, "product_name = ?", new String[]{productName});
        } finally {
            db.close();
        }
    }

    // Добавление продукта в корзину
    public void addProductToBasket(Product product, double weight, double sell) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("product_name", product.getName());
        cv.put("product_category", product.getCategory());
        cv.put("product_price", product.getPrice());
        cv.put("product_weight", weight);
        cv.put("product_sell", sell);
        cv.put("product_picture", product.getPictureResource());
        cv.put("product_country", product.getCountry());
        db.insert(TABLE_MY_BASKET, null, cv);
        db.close();
    }

    // Удаление продукта из корзины
    public void deleteProductFromBasket(String productName) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLE_MY_BASKET, "product_name = ?", new String[]{productName});
        } finally {
            db.close();
        }
    }
    // Метод для очистки таблицы my_basket
    public void clearBasket() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MY_BASKET, null, null);
        db.close();
    }
    Cursor readAllData(String TABLE_NAME){
        String query="SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=null;
        if (db!=null){
            cursor=db.rawQuery(query, null);
        }
        return cursor;
    }
    public List<Purchase> getAllPurchases() {
        List<Purchase> purchaseList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MY_BASKET, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                String category = cursor.getString(1);
                String country = cursor.getString(6);
                int price = cursor.getInt(2);
                int pictureResource = cursor.getInt(5);
                double weight = cursor.getDouble(3);
                double sell = cursor.getDouble(4);
                Purchase purchase = new Purchase(name, category, country, price, pictureResource, weight, sell);
                purchaseList.add(purchase);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return purchaseList;
    }
    // Чтение таблицы user_account и создание объекта UserAccount
    // Чтение таблицы user_account и создание объекта UserAccount
    public UserAccount getUserAccount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER_ACCOUNT, null, null, null, null, null, null);
        UserAccount userAccount = null;

        if (cursor != null && cursor.moveToFirst()) {
            int debetCard = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_DEBET_CARD));
            int cash = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_CASH));
            int bonusCard = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_BONUS_CARD));
            userAccount = new UserAccount(debetCard, cash, bonusCard);
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return userAccount;
    }
    // Метод для обновления данных в таблице user_account
    public boolean updateUserAccount(UserAccount userAccount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_DEBET_CARD, userAccount.getDebetCard());
        contentValues.put(COLUMN_USER_CASH, userAccount.getCash());
        contentValues.put(COLUMN_USER_BONUS_CARD, userAccount.getBonusCard());

        int rowsAffected = db.update(TABLE_USER_ACCOUNT, contentValues, null, null);
        db.close();
        return rowsAffected > 0;
    }
    public void savePurchasesToMyPurchases(Basket basket) {
        SQLiteDatabase db = this.getWritableDatabase();
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        for (Purchase purchase : basket.purchases) {
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_PURCHASE_NAME, purchase.getName());
            cv.put(COLUMN_PURCHASE_CATEGORY, purchase.getCategory());
            cv.put(COLUMN_PURCHASE_PRICE, purchase.getPrice());
            cv.put(COLUMN_PURCHASE_WEIGHT, purchase.getWeight());
            cv.put(COLUMN_PURCHASE_SELL, purchase.getSell());
            cv.put(COLUMN_PURCHASE_PICTURE, purchase.getPictureResource());
            cv.put(COLUMN_PURCHASE_COUNTRY, purchase.getCountry());
            cv.put(COLUMN_PURCHASE_DATE, currentDate);

            db.insert(TABLE_MY_PURCHASES, null, cv);
        }
        db.close();
    }
    public Basket getBasket() {
        List<Purchase> purchaseList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MY_PURCHASES, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_NAME));
                String category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_CATEGORY));
                int price = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_PRICE));
                double weight = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_WEIGHT));
                double sell = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_SELL));
                int picture = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_PICTURE));
                String country = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_COUNTRY));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PURCHASE_DATE));

                Purchase purchase = new Purchase(name, category, country, price, picture, weight, sell, date);
                purchaseList.add(purchase);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return (new Basket(purchaseList));
    }
}
