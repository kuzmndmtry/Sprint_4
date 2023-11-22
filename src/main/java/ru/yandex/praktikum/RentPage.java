package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RentPage {

    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;

    public RentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 3);
        ;
    }

    private By deliveryTimeLocator = By.xpath("//*[@placeholder='* Когда привезти самокат']");
    private By rentalPeriodLocator = By.className("Dropdown-arrow");

    private By blackColorLocator = By.id("black");
    private By greyColorLocator = By.id("grey");
    private By commentForTheCourierLocator = By.xpath("//*[@placeholder='Комментарий для курьера']");
    private By orderButtonLocator = By.xpath("//*[@class='Button_Button__ra12g Button_Middle__1CSJM'  and text()='Заказать']");

    public void waitRentPageHeaderLocator() {
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Про аренду']")));
    }
    public void setDeliveryTimeLocator(String clientDeliveryTime) {
        WebElement deliveryTime = webDriver.findElement(deliveryTimeLocator);
        deliveryTime.sendKeys(clientDeliveryTime);
    }

    public void setRentalPeriodLocator(String clientRentalPeriod) {
        WebElement rentalPerion = webDriver.findElement(rentalPeriodLocator);
        rentalPerion.click();
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Dropdown-option")));

        WebElement rentalPeriodCheckBox = webDriver.findElement(By.xpath("//*[text()='" + clientRentalPeriod + "']"));
        rentalPeriodCheckBox.click();
    }

    public void clickBackColor() {
        WebElement blackColor = webDriver.findElement(blackColorLocator);
        blackColor.click();
    }

    public void clickGreyColor() {
        WebElement greyColor = webDriver.findElement(greyColorLocator);
        greyColor.click();

    }

    public void setCommentForTheCourierLocator(String clientCommentForTheCourierLocator) {
        WebElement commentForTheCourier = webDriver.findElement(commentForTheCourierLocator);
        commentForTheCourier.sendKeys(clientCommentForTheCourierLocator);
    }

    public void clickOrderButton() {
        webDriver.findElement(orderButtonLocator).click();
    }
}
