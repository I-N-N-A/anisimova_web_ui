package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Test2 {
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
                ".//li[@class='dropdown first']/a[@class='unclickable']" +
                        "/span[text()='Контрагенты']")).click();
        sleep(2000);
        driver.findElement(By.xpath(".//*[@class='title' and text()='Контактные лица']")).click();


        driver.findElement(By.linkText("Создать контактное лицо")).click();


        driver.findElement(By.xpath(".//div[@class='controls']" +
                "/input[@type='text']")).sendKeys("Иванов");

        driver.findElement(By.xpath(".//div[@class='controls']" +
                "/input[@name='crm_contact[firstName]']")).sendKeys("Иван");


        driver.findElement(By.xpath(".//*[@class='select2-chosen' and text()='Укажите организацию']")).click();
        sleep(2000);
        driver.findElement(By.xpath(".//div[@class='select2-search']" +
                "/input[@class='select2-input select2-focused']")).sendKeys("123test");
        sleep(2000);
        driver.findElement(By.xpath(".//span[@class='select2-match' and text()='123test']")).click();


        driver.findElement(By.xpath(".//div[@class='controls']" +
                "/input[@name='crm_contact[jobTitle]']")).sendKeys("Логист");

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
