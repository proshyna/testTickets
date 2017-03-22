package testTicketsPackage;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class TicketsTest {
//  public static  WebDriver driver = new FirefoxDriver();

    //private static ChromeDriverService service;
    static WebDriver driver;


    @Test
    public void testTicket(){
        System.setProperty("webdriver.chrome.driver", "D:\\SeleniumTest\\chromedriver_win32\\chromedriver.exe");
       // driver = new ChromeDriver();

        filters();
        getCurrentPeriodTrains();
        searchingForRequiredTrains();
        preparingDesiredTypesOfCarraige();
        checkingPlacesAmount();

    }

    private static void filters() {  // Searching for trains with defined filters
        closingMassages();
        WebDriverWait myDynamicElement = new WebDriverWait(driver, 30);
        driver.navigate().to("http://booking.uz.gov.ua/");
        try {
            myDynamicElement.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id=\"langs\"]//li[1]//b")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :( " + e.toString());
        }

        driver.findElement(By.xpath("//ul[@id=\"langs\"]//li[1]//b")).click();
        driver.findElement(By.name("station_from")).sendKeys("Київ");
        try {
            myDynamicElement.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id=\"ui-id-1\"]/li[contains(text(),'Київ')][1]")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :( " + e.toString());
        }
      //  driver.findElement(By.name("station_from")).sendKeys(Keys.DOWN);
        driver.findElement(By.name("station_from")).sendKeys(Keys.ENTER);
        driver.findElement(By.name("station_till")).sendKeys("Тернопіль");
        try {
            myDynamicElement.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id=\"ui-id-2\"]/li[contains(text(),\"Тернопіль\")][1]")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :( " + e.toString());
        }
      //  driver.findElement(By.name("station_till")).sendKeys(Keys.DOWN);
        driver.findElement(By.name("station_till")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("date_dep")).click();
        driver.findElement(By.xpath("//td[@data-month='3']//a[text()='24']")).click();


        try {
            myDynamicElement.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//td[@data-month='3']//a[text()='24']")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :( " + e.toString());
        }

        try {
            myDynamicElement.until(ExpectedConditions.elementToBeClickable(By.name("search")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :( " + e.toString());
        }
        driver.findElement(By.name("search")).click();
        try {
            myDynamicElement.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='ts_res_tbl']//td[@class='num']//a")));
        } catch (Exception e) {
            System.out.println("Somthing wrong :( " + e.toString());
        }
    }


    private static void closingMassages(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        driver = new ChromeDriver(options);
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


    private static List<String> searchingForRequiredTrains() { // List of possible trains
        List<String> arrayOfWebElements = getCurrentPeriodTrains();
        //ArrayList creation
        List<String> requeredTrains = new ArrayList<String>();
     //   requeredTrains.add("049 К"); //Putting an Item In arraylist at Index = 0.
      //  requeredTrains.add("081 К"); //Putting an Item In arraylist at Index = 1.
       requeredTrains.add("049 К");
        requeredTrains.add("143 К");
        requeredTrains.add("081 К");
       //requeredTrains.add("357 К");





        List<String> listOfPossibleTrains = new ArrayList<String>();
        for (int i = 0; i < requeredTrains.size(); i++) {

            for (int j = 0; j < arrayOfWebElements.size(); j++) {

                if (requeredTrains.get(i).equals(arrayOfWebElements.get(j))) {
                    //System.out.println(requeredTrains.get(i));
                    listOfPossibleTrains.add(requeredTrains.get(i));
                }
            }
        }
        return listOfPossibleTrains;

    }


    private static List<String> preparingDesiredTypesOfCarraige() {
        List<String> trainsDesiredTypes = new ArrayList<String>();
        trainsDesiredTypes.add("Купе");
        trainsDesiredTypes.add("Плацкарт");





        for (int t = 0; t < trainsDesiredTypes.size(); t++) {
            //  System.out.println(trainsDesiredTypes.get(t1));
        }
        return trainsDesiredTypes;
    }


    private static Map<String, List<String>> searchForTrainsWithDesiredCarriageType() {
        List<String> trainsWithDesiredTypes = preparingDesiredTypesOfCarraige();
        List<String> requiredTrainsVar = searchingForRequiredTrains();

        Map<String, List<String>> trainsCurrentTypes = new HashMap<String, List<String>>();

        for (String desiredType : trainsWithDesiredTypes) { // all trains with desired carriage Types
            List<WebElement> checkingCarraigeTypes = driver.findElements(By.xpath("//div[@title='"
                    + desiredType + "']/../..//td[@class='num']/a"));

            for (WebElement trainWithAcceptableType : checkingCarraigeTypes) {//comparing required trains and "all trains with desired carriage Types"
                if (!requiredTrainsVar.contains(trainWithAcceptableType.getText())) {
                    continue;
                }

                if (trainsCurrentTypes.containsKey(trainWithAcceptableType.getText())) {
                    List<String> values = trainsCurrentTypes.get(trainWithAcceptableType.getText());
                    values.add(desiredType);
                } else {
                    List<String> desiredTypes = new ArrayList<String>();
                    desiredTypes.add(desiredType);
                    trainsCurrentTypes.put(trainWithAcceptableType.getText(), desiredTypes);
                }
            }
        }
        return trainsCurrentTypes;
    }

    private static void checkingPlacesAmount() {

        Map<String, List<String>> desiredCarriageTypeTrainsVar = searchForTrainsWithDesiredCarriageType();

        loop:
        for (Map.Entry<String, List<String>> entry : desiredCarriageTypeTrainsVar.entrySet()) {

            for (String carriageType : entry.getValue()) {

                int placesInt = Integer.parseInt(driver.findElement(By.xpath("//td[@class='num']/a[contains(text(),'"
                        + entry.getKey() + "')]/../..//div[@title='" + carriageType + "' ]/b")).getText());
                if (placesInt >= 2) {

                    System.out.println(entry.getKey() + " ----> " + carriageType + " = " + placesInt);

                    //TicketsTest obj = new TicketsTest();
                   // obj.screenshoting();
                    driver.quit();
//                    sendNotificationOnFacebook();
                    sendNotificationOnEmail();
                    break loop;

                }
            }
        }
    }

    public static void sendNotificationOnEmail() {
        Properties properties = getProperties();

        String username = properties.getProperty("email.username");
        String password = properties.getProperty("email.password");
        String recipientEmail = properties.getProperty("email.recipientEmail");
        String messagetitle = properties.getProperty("email.messagetitle");
        String message = properties.getProperty("email.message");
        try {
            GoogleMail.send(username, password, recipientEmail, messagetitle, message);
            System.out.println("Message sent on GMail");
        } catch (MessagingException e) {
            System.out.println("Can't send email. " + e.toString());
        }
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        FileInputStream in;
        try {
            in = new FileInputStream("config.properties");
            properties.load(in);
        } catch (IOException e) {
            System.out.println("Can't read properties " + e.toString());
        }
        return properties;
    }

    private static void sendNotificationOnFacebook() {
        // WebDriver driver = new FirefoxDriver();

        System.setProperty("webdriver.chrome.driver", "D:\\SeleniumTest\\chromedriver_win32\\chromedriver.exe");

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-extensions");
        driver = new ChromeDriver(options);

        WebDriverWait myDynamicElement = new WebDriverWait(driver, 30);
        driver.navigate().to("https://www.facebook.com/");
        try {
            myDynamicElement.until(ExpectedConditions.visibilityOfElementLocated(By.id("u_0_l")));
        } catch (Exception e) {
            System.out.println("Somthing wrong1f :(");
        }
        driver.findElement(By.id("email")).sendKeys("chizdrel@ya.ru");
        driver.findElement(By.id("pass")).sendKeys("Greenice123@");
        try {
            myDynamicElement.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@id='loginbutton']/input")));
        } catch (Exception e) {
            System.out.println("Somthing wrong1.1f :(");
        }
        driver.findElement(By.xpath("//label[@id='loginbutton']/input")).click();


        try {
            myDynamicElement.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"linkWrap noCount\"]//span[contains(text(),'Messenger')]")));
        } catch (Exception e) {
            System.out.println("Somthing wrong2f :(");
    }

        driver.findElement(By.xpath("//div[@class=\"linkWrap noCount\"]//span[contains(text(),'Messenger')]")).click();

        try {
            myDynamicElement.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title=\"Новое сообщение\"]")));
        } catch (Exception e) {
            System.out.println("Somthing wrong3f :(");
        }

        driver.findElement(By.xpath("//a[@title=\"Новое сообщение\"]")).click();

        driver.findElement(By.xpath("//div[@class=\"_2y8y _5l-3\"]/div//input[@class=\"_58al\"]")).sendKeys("Masha Proshyna");
        driver.findElement(By.xpath("//em[@data-intl-translation='Контакты']/../..//div[@class='_4ld-']")).click();
                //sendKeys(Keys.ENTER);

       /* try {
            myDynamicElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"_1mf _1mj\"]//span")));
        } catch (Exception e) {
            System.out.println("Somthing wrong4f :(");
        }

        driver.findElement(By.xpath("//div[@class=\"_1mf _1mj\"]/span/span[@data-text=\"true\"]")).sendKeys("ЕСТЬ БИЛЕТИКИ!");
        try {
            myDynamicElement.until(ExpectedConditions.elementToBeClickable(By.xpath("//em[@class=\"_4qba\"][contains(text(),\"Отправить\")]")));
        } catch (Exception e) {
            System.out.println("Somthing wrong9 :(");
        }
        driver.findElement(By.xpath("//em[@class=\"_4qba\"][contains(text(),\"Отправить\")]")).click();*/
        try {
            myDynamicElement.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='cch_f34d6ffbc75cf3a']/div/div[1]/div/ul/a")));
        } catch (Exception e) {
            System.out.println("Somthing wrong4f :(");
        }
        driver.findElement(By.xpath(".//*[@id='cch_f34d6ffbc75cf3a']/div/div[1]/div/ul/a")).click();

    }
    @AfterTest
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }


    }

    /*private static void screenshoting() { //Capture entire page screenshot and then store it to destination drive
        /*try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("C:\\Users\\Masha\\Dropbox\\screenshotUKRZALIZNUCYA.jpg"));
            System.out.print("Screenshot is captured and stored in your C:\\Users\\Masha\\Dropbox\\");
        } catch (Exception e) {
            System.out.print("Somthing wrong5 :(");
        }
        try{
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage capture = new Robot().createScreenCapture(screenRect);
        ImageIO.write(capture, "bmp", new File(args[0]));}
        catch (Exception e) {
            System.out.print("Somthing wrong5 :(");
        }*/


  /*  public void screenshoting(String fileName) throws Exception {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        ImageIO.write(image, "png", new File(fileName));
    }*/



}

