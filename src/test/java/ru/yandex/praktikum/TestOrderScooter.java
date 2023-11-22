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

 /*
Заказ самоката. Весь флоу позитивного сценария. Обрати внимание, что есть две точки входа в сценарий: кнопка «Заказать» вверху страницы и внизу.
Из чего состоит позитивный сценарий:
    Нажать кнопку «Заказать». На странице две кнопки заказа.
    Заполнить форму заказа.
    Проверить, что появилось всплывающее окно с сообщением об успешном создании заказа.
  */

@RunWith(Parameterized.class)
public class TestOrderScooter {

    private WebDriver webDriver;
    private String browser;
    private String orderButtonLocate;
    private String clientName;
    private String clientSurname;
    private String clientAddress;
    private String clientMetroStation;
    private String clientPhoneNumber;
    private String clientDeliveryTime;
    private String clientRentalPeriod;
    private String clientColor;
    private String clientCommentForTheCourier;

    public TestOrderScooter(String browser, String orderButtonLocate, String clientName, String clientSurname, String clientAddress, String clientMetroStation, String clientPhoneNumber, String clientDeliveryTime, String clientRentalPeriod, String clientColor, String clientCommentForTheCourier) {
        this.browser = browser;
        this.orderButtonLocate = orderButtonLocate;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.clientAddress = clientAddress;
        this.clientMetroStation = clientMetroStation;
        this.clientPhoneNumber = clientPhoneNumber;
        this.clientDeliveryTime = clientDeliveryTime;
        this.clientRentalPeriod = clientRentalPeriod;
        this.clientColor = clientColor;
        this.clientCommentForTheCourier = clientCommentForTheCourier;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {"chrome", "up", "Петр", "Петров", "Кольцевая Улица,16", "Бульвар Рокоссовского", "89051234567", "19.12.2023", "двое суток", "чёрный жемчуг", "без комментариев"},
                {"chrome", "down", "Иван", "Иванов", "Волгоградский просп., 139", "Печатники", "+79159876543", "19.12.2023", "трое суток", "серая безысходность", ""},
//                {"firefox", "up", "Петр", "Петров", "Кольцевая Улица,16", "Бульвар Рокоссовского", "89051234567", "19.12.2023", "двое суток", "чёрный жемчуг", "без комментариев"},
//                {"firefox", "down", "Иван", "Иванов", "Волгоградский просп., 139 ", "Печатники", "+79159876543","19.12.2023", "трое суток", "серая безысходность", ""},
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
    public void TestOrderScooter() {

        MainPage mainPage = new MainPage(webDriver);
        mainPage.openMainPage();
        switch (orderButtonLocate) {
            case "up":
                mainPage.clickOrderButtonTopPage();
                break;
            case "down":
            default:
                mainPage.clickOrderButtonDownPage();
        }


        ClientPage clientPage = new ClientPage(webDriver);
        clientPage.waitClientPageHeaderLocator();
        clientPage.setName(clientName);
        clientPage.setSurname(clientSurname);
        clientPage.setAddress(clientAddress);
        clientPage.setMetroStation(clientMetroStation);
        clientPage.setPhoneNumber(clientPhoneNumber);
        clientPage.clickNextButton();

        RentPage rentPage = new RentPage(webDriver);
        rentPage.waitRentPageHeaderLocator();
        rentPage.setDeliveryTimeLocator(clientDeliveryTime);
        rentPage.setRentalPeriodLocator(clientRentalPeriod);
        switch (clientColor) {
            case "чёрный жемчуг":
                rentPage.clickBackColor();
                break;
            case "серая безысходность":
                rentPage.clickGreyColor();
        }
        rentPage.setCommentForTheCourierLocator(clientCommentForTheCourier);
        rentPage.clickOrderButton();

        OrderConfirmWindow orderConfirmWindow = new OrderConfirmWindow(webDriver);
        orderConfirmWindow.clickOrderConfirmButton();

        OrderInfoWindow orderInfoWindow = new OrderInfoWindow(webDriver);
        orderInfoWindow.waitOrderInfoHeaderLocator();
    }

    @After
    public void teardown() {
        webDriver.quit();
    }
}

