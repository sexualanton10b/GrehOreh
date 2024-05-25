package com.diana.grehoreh;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.test.core.app.ApplicationProvider;
import com.diana.grehoreh.ui.Model.Product;
import com.diana.grehoreh.ui.Presenter.MyDataBaseHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyDataBaseHelperTest {

    private MyDataBaseHelper myDataBaseHelper;
    private SQLiteDatabase db;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        myDataBaseHelper = new MyDataBaseHelper(context);
        db = myDataBaseHelper.getWritableDatabase();
    }

    @After
    public void tearDown() {
        db.close();
        myDataBaseHelper.close();
    }

    @Test
    public void testAddProductToBasket() {
        // Arrange
        Product product = new Product("Apple", "Fruits", "USA", 100, 123);
        double weight = 2.0;
        double sell = 3.0;
        // Act
        myDataBaseHelper.addProductToBasket(product, weight, sell);
        db = myDataBaseHelper.getWritableDatabase();
        // Assert
        Cursor cursor = db.query(MyDataBaseHelper.TABLE_MY_BASKET, null, "product_name = ?", new String[]{"Apple"}, null, null, null);
        assertNotNull(cursor);
        assertTrue(cursor.moveToFirst());
        assertEquals("Apple", cursor.getString(cursor.getColumnIndexOrThrow("product_name")));
        assertEquals("Fruits", cursor.getString(cursor.getColumnIndexOrThrow("product_category")));
        assertEquals(100, cursor.getDouble(cursor.getColumnIndexOrThrow("product_price")), 0.01);
        assertEquals(weight, cursor.getDouble(cursor.getColumnIndexOrThrow("product_weight")), 0.01);
        assertEquals(sell, cursor.getDouble(cursor.getColumnIndexOrThrow("product_sell")), 0.01);
        assertEquals(123, cursor.getInt(cursor.getColumnIndexOrThrow("product_picture")));
        assertEquals("USA", cursor.getString(cursor.getColumnIndexOrThrow("product_country")));
        cursor.close();
    }
}

