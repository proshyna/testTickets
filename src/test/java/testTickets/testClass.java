package testTickets;


import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.awt.SystemColor.text;
import static org.junit.Assert.assertEquals;


@Test
public class testClass {

    public static WebDriver driver = new FirefoxDriver();

    public static void main(String[] args) {

        filters();
        getCurrentPeriodTrains();
        requiredTrains();
       checkCarriageType();

    }


    public static void filters() {  // Searching for trains with defined filters

        WebDriverWait myDynamicElement = new WebDriverWait(driver, 30);
        driver.navigate().to("http://booking.uz.gov.ua/");
        driver.findElement(By.xpath("//ul[@id=\"langs\"]//li[1]//b")).click();
        driver.findElement(By.name("station_from")).sendKeys("Київ");
        driver.findElement(By.name("station_from")).sendKeys(Keys.DOWN);
        driver.findElement(By.name("station_from")).sendKeys(Keys.ENTER);
        driver.findElement(By.name("station_till")).sendKeys("Тернопіль");
        driver.findElement(By.name("station_till")).sendKeys(Keys.DOWN);
        driver.findElement(By.name("station_till")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("date_dep")).click();
        driver.findElement(By.linkText("14")).click();
        try {
            myDynamicElement.until(ExpectedConditions.elementToBeClickable(By.name("search")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :(");
        }
        driver.findElement(By.name("search")).click();

        try {
            myDynamicElement.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='ts_res_tbl']//td[@class='num']//a")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :(");
        }
    }


    private static List<String> getCurrentPeriodTrains() { // Getting WebElements and writing them to ArrayList
        List<String> currentTrains = new ArrayList<String>();
        List<WebElement> myElements = driver.findElements(By.xpath("//table[@id='ts_res_tbl']//td[@class='num']//a"));

        for (int i = 0; i < myElements.size(); i++) {
            currentTrains.add(myElements.get(i).getText());
            // System.out.println(myElements.get(i).getText());
        }
        return currentTrains;
    }


    public static List<String> requiredTrains() { // List of possible trains
        List<String> arrayOfWebElements = getCurrentPeriodTrains();
        //ArrayList creation
        List<String> requeredTrains = new ArrayList<String>();
        requeredTrains.add("049 К"); //Putting an Item In arraylist at Index = 0.
        requeredTrains.add("081 К"); //Putting an Item In arraylist at Index = 1.
        requeredTrains.add("143 К");

        List<String> listOfPossibleTrains = new ArrayList<String>();
        for (int i = 0; i < requeredTrains.size(); i++) {

            for (int j = 0; j < arrayOfWebElements.size(); j++) {

                if (requeredTrains.get(i).equals(arrayOfWebElements.get(j))) {
                    //System.out.println(requeredTrains.get(i));
                    listOfPossibleTrains.add(requeredTrains.get(i));
                }
            }
        }return  listOfPossibleTrains;

    }


    public static List<String> checkCarriageType() {
        List<String> checkType = requiredTrains();


        List<String> trainsMatchingBeforeTypeChecking = new ArrayList<String>();
        for (int i = 0; i < checkType.size(); i++) {

            System.out.println(checkType.get(i));

            List<WebElement> carriageTypeKype = driver.findElements(By.xpath("//table[@id='ts_res_tbl']//td[@class='num']//a[contains(text(),'" + trainsMatchingBeforeTypeChecking.get(i) + "')]/../..//td[@class='place']//i"));



            // String type = driver.findElements(By.xpath("//td[@class=\"place\"]/div[contains(text(),\"Купе\")]"));


            //List<WebElement> plackart = driver.findElements(By.xpath("//td[@class=\"place\"]/div"));


            //    List<String> type = new ArrayList<String>();
            //   List<WebElement> carriageType = driver.findElements(By.xpath("//table[@id='ts_res_tbl']//td[@class='num']//a[contains(text(),'" + listOfPossibleTrains.get(i) + "')]/../..//td[@class='place']//i"));


            //    type.add(carriageType.get(i).getText());
            //  System.out.println(carriageType.get(i).getText());


        /*
            if(train)
            String value = listOfPossibleTrains.get(i);
            System.out.println(value);

            try {
                assertEquals("Log in and get to work", driver.(By.xpath("//table[@id='ts_res_tbl']//td[@class='num']//a[contains(text(),'" + listOfPossibleTrains.get(i) + "';)]")).getText();
            } catch (Exception e) {
                throw new MyFirstTest.TestError("FAIL!'Log in and get to work' IS NOT FUND"); // Вызов ошибки о невыполненом условии(для уведомления по email)
                //verificationErrors.append("FAIL!'Log in and get to work' IS NOT FUND");
            }*/

        }
        return checkType;
    }
}







       // for(int i=0; i<trains.length; i++){
         //   System.out.println(trains[i]);
      //  }


     /*   Boolean plackart049 = driver.findElements(By.xpath("//table[@id='ts_res_tbl']//div[@title=\"Плацкарт\"]/../..//a[contains(text(),'049')]")).size() != 0;
        if (plackart049) {
            String places_sring = driver.findElement(By.xpath("//table[@id='ts_res_tbl']//div[@title=\"Плацкарт\"]/../..//a[contains(text(),'049')]")).getText();
            int placesAmount = Integer.parseInt(places_sring);
            if (placesAmount > 1) {
                facebook();
            } else { System.out.println("nice");
            }
        }


        Boolean plackart081 = driver.findElements(By.xpath("//table[@id='ts_res_tbl']//div[@title=\"Плацкарт\"]/../..//a[contains(text(),'049')]")).size() != 0;
        if (plackart081) {
            facebook();
        } else {
        }

        Boolean plackart143 = driver.findElements(By.xpath("//table[@id='ts_res_tbl']//div[@title=\"Плацкарт\"]/../..//a[contains(text(),'049')]")).size() != 0;
        if (plackart143) {
            facebook();
        } else {
        }

        Boolean kype049 = driver.findElements(By.xpath("//table[@id='ts_res_tbl']//div[@title=\"Плацкарт\"]/../..//a[contains(text(),'049')]")).size() != 0;
        if (kype049) {
            facebook();
        } else {
        }

        Boolean kype081 = driver.findElements(By.xpath("//table[@id='ts_res_tbl']//div[@title=\"Плацкарт\"]/../..//a[contains(text(),'049')]")).size() != 0;
        if (kype081) {
            facebook();
        } else {
        }

        Boolean kype143 = driver.findElements(By.xpath("//table[@id='ts_res_tbl']//div[@title=\"Плацкарт\"]/../..//a[contains(text(),'049')]")).size() != 0;
        if (kype143) {
            facebook();
        } else {
        }
    }



    public static void facebook() {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait myDynamicElement = new WebDriverWait(driver, 30);
        driver.navigate().to("https://www.facebook.com/");
        try {
            myDynamicElement.until(ExpectedConditions.visibilityOfElementLocated(By.id("u_0_j")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :(");
        }
        driver.findElement(By.id("email")).sendKeys("chizdrel@ya.ru");
        driver.findElement(By.id("pass")).sendKeys("Greenice123@");
        driver.findElement(By.id("u_0_m")).click();
        try {
            myDynamicElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"linkWrap noCount\"]//span")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :(");
        }
        driver.findElement(By.xpath("//a[@class=\"_5afe sortableItem\"]//div[@class=\"linkWrap noCount\"]//span")).click();

        try {
            myDynamicElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@value=\"1\"]")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :(");
        }

        driver.findElement(By.xpath("//button[@value='1']")).click();

        try {
            myDynamicElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"_1rt\"]//textarea//span")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :(");
        }

        driver.findElement(By.xpath("//div[@class=\"_1rt\"]//textarea")).sendKeys("ЕСТЬ БИЛЕТИКИ!");
        driver.findElement(By.id("u_jsonp_2_6")).click();
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}



*/