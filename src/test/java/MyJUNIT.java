import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyJUNIT {
    WebDriver driver;

    @BeforeAll
    public void Setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @DisplayName("Check if title is showing")
    @Test
    public void a_getTitle() {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        String titleActual = driver.getTitle();
        String titleExpected = "Practice webform for learners | Digital Unite";
        Assertions.assertEquals(titleExpected, titleActual);
    }

    @DisplayName("Check if Header title is showing")
    @Test
    public void b_getHeaderTitle() {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        boolean isExists = driver.findElement(By.cssSelector(".field.field--name-title.field--type-string.field--label-hidden")).isDisplayed();
        Assertions.assertTrue(isExists);
    }

    @DisplayName("Check menu bar by hovering cursor")
    @Test
    public void c_mouseHover() {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        Actions actions = new Actions(driver);
        WebElement about = driver.findElement(By.xpath("(//span[@class='sf-depth-1 menuparent nolink sf-with-ul'])[2]"));
        actions.moveToElement(about).perform();
    }
    @DisplayName("Check if texts under fill box showing")
    @Test
    public void d_checkFormboxTexts() {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        String nameActual = driver.findElement(By.id("edit-name--description")).getText();
        Assertions.assertTrue(nameActual.contains("This is a blank box for typing in information"));
    }

    @DisplayName("Check if form is submitted properly")
    @Test
    public void e_submitForm() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        driver.findElement(By.id("edit-name")).sendKeys("Niaz Haque");
        driver.findElement(By.id("edit-number")).sendKeys("01625453584");
        scroll(0, 500);
        driver.findElement(By.xpath("//label[normalize-space()='20-30']")).click();
        driver.findElement(By.id("edit-date")).sendKeys("10/20/2023");
        driver.findElement(By.id("edit-email")).sendKeys("niaz@test.com");
        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("I am a fresh graduate with a degree in CSE");

        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys(System.getProperty("user.dir") + "/src/test/resources/Doc_1.docx");
        scroll(0,3700);
        Thread.sleep(4000);
        driver.findElement(By.id("edit-age")).click();

        driver.findElement(By.id("edit-submit")).click();
//        Thread.sleep(2000);

        String SubmissionTitle = driver.findElement(By.className("block-page-title-block")).getText();
        Assertions.assertTrue(SubmissionTitle.contains("Thank you for your submission!"));
    }

    @DisplayName("Check social media icon")
    @Test
    public void f_checkSocialMediaIcon() {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        WebElement socialMediaIcon = driver.findElement(By.xpath("//img[@alt='Facebook logo']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(socialMediaIcon).click().perform();
    }

    public void scroll(int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(" + x + "," + y + ")");
    }

//    @AfterAll
//    public void closeDriver(){
//        driver.quit();
//    }
}

