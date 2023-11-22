package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//Проверить: если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса.

public class TestClickYandexButton {
    private WebDriver webDriver;


    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }


    @Test
    public void TestClickYandexButton() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openMainPage();
        mainPage.clickButtonYa();
        String winHandleBefore = webDriver.getWindowHandle();
        for (String winHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(winHandle);
        }
        YandexPage yandexPage = new YandexPage(webDriver);
        yandexPage.waitLogoDzen();
        webDriver.close();
        webDriver.switchTo().window(winHandleBefore);
    }

    @After
    public void teardown() {
        webDriver.quit();
    }
}
