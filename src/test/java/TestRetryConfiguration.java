import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({Configuration.class})
public class TestRetryConfiguration {

    private String str = "Dem";

    private WebDriver driver;
    @BeforeClass
    @Retry(retries = 2)
    public void setUp() {
        if(!"Demo".equalsIgnoreCase(this.str)) {
            str+="o";
            System.err.println("Strings are not equal");
        }
        System.out.println("Configuration Retry worked");
    }

    @Test
    public void test() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.get("https://www.google.com");
        System.out.println(driver.getTitle());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        this.driver.quit();
    }
}
