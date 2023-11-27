package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//Проверить: если нажать на логотип «Самоката», попадёшь на главную страницу «Самоката».


public class TestClickScooterButton {
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
    public void clickScooterButton() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openMainPage();
        //From client page:
        mainPage.clickOrderButtonTopPage();
        mainPage.clickScooterButton();
        mainPage.waitHomeHeader();
        //From rent page:
        mainPage.clickOrderButtonTopPage();
        ClientPage clientPage = new ClientPage(webDriver);
        clientPage.waitClientPageHeaderLocator();
        clientPage.setName("Иван");
        clientPage.setSurname("Иванов");
        clientPage.setAddress("Москва");
        clientPage.setMetroStation("Охотный ряд");
        clientPage.setPhoneNumber("81234567890");
        clientPage.clickNextButton();
        mainPage.clickScooterButton();
        mainPage.waitHomeHeader();
        //From status page:
        mainPage.clickButtonOrderStatus();
        mainPage.setOrderNum("1");
        mainPage.clickButtonGo();
        mainPage.clickScooterButton();
        mainPage.waitHomeHeader();
    }

    @After
    public void teardown() {
        webDriver.quit();
    }
}

