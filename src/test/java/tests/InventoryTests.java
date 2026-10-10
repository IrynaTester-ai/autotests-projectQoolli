package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class InventoryTests extends BaseTest {

    @Test(description = "Проверка заголовка страницы Products")
    public void inventoryTitleTest() {
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");// Авторизация на сайте
        InventoryPage inventory = new InventoryPage(driver, wait);// Создание страницы товаров
        Assert.assertEquals(inventory.getTitle(), "Products");// Проверка текста заголовка страницы
    }

    @Test(description = "Проверка выхода из системы")
    public void logout() {
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");// Авторизация на сайте
        InventoryPage inventory = new InventoryPage(driver, wait);// Создание страницы товаров
        inventory.logout();// Нажимаем logout через меню
        Assert.assertTrue(driver.getCurrentUrl().contains("www.saucedemo"));//Проверяем, что сайт вернулся на стартовую стр.
    }

    @Test(description = "Проверка, что после Logout нельзя попасть на стр.Inventory")
    public void inventoryUnavailableAfterLogoutTest() {
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");// Авторизация на сайте
        InventoryPage inventory = new InventoryPage(driver, wait);// Создание страницы товаров
        inventory.logout();// Нажимаем logout через меню
        driver.get("https://www.saucedemo.com/inventory.html");// Пытаемся попасть на страницу товаров
        Assert.assertFalse(driver.getCurrentUrl().contains("inventory"));//Проверяем, что после Logout нельзя попасть на Inventory
    }

    @Test(description = "Проверка счетчика корзины после открытия корзины")
    public void cartBadgeAfterOpeningCartTest() {
        new LoginPage(driver, wait).login("standard_user", "secret_sauce");// Авторизация на сайте
        InventoryPage inventory = new InventoryPage(driver, wait);// Создание страницы товаров
        inventory.addBackpackToCart();// Добавляем товар в корзину
        inventory.openCart();// Открываем корзину
        Assert.assertEquals(inventory.getCarBadge(),"1");
    }
}
