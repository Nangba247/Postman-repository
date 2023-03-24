import org.apache.velocity.tools.view.VelocityLayoutServlet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
             PROCESS FLOW
             We want to automate the ordering flow of Konga as an existing customer following the steps described below
             - Visit the URL Konga
             - Sign in to Konga Successfully
             - From the Categories, click on the “Computers and Accessories”
             - Click on the Laptop SubCategory
             - Click on the Apple MacBooks
             - Add an item to the cart
             - Select Address
             - Continue to make payment
             - Select a Card Payment Method
             - Input invalid card details
             - Print Out the error message: Invalid card number
             - Close the iFrame that displays the input card Modal
             - Quit the browser.
             */
public class KongaTest {
    private WebDriver driver;
    private By modalBtn = By.xpath(".//*[@id=\"channel-template\"]/div[2]/div/div[2]/button");
    private By cardNumberField = By.xpath("//*[@id='card-number']");
    private By dateField = By.id("expiry");
    private By cvvField = By.id("cvv");
    private By invalidMessage = By.xpath("//*[@id='card-number_unhappy']");
    private By checkOut_iFrame = By.xpath("//*[@id='kpg-frame-component']");
    private By cardButton;

    @BeforeTest
    public void start() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");

        driver = new ChromeDriver(chromeOptions);
        // Test 1 Visit the URL Konga
        driver.get("https://konga.com");
        //Maximize screen
        driver.manage().window().maximize();
        Thread.sleep(4000);
        //click sign in button
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/a")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 0)
    public void SignIn() throws InterruptedException {
        //Test 2 Sign in to Konga Successfully
        //Locate the email field and Input your email on the username field
        driver.findElement(By.id("username")).sendKeys("victornangba200@gmail.com");
        //  Locate the password field and Input your password on the username field
        driver.findElement(By.id("password")).sendKeys("Admin247");
        // Click on the signin button
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
        Thread.sleep(6000);

    }

    @Test(priority = 1)
    public void categories() throws InterruptedException {

        //Test3 From the Categories, click on the “Computers and Accessories”
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[2]/div/a[2]")).click();
        Thread.sleep(5000);
        //Test4 Click on the Laptop SubCategory
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a/label/span")).click();
        Thread.sleep(6000);
        //Test5 Click on the Apple MacBooks
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a/ul/li[1]/a/label/span")).click();
        Thread.sleep(7000);
        //Test6 Add an item to the cart
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/section/section/section/ul/li[1]/div/div/div[2]/form/div[3]/button")).click();
        Thread.sleep(5000);

    }

    @Test(priority = 2)
    public void AddAddress() throws InterruptedException {

        // click on my cart button
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/a[2]/span[1]")).click();
        Thread.sleep(5000);
        //click on checkout button
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[3]/section/section/aside/div[3]/div/div[2]/button")).click();
        Thread.sleep(10000);
    //Test7 Select address
         driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[2]/div[3]/div[1]/input")).click();
         Thread.sleep(5000);
         //click on pickup address
         driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[2]/div[3]/div[2]/div[1]/div/button")).click();
         Thread.sleep(5000);
         //choose ikeja
         driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[2]/div/div/div[2]/div[1]/form/button")).click();
         Thread.sleep(5000);
         //click use address
         driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[3]/div/div/div/a")).click();
         Thread.sleep(5000);
         
    }

    @Test(priority = 3)
    public void PaymentMethod() throws InterruptedException {
        //click on pay now
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[1]/div[1]/span/input")).click();
        Thread.sleep(3000);
        //Test 8 click on continue payment
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[3]/div[2]/div/button")).click();
        Thread.sleep(10000);
    }
    @Test(priority = 4)
    //Test 9 Select a card payment method
    public void clickOnPaymentType() throws InterruptedException {
        WebElement checkOutFrame = this.driver.findElement(this.checkOut_iFrame);
        this.driver.switchTo().frame(checkOutFrame);
        this.driver.findElement(this.modalBtn).click();
        Thread.sleep(3000L);
    }


@Test (priority = 5)
    public void CardDetails () throws InterruptedException {
        //Test 10 input invalid card details
        //Locate the card number field and input card number
        driver.findElement(By.id("card-number")).sendKeys("8765987645672364");
        //Locate the password field and Input your password on the username field
       driver.findElement(By.id("expiry")).sendKeys("0426");
        //input cvv
        driver.findElement(By.id("cvv")).sendKeys("537");
        Thread.sleep(5000);
        // Click on the pay mow button
        driver.findElement(By.xpath("//*[@id=\"validateCardForm\"]")).click();
        Thread.sleep(5000);

        //Test 11 Print Out the error message: Invalid card number
        System.out.println("Invalid Card Number");
        }

    @AfterTest
    public void closing() throws InterruptedException {
        //Test 12 Close the iFrame that displays the input card Modal
        driver.findElement(By.xpath("/html/body/section/section/section/div[2]/div[1]/aside")).click();
        Thread.sleep(5000);
        //Test 13 Quit the browser
        driver.quit();

    }
}
