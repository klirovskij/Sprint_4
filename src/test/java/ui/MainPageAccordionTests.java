package ui;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import pageObjects.MainPage;
import static org.hamcrest.CoreMatchers.equalTo;

public class MainPageAccordionTests extends BaseTest {
    @Test
    public void testAccordionItems() {
        MainPage mainPage = new MainPage(this.webDriver);

        mainPage.clickOnCookieAcceptButton();

        // Проверка основной страницы аренды самоката
        int numberOfAccordionItems = 8;

        for (int i = 0; i < numberOfAccordionItems; i++) {
            mainPage.clickAccordionHeader(i);
            mainPage.waitForLoadItem(i);

            String headerText = mainPage.getAccordionHeaderText(i);
            String itemText = mainPage.getAccordionItemText(i);

            MatcherAssert.assertThat(
                    "Problem with accordion item #" + i + " header text",
                    headerText,
                    equalTo("Expected Header Text " + i)
            );

            MatcherAssert.assertThat(
                    "Problem with accordion item #" + i + " item text",
                    itemText,
                    equalTo("Expected Item Text " + i)
            );
        }
    }
}
