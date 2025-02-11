package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class HomePageFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Defaults to {@code http://localhost}.
     */
    @Value("${app.baseUrl: http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d/product/list", testBaseUrl, serverPort); // ✅ Tambahkan path yang benar!
    }

    @Test
    void pageTitle_isCorrect(ChromeDriver driver) throws Exception {
        // Exercise
        driver.get(baseUrl);
        String pageTitle = driver.getTitle();
        // Verify
        assertEquals("ADV Shop", pageTitle);
    }

    @Test
    void welcomeMessage_homePage_isCorrect(ChromeDriver driver) throws Exception {
        // Exercise
        driver.get(baseUrl);
        String welcomeMessage = driver.findElement(By.tagName("h3"))
                .getText();
        // Verify
        assertEquals("Welcome", welcomeMessage);
    }
}
