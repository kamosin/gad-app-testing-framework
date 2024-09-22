import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class BaseTest {

    WebDriver driver;

    @Test
    public void openApp() throws InterruptedException {
        driver = new ChromeDriver(new ChromeOptions());
        driver.get("http://localhost:3000");
        Thread.sleep(3000);
    }
}
