package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class CheckoutTests extends BaseTest {
    @Test(description = "Успешное оформление заказа")

    public void checkoutTest() {
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");// Авторизация на сайте

        InventoryPage inventory = new InventoryPage(driver, wait);// Создание страницы товаров
        inventory.addBackpackToCart(); // Добавляем товар
        inventory.openCart();// Переходим в корзину

        CartPage cart = new CartPage(driver, wait);// Создаем страницу корзины
        cart.checkout();// Начинаем checkout процесс

        CheckoutPage checkout = new CheckoutPage(driver, wait);// Создаем страницу checkout
        checkout.fillInformation("John", "Doe", "10001");// Заполняем форму пользователя
        checkout.finishOrder();// Завершаем заказ

        CheckoutCompletePage complete = new CheckoutCompletePage(driver, wait);// Создаем страницу Checkout Complete Page
        Assert.assertEquals(complete.getSuccessMessage(), "Thank you for your order!");
    }

    @Test(description = "Continue shopping после корзины")

    public void continueFromCartTest() {
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");// Авторизация на сайте

        InventoryPage inventory = new InventoryPage(driver, wait);// Создание страницы товаров
        inventory.addBackpackToCart(); // Добавляем товар
        inventory.openCart();// Переходим в корзину

        CartPage cart = new CartPage(driver, wait);// Создаем страницу корзины
        cart.continueShopping();// Возврат назад в магазин
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));// Проверяем, что мы снова на странице товаров
    }
}
