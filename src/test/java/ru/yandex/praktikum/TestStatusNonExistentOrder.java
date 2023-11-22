package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//Проверить: если ввести неправильный номер заказа, попадёшь на страницу статуса заказа. На ней должно быть написано, что такого заказа нет.

public class TestStatusNonExistentOrder {
    private WebDriver webDriver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    @Test
    public void TestStatusNonExistentOrder() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openMainPage();
        mainPage.clickButtonOrderStatus();
        mainPage.setOrderNum("non-existent order number");
        mainPage.clickButtonGo();
        StatusPage statusPage = new StatusPage(webDriver);
        statusPage.findImgNotFound();
    }

    @After
    public void teardown() {
        webDriver.quit();
    }
}
