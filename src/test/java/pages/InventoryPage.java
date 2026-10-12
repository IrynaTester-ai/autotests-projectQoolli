package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By backpack = By.id("add-to-cart-sauce-labs-backpack");// Кнопка добавления рюкзака.
    private By bikeLight = By.id("add-to-cart-sauce-labs-bike-light");// Кнопка добавления фонаря.
    private By cartButton = By.className("shopping_cart_link"); // Значок корзины.
    private By cartBadge = By.className("shopping_cart_badge"); // Счетчик товаров в корзине.
    private By menuButton = By.id("react-burger-menu-btn"); // Кнопка открытия бокового меню.
    private By logout = By.id("logout_sidebar_link"); // Кнопка Logout.
    private By title = By.className("title"); // Заголовок страницы Products.
    private By removeBackpack = By.id("remove-sauce-labs-backpack"); // Кнопка Remove у Backpack.
    private By removeBikeLight = By.id("remove-sauce-labs-bike-light"); // Кнопка Remove у Bike Light.
    private By sortDropdown = By.className("product_sort_container"); // Выпадающий список сортировки.

    public InventoryPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }
    public void addBackpackToCart(){
        Allure.step("Добавление рюкзака в корзину");
        wait.until(ExpectedConditions.elementToBeClickable(backpack));
        driver.findElement(backpack).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(removeBackpack));// Ждём, что товар реально добавился и
        // (кнопка сменилась на Remove)
    }
    public void addBikeLightToCart(){
        Allure.step("Добавление фонаря в корзину");
        wait.until(ExpectedConditions.elementToBeClickable(bikeLight));
        driver.findElement(bikeLight).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(removeBikeLight));// Ждём, что товар реально добавился и
        // (кнопка сменилась на Remove)
    }
    public void openCart(){
        Allure.step("Открытие корзины");
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        driver.findElement(cartButton).click();
        wait.until(ExpectedConditions.urlContains("cart.html"));// Ждём, что страница корзины реально загрузилась
    }
    public String getCarBadge(){
        Allure.step("Получение количества товаров в корзине");
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
        return driver.findElement(cartBadge).getText();
    }
    public void logout(){
        Allure.step("Выход из аккаунта");
        driver.findElement(menuButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(logout));
        driver.findElement(logout).click();
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/"));// Ждём, что произошёл реальный возврат на стр. логина
    }
    public String getTitle(){
        return driver.findElement(title).getText();
    }
    public boolean isInventoryPageDisplayed(){
        return driver.getCurrentUrl().contains("inventory");
    }
}
