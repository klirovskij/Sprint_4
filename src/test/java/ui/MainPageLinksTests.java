import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.MainPage;

import static org.junit.Assert.assertTrue;

public class MainPageLinksTests {
    private WebDriver webDriver;

    private final String mainPageUrl = "https://qa-scooter.praktikum-services.ru";
    private final String yandexUrl = "//yandex.ru";
    private final String scooterUrl = "//qa-scooter.praktikum-services.ru";

    @Before
    public void setUp() {
        // Установка пути к драйверу Chrome
        System.setProperty("webdriver.chrome.driver", "/Users/hoodozhnik/Desktop/Практикум \"Автоматизация тестирования\"/chromedriver_mac_arm64"); // Замените на реальный путь к драйверу

        // Создание экземпляра WebDriver
        this.webDriver = new ChromeDriver();
        this.webDriver.get(this.mainPageUrl);
    }

    @After
    public void tearDown() {
        this.webDriver.quit();
    }

    @Test
    public void checkYandexLinkIsCorrect() {
        MainPage mainPage = new MainPage(this.webDriver);

        assertTrue(
                "Yandex Logo Link doesn't go to " + this.yandexUrl,
                mainPage.getYandexLogoLink().contains(this.yandexUrl)
        );

        assertTrue(
                "Yandex Logo Link doesn't open in a new tab",
                mainPage.isYandexLogoLinkOpenedInNewTab()
        );
    }

    @Test
    public void checkScooterLinkIsCorrect() {
        MainPage mainPage = new MainPage(this.webDriver);

        assertTrue(
                "Scooter Logo Link doesn't go to " + this.scooterUrl,
                mainPage.getScooterLogoLink().contains(this.scooterUrl)
        );
    }
}
