package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//Проверить: если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса.

public class TestClickYandexButton {
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
    public void testClickYandexButton() {
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
