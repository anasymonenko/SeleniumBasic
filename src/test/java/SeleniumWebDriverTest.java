import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWebDriverTest {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.bookdepository.com/");
        driver.manage().window().maximize();

        WebElement logo = driver.findElement(By.xpath("//a[@href = '/' and @class = 'brand-link']/img[@alt='Bookdepository.com']"));
        Assert.assertTrue("Logo is not displayed", logo.isDisplayed());

        WebElement signInIcon = driver.findElement(By.xpath("//a[contains(@href,'account/login/to/account')]"));
        Assert.assertTrue("Sign in Register icon is not displayed", signInIcon.isDisplayed());

        WebElement navigationMenu = driver.findElement(By.xpath("//div[@class = 'secondary-header']//li[@class = 'tbd-dropdown category-dropdown mob-nav-shop dropdown']"));
        Assert.assertTrue("Navigation Menu is not displayed", navigationMenu.isDisplayed());

        WebElement banner = driver.findElement(By.xpath("//img[@alt = 'Books on Screen']"));
        Assert.assertTrue("Banner is not displayed", banner.isDisplayed());

        WebElement cartIcon = driver.findElement(By.xpath("//div[@class = 'right-section']//i[@class = 'icon-basket']"));
        Assert.assertTrue("Cart icon is not displayed",cartIcon.isDisplayed());

        WebElement searchField = driver.findElement(By.xpath("//input[@name = 'searchTerm']"));
        searchField.sendKeys("Ugly Love");

        WebElement searchButton = driver.findElement(By.xpath("//span[text()='Search']"));
        searchButton.click();

        WebElement addToBasket = driver.findElement
                (By.xpath("//div[@class = 'btn-wrap']/a[@href = '/basket/addisbn/isbn13/9781471136726']"));
        addToBasket.click();

        new WebDriverWait(driver, 120)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[text()='Item added to your basket']")));

        WebElement continueShoppingButton = driver.findElement(By.xpath("//div[@class = 'modal-dialog modal-md']" +
                "//a[@class = 'btn btn-grey pull-left continue-shopping string-to-localize']"));
        continueShoppingButton.click();

        WebElement itemCounter = driver.findElement(By.xpath("//div[@class = 'right-section']//span[@class = 'item-count']"));
        Assert.assertEquals("The value of the item counter is incorrect", 1,
                (Integer.parseInt(itemCounter.getText().trim())));

        WebElement bookName = driver.findElement(By.xpath("//a[ contains(text(), 'Ugly Love')]"));
        bookName.click();

        driver.quit();
    }
}
