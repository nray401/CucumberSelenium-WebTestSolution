package theinternet;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.*;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestSteps {
    private ChromeDriver driver;
    static String TestString;
    static WebDriverWait wait = null;

    @Before
    public void setUp() {

        // ChromeDriver path on development machine, which is Windows
        String OS = System.getProperty("os.name");
        if (OS.startsWith("Windows")) {
            System.setProperty("webdriver.chrome.driver",
                    Paths.get("src/test/resources/chromedriver/chromedriver.exe").toString());

        }

        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        if (driver!=null) {
            driver.close();
            driver.quit();
        }
    }

    @Given("I add four different products to my wishlist")
    public void iAddFourDifferentProductsToMyWishlist() {
        driver.get("https://testscriptdemo.com/");
        //Adding black pants to wishlist
        driver.findElementByLinkText("Black Pants").click();
        wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Black Pants")));

        driver.findElementByLinkText("Add to wishlist").click();

        //Adding Single Shirt to wishlist
        driver.findElementByLinkText("Single Shirt").click();
        wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Single Shirt")));

        driver.findElementByLinkText("Add to wishlist").click();

        //Adding Top pants upper to wishlist
        driver.findElementByLinkText("Top pants upper").click();
        wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Top pants upper")));

        driver.findElementByLinkText("Add to wishlist").click();

        //Adding Women’s dress to wishlist
        driver.findElementByLinkText("Women’s dress").click();
        wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Women’s dress")));

        driver.findElementByLinkText("Add to wishlist").click();

    }

    @When("I view my wishlist table")
    public void iViewMyWishListTable() {

        //Viewing the added items in wishlist
        driver.findElementByLinkText("lar la-heart").click();
        wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("lar la-heart")));


    }

    @Then("I find total four selected items in my wishlist")
    public void iFindTotalFourSelectedItemsInMyWishlist() {
        //test that we are on MY wishlist page and can see all the items added here.
        Assert.assertTrue(driver.getTitle().equals("Wishlist - TESTSCRIPTDEMO"));
        //Assert all selected products
        Assert.assertEquals(driver.findElementByLinkText("Black Pants"), "Black Pants");
        Assert.assertEquals(driver.findElementByLinkText("Single Shirt"), "Single Shirt");
        Assert.assertEquals(driver.findElementByLinkText("Top pants upper"), "Top pants upper");
        Assert.assertEquals(driver.findElementByLinkText("Women’s dress"), "Women’s dress");

    }
    @When("I search for lowest price product")
    public void iSearchForLowestPriceProduct() {
        //searching the lowest item from wishlist
        driver.findElement(By.name("s")).sendKeys("Single Shirt");
        wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Single Shirt")));

    }
    @And("I am able to add the lowest price item to my cart")
    public void iAmAbleToAddTheLowestPriceItemToMyCart() {
        //searching the lowest item from wishlist
        driver.findElement(By.name("add-to-cart")).click();
        wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("woocommerce-message")));

    }
    @Then("I am able to verify the item in my cart")
    public void iAmAbleToVerifyTheItemInMyCart() {
        //Click on cart and see if item is added then proceed to checkout enabled
        driver.findElementByLinkText("la la-shopping-bag").click();
        Assert.assertTrue(driver.getTitle().equals("Cart"));
        Assert.assertEquals(driver.findElementByLinkText("Proceed to checkout"), "Proceed to checkout");
    }
}
