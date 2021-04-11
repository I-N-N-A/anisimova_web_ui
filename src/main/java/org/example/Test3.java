package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Test3 {

    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String STUDENT_LOGIN = "Applanatest";
    private static final String STUDENT_PASSWORD = "Student2020!";
    private static  WebDriver driver;

    static {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
//        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        login();

        driver.findElement(By.xpath(
                ".//li[@class='dropdown']/a[@class='unclickable']" +
                        "/span[text()='Справочники']")).click();
        sleep(2000);
        driver.findElement(By.xpath(".//*[@class='title' and text()='Курсы валют']")).click();


        driver.findElement(new By.ByCssSelector(".filter-select")).click();

        driver.findElement(By.xpath("//label[contains(.,'Доллар США')]")).click();


//        driver.findElement(By.xpath(
//                "//span/div[2]/div")).click();
//
//        driver.findElement(By.xpath(
//                "//select[@name='date']")).click();
//        WebElement element = driver.findElement(By.xpath("//select[@name='date']"));
//        Select dropDown = new Select(element);
//        dropDown.selectByVisibleText("равно");
//         element.click();
//
//
//        driver.findElement(By.cssSelector(
//                ".filter-start-date .datepicker-input")).click();
//        driver.findElement(By.cssSelector(".open > .datepicker-input")).click();
//
////        driver.switchTo().frame(
////                driver.findElement(By.cssSelector("iframe.demo-frame")));
////        setDatepicker(driver, "#datepicker", "02/21/2021");
//
//        ((JavascriptExecutor)driver).executeScript ("document.getElementById('fromDate').removeAttribute('readonly',0);"); // Enables the from date box
//
//        WebElement fromDateBox= driver.findElement(By.id("fromDate"));
//        fromDateBox.clear();
//        fromDateBox.sendKeys("8-Dec-2014");


        driver.close();
        driver.quit();
    }



    private static void login() {
        driver.get(LOGIN_PAGE_URL);

        WebElement loginTextInput = driver.findElement(By.cssSelector("input[id='prependedInput']"));
        loginTextInput.sendKeys(STUDENT_LOGIN);

        WebElement passwordTextInput = driver.findElement(By.cssSelector(".span2[name='_password']"));
        passwordTextInput.sendKeys(STUDENT_PASSWORD);

        WebElement loginButton = driver.findElement(By.xpath(".//button[@name='_submit']"));
        loginButton.click();
    }
//    private static void setDatepicker(WebDriver driver, String cssSelector, String date) {
//        new WebDriverWait(driver, 30000).until(
//                (WebDriver d) -> d.findElement(By.cssSelector(cssSelector)).isDisplayed());
//        JavascriptExecutor.class.cast(driver).executeScript(
//                String.format("$('%s').datepicker('setDate', '%s')", cssSelector, date));
//    }
}
