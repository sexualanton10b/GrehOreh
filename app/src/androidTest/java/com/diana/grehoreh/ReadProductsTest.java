package com.diana.grehoreh;

import android.content.Context;
import android.content.res.Resources;
import androidx.test.core.app.ApplicationProvider;
import com.diana.grehoreh.ui.Model.Product;
import com.diana.grehoreh.ui.Presenter.ReadProducts;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class ReadProductsTest {

    private Context context;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
    }

    @Test
    public void testReadProductsFromFile() {
        // Act
        ArrayList<Product> products = ReadProducts.readProductsFromFile(context, R.raw.products);

        // Assert
        assertNotNull(products);
        assertEquals(10, products.size());

        Product product1 = products.get(0);
        assertEquals("Яблоко", product1.getName());
        assertEquals("Фрукт", product1.getCategory());
        assertEquals("Россия", product1.getCountry());
        assertEquals(100, product1.getPrice());
        assertEquals(R.drawable.apple, product1.getPictureResource());

        Product product2 = products.get(9);
        assertEquals("Манго", product2.getName());
        assertEquals("Фрукт", product2.getCategory());
        assertEquals("Индия", product2.getCountry());
        assertEquals(130, product2.getPrice());
        assertEquals(R.drawable.mango, product2.getPictureResource());
    }
}
