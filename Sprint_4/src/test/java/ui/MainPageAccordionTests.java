package ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class MainPageAccordionTests {
    private WebDriver driver; // Поле для хранения экземпляра WebDriver

    /** Веб-драйвер */
    private final String mainPageUrl = "https://qa-scooter.praktikum-services.ru";

    /** Порядковый номер элемента аккордеона */
    private final int numberOfElement;

    /** Ожидаемый текст в заголовке элемента аккордеона */
    private final String expectedHeaderText;

    /** Ожидаемый текст в раскрывающемся блоке элемента аккордеона */
    private final String expectedItemText;

    /**
     * Конструктор класса MainPageAccordionTests
     * @param numberOfAccordionItem Порядковый номер элемента аккордеона
     * @param expectedHeaderText Ожидаемый текст в заголовке элемента аккордеона
     * @param expectedItemText Ожидаемый текст в раскрывающемся блоке элемента аккордеона
     */
    public MainPageAccordionTests(int numberOfAccordionItem, String expectedHeaderText, String expectedItemText) {
        this.numberOfElement = numberOfAccordionItem;
        this.expectedHeaderText = expectedHeaderText;
        this.expectedItemText = expectedItemText;
    }

    @Before
    public void setup() {
        // Установка пути к драйверу Chrome
        System.setProperty("webdriver.chrome.driver", "/Users/hoodozhnik/Desktop/Практикум \"Автоматизация тестирования\"/chromedriver_mac_arm64"); // Замените на реальный путь к драйверу

        // Создание экземпляра WebDriver
        driver = new ChromeDriver();

        driver.get(mainPageUrl); // Открытие главной страницы
    }

    @Test
    public void checkAccordionIsCorrect() {
        // Ваш тестовый код для проверки аккордеона
        WebElement accordionHeader = driver.findElement(By.xpath("//xpath-вашего-элемента-заголовка"));
        accordionHeader.click();

        WebElement accordionItem = driver.findElement(By.xpath("//xpath-вашего-элемента-раскрывающегося-блока"));

        if (accordionItem.isDisplayed()) {
            assertThat("Problems with text in accordion header #" + this.numberOfElement,
                    this.expectedHeaderText,
                    equalTo(accordionHeader.getText())
            );
            assertThat("Problems with text in accordion item #" + this.numberOfElement,
                    this.expectedItemText,
                    equalTo(accordionItem.getText())
            );
        } else {
            fail("Accordion header item #" + this.numberOfElement + " didn't load");
        }
    }

    @After
    public void teardown() {
        // Закрытие браузера
        if (driver != null) {
            driver.quit();
        }
    }
}
