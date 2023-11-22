package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

//Проверить ошибки для всех полей формы заказа.

@RunWith(Parameterized.class)
public class TestErrorMessageOnClientPage {
    private WebDriver webDriver;
    private String clientName;
    private String clientSurname;
    private String clientAddress;
    private String clientPhoneNumber;

    public TestErrorMessageOnClientPage(String clientName, String clientSurname, String clientAddress, String clientPhoneNumber) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.clientAddress = clientAddress;
        this.clientPhoneNumber = clientPhoneNumber;
    }

    @Parameterized.Parameters
    public static Object[][] TestData() {
        return new Object[][]{
                {"Ivan", "Ivanov", "MSK", "qwertyuiop"},
                {"123", "123", "123", "+7(915)376-12-23"},
        };
    }

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    @Test
    public void TestErrorMessageOnClientPage() {

        MainPage mainPage = new MainPage(webDriver);
        mainPage.openMainPage();
        mainPage.clickOrderButtonTopPage();

        ClientPage clientPage = new ClientPage(webDriver);
        clientPage.waitClientPageHeaderLocator();
        clientPage.setName(clientName);
        clientPage.findNameErrorMessage();
        clientPage.setSurname(clientSurname);
        clientPage.findSurnameErrorMessage();
        clientPage.setAddress(clientAddress);
        clientPage.findAddressErrorMessage();
        clientPage.setPhoneNumber(clientPhoneNumber);
        clientPage.findPhoneNumberError();
        clientPage.clickNextButton();
        clientPage.findMetroStationErrorMessage();

    }

    @After
    public void teardown() {
        webDriver.quit();
    }
}
