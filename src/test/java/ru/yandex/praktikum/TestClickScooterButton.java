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

@RunWith(Parameterized.class)
public class TestClickScooterButton {
    private WebDriver webDriver;
    private String browser;
    private String pageFromClick;

    public TestClickScooterButton(String browser, String pageFromClick) {
        this.browser = browser;
        this.pageFromClick = pageFromClick;
    }

    @Parameterized.Parameters
    public static Object[][] TestClickScooterButton() {
        return new Object[][]{
                {"chrome","From client page"},
                {"chrome","From rent page"},
                {"chrome","From status page"},
        };
    }
    @Before
    public void setup() {
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().clearDriverCache().clearResolutionCache().setup();
                webDriver = new FirefoxDriver();
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
        }
    }

    @Test
    public void ClickScooterButton() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openMainPage();
        switch (pageFromClick) {
            case "From client page":
                mainPage.clickOrderButtonTopPage();
                mainPage.clickScooterButton();
                mainPage.waitHomeHeader();
                break;
            case "From rent page":
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
                break;
            case "From status page":
            default:
                mainPage.clickButtonOrderStatus();
                mainPage.setOrderNum("1");
                mainPage.clickButtonGo();
                mainPage.clickScooterButton();
                mainPage.waitHomeHeader();
        }
    }
    @After
    public void teardown() {
        webDriver.quit();
    }
}
