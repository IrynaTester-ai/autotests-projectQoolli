package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutCompletePage {
    private WebDriver driver;// Объект браузера.
    private WebDriverWait wait;// Объект ожидания.

    private By successMessage = By.className("complete-header");// Локатор

    public CheckoutCompletePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver; // Сохраняем браузер.
        this.wait = wait; // Сохраняем ожидание.
    }
    public String getSuccessMessage(){
        Allure.step("Проверка сообщения об успешном заказе");
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));// Ожидание сообщения
        return driver.findElement(successMessage).getText();// Возвращаем текст сообщения
    }
}
