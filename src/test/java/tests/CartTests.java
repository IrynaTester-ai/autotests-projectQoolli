package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CartTests extends BaseTest {

    @Test(description = "Добавление одного товара в корзину")
    public void addOneItemToCartTest() {
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");// Авторизация на сайте

        InventoryPage inventory = new InventoryPage(driver, wait);// Создание страницы товаров
        inventory.addBackpackToCart(); // Добавляем товар Backpack
        inventory.openCart();// Переходим в корзину

        CartPage cart = new CartPage(driver, wait);// Создаем страницу корзины
        Assert.assertEquals(inventory.getCarBadge(), "1");// Проверяем, что в корзине один товар
    }

    @Test(description = "Добавление двух товара в корзину")
    public void addTwoItemsToCartTest() {
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");// Авторизация на сайте

        InventoryPage inventory = new InventoryPage(driver, wait);// Создание страницы товаров
        inventory.addBackpackToCart(); // Добавляем товар Backpack
        inventory.addBikeLightToCart(); // Добавляем товар BikeLight
        inventory.openCart();// Переходим в корзину

        CartPage cart = new CartPage(driver, wait);// Создаем страницу корзины
        Assert.assertEquals(inventory.getCarBadge(), "2");// Проверяем, что в корзине два товара
    }

    @Test(description = "Проверка одного товара в корзине")
    public void verifyOneItemInCartTest() {
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");// Авторизация на сайте

        InventoryPage inventory = new InventoryPage(driver, wait);// Создание страницы товаров
        inventory.addBackpackToCart(); // Добавляем товар Backpack
        inventory.openCart();// Переходим в корзину

        CartPage cart = new CartPage(driver, wait);// Создаем страницу корзины
        Assert.assertTrue(cart.isBackpackDisplayed());// Проверяем, что в корзине именно Backpack
    }

    @Test(description = "Проверка двух товаров в корзине")
    public void verifyTwoItemsInCartTest() {
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");// Авторизация на сайте

        InventoryPage inventory = new InventoryPage(driver, wait);// Создание страницы товаров
        inventory.addBackpackToCart(); // Добавляем товар Backpack
        inventory.addBikeLightToCart(); // Добавляем товар BikeLight
        inventory.openCart();// Переходим в корзину

        CartPage cart = new CartPage(driver, wait);// Создаем страницу корзины
        Assert.assertTrue(cart.isBackpackDisplayed());// Проверяем, что в корзине именно Backpack
        Assert.assertTrue(cart.isBikeLightDisplayed());// Проверяем, что в корзине именно BikeLight
    }

    @Test(description = "Удаление товара из корзины")
    public void removeItemFromCartTest() {
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");// Авторизация на сайте

        InventoryPage inventory = new InventoryPage(driver, wait);// Создание страницы товаров
        inventory.addBackpackToCart(); // Добавляем товар Backpack
        inventory.openCart();// Переходим в корзину

        CartPage cart = new CartPage(driver, wait);// Создаем страницу корзины
        cart.removeBackpack();// Удаляем товар Backpack из корзины
        Assert.assertFalse(cart.isBackpackPresent()); // Проверяем, что товар Backpack не отображается в корзине
    }

    @Test(description = "Удаление одного товара из двух из корзины")
    public void removeOneOfTwoItemsFromCartTest() {
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");// Авторизация на сайте

        InventoryPage inventory = new InventoryPage(driver, wait);// Создание страницы товаров
        inventory.addBackpackToCart(); // Добавляем товар Backpack
        inventory.addBikeLightToCart(); // Добавляем товар BikeLight
        inventory.openCart();// Переходим в корзину

        CartPage cart = new CartPage(driver, wait);// Создаем страницу корзины
        cart.removeBackpack();// Удаляем товар Backpack из корзины
        Assert.assertFalse(cart.isBackpackPresent());// Проверяем, что товар Backpack не отображается в корзине
        Assert.assertTrue(cart.isBikeLightDisplayed());// Проверяем, что товар BikeLight в корзине
    }

    @Test(description = "Проверка сохранения содержимого корзины после переходов между страницами")
    public void cartPersistsAfterNavigationTest() {
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");// Авторизация на сайте

        InventoryPage inventory = new InventoryPage(driver, wait);// Создание страницы товаров
        inventory.addBackpackToCart(); // Добавляем товар Backpack
        inventory.openCart();// Переходим в корзину

        CartPage cart = new CartPage(driver, wait);// Создаем страницу корзины
        cart.continueShopping(); //Возврат на страницу товаров
        inventory.openCart();// Переходим в корзину еще раз
        Assert.assertTrue(cart.isBackpackDisplayed());// Проверяем, что товар Backpack отображается в корзине
    }
}
