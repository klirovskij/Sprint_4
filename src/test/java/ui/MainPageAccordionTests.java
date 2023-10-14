import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobjects.MainPage;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class MainPageAccordionTests extends BaseTest {
    private final int accordionIndex;
    private final String expectedHeaderText;
    private final String expectedItemText;

    public MainPageAccordionTests(int accordionIndex, String expectedHeaderText, String expectedItemText) {
        this.accordionIndex = accordionIndex;
        this.expectedHeaderText = expectedHeaderText;
        this.expectedItemText = expectedItemText;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        });
    }

    @Test
    public void testAccordionItems() {
        MainPage mainPage = new MainPage(this.webDriver);

        mainPage.clickOnCookieAcceptButton();

        mainPage.clickAccordionHeader(accordionIndex);
        mainPage.waitForLoadItem(accordionIndex);

        String headerText = mainPage.getAccordionHeaderText(accordionIndex);
        String itemText = mainPage.getAccordionItemText(accordionIndex);

        MatcherAssert.assertThat(
                "Problem with accordion item #" + accordionIndex + " header text",
                headerText,
                equalTo(expectedHeaderText)
        );

        MatcherAssert.assertThat(
                "Problem with accordion item #" + accordionIndex + " item text",
                itemText,
                equalTo(expectedItemText)
        );
    }
}
