package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//Проверить: если ввести неправильный номер заказа, попадёшь на страницу статуса заказа. На ней должно быть написано, что такого заказа нет.

public class TestStatusNonExistentOrder {
    private WebDriver webDriver;

    @Before
    public void setup() {
        switch (System.getProperty("browser")) {
            case "firefox":
                WebDriverManager.firefoxdriver().clearDriverCache().clearResolutionCache().setup();
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                webDriver = new FirefoxDriver();
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                webDriver = new ChromeDriver();
        }
    }


    @Test
    public void testStatusNonExistentOrder() {
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
