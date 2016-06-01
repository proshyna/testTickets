package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Java {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://booking.uz.gov.ua/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testJava() throws Exception {
    // ERROR: Caught exception [unknown command [label]]
    driver.get(baseUrl + "/");
    // КИЕВ-ТЕРНОПОЛЬ
    driver.get(baseUrl + "/");
    driver.findElement(By.name("station_from")).sendKeys("Київ");
    // ERROR: Caught exception [ERROR: Unsupported command [mouseDown | //div[@title='Київ'] | ]]
    driver.findElement(By.name("station_till")).sendKeys("Терноп");
    // ERROR: Caught exception [ERROR: Unsupported command [mouseDown | //div[@title='Тернопіль'] | ]]
    // 11.06.15
    driver.findElement(By.id("date_dep")).click();
    driver.findElement(By.linkText("10")).click();
    driver.findElement(By.name("search")).click();
    boolean kype_luboe = isElementPresent(By.xpath("//td[@class='place']/div[@title=\"Купе\"]/../..//td[@class='stations'][contains(text(),'Київ')]"));
    boolean plackart049 = isElementPresent(By.xpath("//table[@id='ts_res_tbl']//div[@title=\"Плацкарт\"]/../..//a[contains(text(),'049')]"));
    boolean plackart081 = isElementPresent(By.xpath("//table[@id='ts_res_tbl']//div[@title=\"Плацкарт\"]/../..//a[contains(text(),'081')]"));
    boolean plackart143 = isElementPresent(By.xpath("//table[@id='ts_res_tbl']//div[@title=\"Плацкарт\"]/../..//a[contains(text(),'143')]"));
    System.out.println(plackart049 + "|" + plackart081 + "|" + plackart143);
    // ERROR: Caught exception [unknown command [gotoIf]]
    // ERROR: Caught exception [unknown command [gotoIf]]
    // ERROR: Caught exception [unknown command [gotoIf]]
    boolean kype049 = isElementPresent(By.xpath("//table[@id='ts_res_tbl']//div[@title=\"Купе\"]/../..//a[contains(text(),'049')]"));
    boolean kype081 = isElementPresent(By.xpath("//table[@id='ts_res_tbl']//div[@title=\"Купе\"]/../..//a[contains(text(),'081')]"));
    boolean kype143 = isElementPresent(By.xpath("//table[@id='ts_res_tbl']//div[@title=\"Купе\"]/../..//a[contains(text(),'143')]"));
    System.out.println(kype049 + "|" + kype081 + "|" + kype143);
    // ERROR: Caught exception [unknown command [gotoIf]]
    // ERROR: Caught exception [unknown command [gotoIf]]
    // ERROR: Caught exception [unknown command [gotoIf]]
    // ERROR: Caught exception [unknown command [gotolabel]]
    // FACEBOOK
    // ERROR: Caught exception [unknown command [label]]
    // ERROR: Caught exception [ERROR: Unsupported command [captureEntirePageScreenshot | C:\Users\Masha\Desktop\scrin.png | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [captureEntirePageScreenshot | C:\Users\Masha\Dropbox\scrin.png | ]]
    driver.get("https://www.facebook.com/");
    driver.findElement(By.cssSelector("a._5afe.sortableItem > div.linkWrap.noCount > span")).click();
    driver.findElement(By.xpath("//button[@value='1']")).click();
    driver.findElement(By.cssSelector("input.inputtext.textInput")).sendKeys("мasha proshyna" + Keys.ENTER);
    driver.findElement(By.name("message_body")).clear();
    driver.findElement(By.name("message_body")).sendKeys("ЕСТЬ БИЛЕТИКИ!");
    driver.findElement(By.xpath("//input[@value='Отправить']")).click();
    // ERROR: Caught exception [unknown command [gotolabel]]
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }

}


