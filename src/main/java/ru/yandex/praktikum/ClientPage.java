package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClientPage {
    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;

    private By nameLocator = By.xpath("//*[@placeholder='* Имя']");
    private By nameErrorMessageLocator = By.xpath("//*[text()='Введите корректное имя']");
    private By surnameLocator = By.xpath("//*[@placeholder='* Фамилия']");
    private By surnameErrorMessageLocator = By.xpath("//*[text()='Введите корректную фамилию']");
    private By addressLocator = By.xpath("//*[@placeholder='* Адрес: куда привезти заказ']");
    private By addressErrorMessageLocator = By.xpath("//*[text()='Введите корректный адрес']");
    private By phoneNumberLocator = By.xpath("//*[@placeholder='* Телефон: на него позвонит курьер']");
    private By phoneNumberErrorMessageLocator = By.xpath("//*[text()='Введите корректный номер']");
    private By metroStationLocator = By.xpath("//input[@placeholder='* Станция метро']");
    private By metroStationErrorMessageLocator = By.xpath("//div[text()='Выберите станцию']");
    private By nextButtonLocator = By.xpath("//button[text()='Далее']");


    public ClientPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 3);

    }

    public void waitClientPageHeaderLocator() {
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Для кого самокат']")));
    }

    public void setName(String clientName) {

        WebElement name = webDriver.findElement(nameLocator);
        name.sendKeys(clientName, Keys.TAB);
    }

    public void setSurname(String clientSurname) {

        WebElement name = webDriver.findElement(surnameLocator);
        name.sendKeys(clientSurname, Keys.TAB);
    }

    public void setAddress(String clientAddress) {

        WebElement name = webDriver.findElement(addressLocator);
        name.sendKeys(clientAddress, Keys.TAB);
    }

    public void setPhoneNumber(String phoneNumber) {

        WebElement name = webDriver.findElement(phoneNumberLocator);
        name.sendKeys(phoneNumber, Keys.TAB);
    }

    public void setMetroStation(String clientMetroStation) {
        webDriver.findElement(metroStationLocator).sendKeys(clientMetroStation, Keys.DOWN, Keys.ENTER);

    }

    public void findNameErrorMessage() {
        WebElement nameErrorMessage = webDriver.findElement(nameErrorMessageLocator);
    }

    public void findSurnameErrorMessage() {
        WebElement element = webDriver.findElement(surnameErrorMessageLocator);
    }

    public void findAddressErrorMessage() {
        WebElement surnameErrorMessage = webDriver.findElement(addressErrorMessageLocator);
    }

    public void findMetroStationErrorMessage() {
        WebElement metroStationErrorMessage = webDriver.findElement(metroStationErrorMessageLocator);
    }

    public void findPhoneNumberError() {
        WebElement phoneNumberErrorMessage = webDriver.findElement(phoneNumberErrorMessageLocator);
    }


    public void clickNextButton() {
        webDriver.findElement(nextButtonLocator).click();
    }

}
