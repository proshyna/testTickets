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
    // ТЕРНОПОЛЬ-КИЕВ
    driver.get(baseUrl + "/");
    driver.findElement(By.name("station_from")).sendKeys("Терноп");
    // ERROR: Caught exception [ERROR: Unsupported command [mouseDown | //div[@title='Тернопіль'] | ]]
    driver.findElement(By.name("station_till")).sendKeys("Київ");
    // ERROR: Caught exception [ERROR: Unsupported command [mouseDown | //div[@title='Київ'] | ]]
    // 02.05.16
    driver.findElement(By.id("date_dep")).click();
    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div[2]/table/tbody/tr[2]/td")).click();
    driver.findElement(By.name("search")).click();
    boolean ivano = isElementPresent(By.xpath("//td[contains(text(),'Івано')]"));
    boolean rahiv_k = isElementPresent(By.xpath("//td[contains(text(),'Рахів')]/..//td[@class='place']//div[@title=\"Купе\"]"));
    boolean rahiv_p = isElementPresent(By.xpath("//td[contains(text(),'Рахів')]/..//td[@class='place']//div[@title=\"Купе\"]"));
    boolean lviv_k = isElementPresent(By.xpath("//td[contains(text(),'Львів')]/..//td[@class='place']//div[@title=\"Купе\"]"));
    boolean lviv_p = isElementPresent(By.xpath("//td[contains(text(),'Львів')]/..//td[@class='place']//div[@title=\"Плацкарт\"]"));
    System.out.println(ivano + "|" + rahiv_k + "|" + rahiv_p + "|" + lviv_k + "|" + lviv_p);
    // ERROR: Caught exception [unknown command [gotoIf]]
    // ERROR: Caught exception [unknown command [gotoIf]]
    // ERROR: Caught exception [unknown command [gotoIf]]
    // ERROR: Caught exception [unknown command [gotoIf]]
    // ERROR: Caught exception [unknown command [gotoIf]]
    // 03.05.16
    driver.findElement(By.id("date_dep")).click();
    driver.findElement(By.linkText("3")).click();
    driver.findElement(By.name("search")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [isTextPresent | //td[contains(text(),'Ужгород')]/..//td[@class='place']//div[@title="Плацкарт"] | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [isTextPresent | //td[contains(text(),'Ужгород')]/..//td[@class='place']//div[@title="Купе"] | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [isTextPresent | //td[contains(text(),'Трускавець')]/..//td[@class='place']//div[@title="Плацкарт"] | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [isTextPresent | //td[contains(text(),'Трускавець')]/..//td[@class='place']//div[@title="Купе"] | ]]
    System.out.println(yzgorod_p + "|" + yzgorod_k + "|" + tryskavec_p + "|" + tryskavec_k);
    // ERROR: Caught exception [unknown command [gotoIf]]
    // ERROR: Caught exception [unknown command [gotoIf]]
    // ERROR: Caught exception [unknown command [gotoIf]]
    // ERROR: Caught exception [unknown command [gotoIf]]
    // КИЕВ-ТЕРНОПОЛЬ
    driver.get(baseUrl + "/");
    driver.findElement(By.name("station_from")).sendKeys("Київ");
    // ERROR: Caught exception [ERROR: Unsupported command [mouseDown | //div[@title='Київ'] | ]]
    driver.findElement(By.name("station_till")).sendKeys("Терноп");
    // ERROR: Caught exception [ERROR: Unsupported command [mouseDown | //div[@title='Тернопіль'] | ]]
    // 29.04.16
    driver.findElement(By.id("date_dep")).click();
    driver.findElement(By.linkText("29")).click();
    driver.findElement(By.name("search")).click();
    boolean kype_luboe = isElementPresent(By.xpath("//td[@class='place']/div[@title=\"Купе\"]/../..//td[@class='stations'][contains(text(),'Київ')]"));
    boolean placksrt_luboy = isElementPresent(By.xpath("//td[@class='place']/div[@title=\"Плацкарт\"]/../..//td[@class='stations'][contains(text(),'Київ')]"));
    System.out.println(kype_luboe + "|" + placksrt_luboy);
    // ERROR: Caught exception [unknown command [gotoIf]]
    // ERROR: Caught exception [unknown command [gotoIf]]
    // 30.04.16
    driver.findElement(By.id("date_dep")).click();
    driver.findElement(By.linkText("30")).click();
    driver.findElement(By.name("search")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [isTextPresent | //td[@class='place']/div[@title="Купе"] | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [isTextPresent | //td[@class='place']/div[@title="Плацкарт"] | ]]
    System.out.println(kype_luboe + "|" + placksrt_luboy);
    // ERROR: Caught exception [unknown command [gotoIf]]
    // ERROR: Caught exception [unknown command [gotoIf]]
    // ERROR: Caught exception [unknown command [gotolabel]]
    // FACEBOOK
    // ERROR: Caught exception [unknown command [label]]
    driver.get("https://www.facebook.com/");
    driver.findElement(By.cssSelector("a._5afe.sortableItem > div.linkWrap.noCount > span")).click();
    driver.findElement(By.xpath("//button[@value='1']")).click();
    driver.findElement(By.cssSelector("input.inputtext.textInput")).sendKeys("Київ" + Keys.ENTER);
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
