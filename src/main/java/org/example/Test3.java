package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Test3 {

    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String STUDENT_LOGIN = "Applanatest";
    private static final String STUDENT_PASSWORD = "Student2020!";
    private static WebDriver driver;

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

        driver.findElement(By.xpath(
                "//span/div[2]/div")).click();

        driver.findElement(By.xpath(
                "//select[@name='date']")).click();
        WebElement element = driver.findElement(By.xpath("//select[@name='date']"));
        Select dropDown = new Select(element);
        dropDown.selectByVisibleText("равно");
         element.click();


        driver.findElement(By.cssSelector(
                ".filter-start-date .datepicker-input")).click();
        driver.findElement(By.cssSelector(".open > .datepicker-input")).click();





//        WebElement notifyDateHasChangedCheckbox = driver.findElement(By.name("data[dateChangeNotify]"));
//        System.out.println("Before click: " + notifyDateHasChangedCheckbox.isSelected());
//        notifyDateHasChangedCheckbox.click();
//
//        System.out.println("After click: " + notifyDateHasChangedCheckbox.isSelected());
//        driver.findElement(By.xpath("//input[@type='text'] and [text()='21.03.2021']")).click();


        driver.findElement(By.cssSelector("button[class='btn btn-success action-button']")).click();

        driver.findElement(By.xpath(".//div[@class='btn-group']" +
                "/button[@class='btn btn-success action-button']")).click();


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

}
