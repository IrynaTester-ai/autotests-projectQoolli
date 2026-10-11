package tests;

import base.BaseTest;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    @Test(description = "Успешный вход в систему")
    public void validLoginTest(){
        Allure.step("Тест на успешную Авторизацию пользователя");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }
    @Test(description = "Неверный пароль")
    public void invalidLoginTest() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("wrong_user", "secret_sauce");
        Assert.assertTrue(loginPage.getErrorText().contains("Username"));
    }

    @Test(description = "Login с пустыми полями")
    public void emptyLoginTest() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("", "");
        Assert.assertTrue(loginPage.getErrorText().contains("Username"));
    }
    @Test(description = "Login без пароля")
    public void emptyPasswordTest() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("standard_user", "");
        Assert.assertTrue(loginPage.getErrorText().contains("Password"));
    }
}
