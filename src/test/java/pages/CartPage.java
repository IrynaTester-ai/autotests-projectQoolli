package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    private WebDriver driver;// Объект браузера.
    private WebDriverWait wait;// Объект ожидания.

    private By backpack = By.xpath("//div[text()='Sauce Labs Backpack']"); // Название товара Backpack.
    private By bikeLight = By.xpath("//div[text()='Sauce Labs Bike Light']"); // Название товара Bike Light.
    private By checkout = By.id("checkout"); // Кнопка Checkout.
    private By continueShopping = By.id("continue-shopping"); // Кнопка Continue Shopping.
    private By removeBackpack = By.id("remove-sauce-labs-backpack"); // Кнопка Remove у Backpack.
    private By removeBikeLight = By.id("remove-sauce-labs-bike-light"); // Кнопка Remove у Bike Light.

    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver; // Сохраняем браузер.
        this.wait = wait; // Сохраняем ожидание.
    }
    public boolean isBackpackDisplayed(){
        Allure.step("Проверяем наличие Backpack в корзине");
        wait.until(ExpectedConditions.visibilityOfElementLocated(backpack));
        return driver.findElement(backpack).isDisplayed();
    }
    public boolean isBikeLightDisplayed(){
        Allure.step("Проверяем наличие Bike Light в корзине");
        wait.until(ExpectedConditions.visibilityOfElementLocated(bikeLight));
        return driver.findElement(bikeLight).isDisplayed();
    }
    public void removeBackpack(){
        Allure.step("Удаляем Backpack");
        wait.until(ExpectedConditions.elementToBeClickable(removeBackpack));
        driver.findElement(removeBackpack).click();
    }
    public void removeBikeLight(){
        Allure.step("Удаляем Bike Light");
        wait.until(ExpectedConditions.elementToBeClickable(removeBikeLight));
        driver.findElement(removeBikeLight).click();
    }
    public void checkout() {
        Allure.step("Нажимаем Checkout");// Оформление заказа
        wait.until(ExpectedConditions.elementToBeClickable(checkout));
        driver.findElement(checkout).click();
    }
    public void continueShopping(){
        Allure.step("Нажимаем Continue Shopping");// Возврат на страницу товаров
        wait.until(ExpectedConditions.elementToBeClickable(continueShopping));
        driver.findElement(continueShopping).click();
    }
    public boolean isBikeLightPresent(){
        return driver.getPageSource().contains("Sauce Labs Bike Light");//Проверка, что товара больше нет в корзине
    }
}
