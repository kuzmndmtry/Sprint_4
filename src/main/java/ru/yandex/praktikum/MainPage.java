package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainPage {

    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;

    public MainPage (WebDriver webDriver){
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver,3);
    }
    private String questionButtonNum;

    private By orderButtonTopPageLocator = By.className("Button_Button__ra12g");
    private By orderButtonDownPageLocator = By.xpath("//*[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private By scooterButtonLocator = By.xpath("//img[@alt='Scooter']");
    private By buttonOrderStatusLocator = By.xpath("//*[text()='Статус заказа']");
    private By buttonGoLocator = By.xpath("//*[text()='Go!']");
    private By inputOrderNumLocator = By.xpath("//input[@placeholder='Введите номер заказа']");
    private By buttonYandexLocator = By.xpath("//img[@alt='Yandex']");



    public void openMainPage() {
        webDriver.get("https://qa-scooter.praktikum-services.ru/");
    }
    public void clickOrderButtonTopPage() {
        WebElement orderButtonTopPage = webDriver.findElement(orderButtonTopPageLocator);
        orderButtonTopPage.click();
    }

    public void clickButtonOrderStatus() {
        WebElement buttonOrderStatus = webDriver.findElement(buttonOrderStatusLocator);
        buttonOrderStatus.click();
    }
    public void clickButtonGo() {
        WebElement buttonGo = webDriver.findElement(buttonGoLocator);
        buttonGo.click();
    }

    public void setOrderNum(String orderNumber) {
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Введите номер заказа']")));
        WebElement inputOrderNum = webDriver.findElement(inputOrderNumLocator);
        inputOrderNum.sendKeys(orderNumber);
    }


    public void clickScooterButton() {
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Scooter']")));
        WebElement scooterButton = webDriver.findElement(scooterButtonLocator);
        scooterButton.click();
    }
    public void clickOrderButtonDownPage() {
        WebElement element = webDriver.findElement(By.xpath("//*[text()='Как это работает']"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
        WebElement orderButtonDownPage = webDriver.findElement(orderButtonDownPageLocator);
        orderButtonDownPage.click();
    }

    public void waitHomeHeader() {
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Home_Header__iJKdX")));
    }
    public void clickButtonYa() {
        WebElement buttonYa = webDriver.findElement(buttonYandexLocator);
        buttonYa.click();
    }

    public void scrollToHeaderQuestionsAboutImportant() {
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Вопросы о важном']")));
        WebElement questionsHeader = webDriver.findElement(By.xpath("//*[text()='Вопросы о важном']"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", questionsHeader);
    }

    public void clickQuestion(String questionButtonNum) {
        WebElement buttonImportantQuestion = webDriver.findElement(By.xpath("//*[@id='accordion__heading-" + questionButtonNum + "']"));
        buttonImportantQuestion.click();
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='accordion__panel-" + questionButtonNum + "']")));
    }

    public String getAnswerTextToAQuestion(String questionButtonNum) {
        WebElement buttonAnswerToAQuestion = webDriver.findElement(By.xpath("//*[@id='accordion__panel-" + questionButtonNum + "']"));
        return buttonAnswerToAQuestion.getText();
    }



}
