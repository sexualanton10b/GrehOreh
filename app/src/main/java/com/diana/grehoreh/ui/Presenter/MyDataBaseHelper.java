package com.diana.grehoreh.ui.Presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.diana.grehoreh.ui.Model.Product;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    // Имя базы данных
    public static final String DATABASE_NAME = "ProjectDataBase.db";
    // Версия базы данных
    public static final int DATABASE_VERSION = 1;

    // Таблица my_basket
    public static final String TABLE_MY_BASKET = "my_basket";
    public static final String COLUMN_PRODUCT_NAME = "product_name";
    public static final String COLUMN_PRODUCT_PRICE = "product_price";
    public static final String COLUMN_PRODUCT_WEIGHT = "product_weight";
    public static final String COLUMN_PRODUCT_SELL = "product_sell";
    public static final String COLUMN_PRODUCT_PICTURE = "product_picture";
    public static final String COLUMN_PRODUCT_COUNTRY = "product_country";

    // Таблица user_account
    public static final String TABLE_USER_ACCOUNT = "user_account";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_USER_BONUS = "user_bonus";
    public static final String COLUMN_USER_ADDRESS = "user_address";

    // Таблица my_purchases
    public static final String TABLE_MY_PURCHASES = "my_purchases";
    public static final String COLUMN_PURCHASE_NAME = "purchase_name";
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
                    COLUMN_PRODUCT_PRICE + " REAL, " +
                    COLUMN_PRODUCT_WEIGHT + " REAL, " +
                    COLUMN_PRODUCT_SELL + " REAL, " +
                    COLUMN_PRODUCT_PICTURE + " TEXT, " +
                    COLUMN_PRODUCT_COUNTRY + " TEXT)";

    private static final String SQL_CREATE_USER_ACCOUNT_TABLE =
            "CREATE TABLE " + TABLE_USER_ACCOUNT + " (" +
                    COLUMN_USER_NAME + " TEXT, " +
                    COLUMN_USER_BONUS + " INTEGER, " +
                    COLUMN_USER_ADDRESS + " TEXT)";

    private static final String SQL_CREATE_MY_PURCHASES_TABLE =
            "CREATE TABLE " + TABLE_MY_PURCHASES + " (" +
                    COLUMN_PURCHASE_NAME + " TEXT, " +
                    COLUMN_PURCHASE_PRICE + " REAL, " +
                    COLUMN_PURCHASE_WEIGHT + " REAL, " +
                    COLUMN_PURCHASE_SELL + " REAL, " +
                    COLUMN_PURCHASE_PICTURE + " TEXT, " +
                    COLUMN_PURCHASE_COUNTRY + " TEXT, " +
                    COLUMN_PURCHASE_DATE + " TEXT)";

    public MyDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MY_BASKET_TABLE);
        db.execSQL(SQL_CREATE_USER_ACCOUNT_TABLE);
        db.execSQL(SQL_CREATE_MY_PURCHASES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MY_BASKET);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MY_PURCHASES);
        onCreate(db);
    }
    boolean isProductInBasket(String productName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        boolean isProductExists = false;
        try {
            // Выполнение запроса к базе данных для поиска продукта по имени
            cursor = db.query(TABLE_MY_BASKET, null, "product_name = ?", new String[]{productName}, null, null, null);

            // Проверка наличия результатов запроса
            if (cursor != null && cursor.moveToFirst()) {
                isProductExists = true; // Если результаты запроса найдены, устанавливаем флаг в true
            }
        } finally {
            // Закрытие курсора и соединения с базой данных
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return isProductExists; // Возврат результата проверки
    }
    void addProductToBasket(Product product, double weight, double sell){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        // Заполнение ContentValues данными о продукте
        cv.put("product_name", product.getName());
        cv.put("product_price", product.getPrice());
        cv.put("product_weight", weight);
        cv.put("product_sell", sell);
        cv.put("product_picture", String.valueOf(product.getPictureResource()));
        cv.put("product_country", product.getCountry());

        // Вставка данных в таблицу my_basket
        db.insert(TABLE_MY_BASKET, null, cv);

        // Закрытие соединения с базой данных
        db.close();
    }
}
