package org.example;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;


import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test1
{
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
    @DisplayName("")
    @Test
    @Tag("TestMenuClick")
    public  void commonWaiterResult () {
        login();

        driver.findElement(By.xpath(
                ".//li[@class='dropdown']/a[@class='unclickable']" +
                        "/span[text()='Проекты']")).click();

        driver.findElement(By.xpath(".//*[@class='title' and text()='Мои проекты']")).click();

        driver.findElement(By.linkText("Создать проект")).click();

        driver.findElement(By.xpath(".//div[@class='controls']" +
                "/input[@type='text']")).sendKeys("Applana");

        driver.findElement(By.xpath(".//*[@class='select2-chosen' and text()='Укажите организацию']")).click();

        driver.findElement(By.xpath(".//div[@class='select2-search']" +
                "/input[@class='select2-input select2-focused']")).sendKeys("123test");

        driver.findElement(By.xpath(".//span[@class='select2-match' and text()='123test']")).click();
        WebElement org1=driver.findElement(By.xpath("//div[@id='select2-drop']/div/input"));
        String org2 = org1.getAttribute("value");
        assertEquals("123test", org2);

        driver.findElement(By.xpath(".//select[@name='crm_project[businessUnit]']" +
                "/option[text()='Research & Development']")).click();
        WebElement dep=driver.findElement(By.xpath(".//select[@name='crm_project[businessUnit]']"));
        String dep2 = dep.getText();
        Assertions.assertTrue(dep2.contains("Research & Development"));

        driver.findElement(By.xpath(".//select[@name='crm_project[curator]']" +
                "/option[text()='Карпов Руслан']")).click();
        WebElement name3=driver.findElement(By.xpath(".//select[@name='crm_project[curator]']"));
        String name4 = name3.getText();
        Assertions.assertTrue(name4.contains("Карпов Руслан"));

        driver.findElement(By.xpath(".//select[@name='crm_project[rp]']" +
                "/option[text()='Караев Сергей']")).click();
        WebElement name5=driver.findElement(By.xpath(".//select[@name='crm_project[rp]']"));
        String name6 = name5.getText();
        Assertions.assertTrue(name6.contains("Караев Сергей"));

        driver.findElement(By.xpath(".//select[@name='crm_project[manager]']" +
                "/option[text()='Козлова Светлана']")).click();

        WebElement click1 = driver.findElement(By.xpath("//div[4]/button"));
        WebElement click2 = driver.findElement(By.xpath("//li/a"));
        WebElement click3 = driver.findElement(By.cssSelector("#user-menu > .dropdown-toggle"));
        Actions builder1 = new Actions(driver);
        builder1
                .click(click1)
                .pause(5000)
                .click(click2)
                .pause(5000)
                .click(click3)
                .build()
                .perform();

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 5000);");
        driver.close();
        driver.quit();


        driver.close();
        driver.quit();
    }

    @DisplayName("Mylogin1")
    private static void login() {
        driver.get(LOGIN_PAGE_URL);

        WebElement loginTextInput = driver.findElement(By.cssSelector("input[id='prependedInput']"));
        loginTextInput.sendKeys(STUDENT_LOGIN);
        String user = loginTextInput.getAttribute("value");
        assertEquals(STUDENT_LOGIN,user);

        WebElement passwordTextInput = driver.findElement(By.cssSelector(".span2[name='_password']"));
        passwordTextInput.sendKeys(STUDENT_PASSWORD);
        String password = passwordTextInput.getAttribute("value");
        assertEquals(STUDENT_PASSWORD, password);

        WebElement loginButton = driver.findElement(By.xpath(".//button[@name='_submit']"));
        loginButton.click();
    }
}