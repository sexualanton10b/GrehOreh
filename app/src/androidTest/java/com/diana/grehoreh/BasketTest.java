package com.diana.grehoreh;

import com.diana.grehoreh.ui.Model.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class BasketTest { // Тестирование разных способов оплаты

    private Basket basket;
    private UserAccount userAccount;

    @Before
    public void setUp() {
        // Создаем пользовательский аккаунт с достаточными средствами
        userAccount = new UserAccount(180, 200, 50);
        List<Purchase> purchases=new ArrayList<>();
        purchases.add(new Purchase("Name", "Овощ", "Грузия", 100, 123, 0.3, 30));
        purchases.add(new Purchase("Name", "Овощ", "Грузия", 100, 123, 0.3, 60));
        purchases.add(new Purchase("Name", "Овощ", "Грузия", 100, 123, 0.3, 90));
        // Устанавливаем метод оплаты наличными
        basket = new Basket(purchases);
    }

    // Тест для оплаты наличными
    @Test
    public void testCalculateTotalPrice(){
        assertEquals(180, (int)basket.getTotalCost());
    }
    @Test
    public void testPayWithCash() {

        basket.setPaymentMethod(new CashPayment());
        // Проверяем, что оплата проходит успешно
        assertTrue(basket.pay(userAccount));
        // Проверяем, что наличные были уменьшены на сумму покупок
        assertEquals(20, userAccount.getCash());
    }

    @Test
    public void testPayWithCard() {

        basket.setPaymentMethod(new CardPayment());
        // Проверяем, что оплата проходит успешно
        assertTrue(basket.pay(userAccount));
        // Проверяем, что наличные были уменьшены на сумму покупок
        assertEquals(0, userAccount.getDebetCard());
    }
    @Test
    public void testPayWithBonusCash() {
        basket.setPaymentMethod(new BonusCashPayment());
        // Проверяем, что оплата проходит успешно
        assertTrue(basket.pay(userAccount));
        // Проверяем, что наличные были уменьшены на сумму покупок
        assertEquals(70, userAccount.getCash());
        assertEquals(0, userAccount.getBonusCard());
    }
    @Test
    public void testPayWithBonusCard() {
        basket.setPaymentMethod(new BonusCardPayment());
        // Проверяем, что оплата проходит успешно
        assertTrue(basket.pay(userAccount));
        // Проверяем, что наличные были уменьшены на сумму покупок
        assertEquals(50, userAccount.getDebetCard());
        assertEquals(0, userAccount.getBonusCard());
    }
}

