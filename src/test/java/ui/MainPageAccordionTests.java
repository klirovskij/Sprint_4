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
                {0, "Expected Header Text 0", "Expected Item Text 0"},
                {1, "Expected Header Text 1", "Expected Item Text 1"},
                {2, "Expected Header Text 2", "Expected Item Text 2"},
                {3, "Expected Header Text 3", "Expected Item Text 3"},
                {4, "Expected Header Text 4", "Expected Item Text 4"},
                {5, "Expected Header Text 5", "Expected Item Text 5"},
                {6, "Expected Header Text 6", "Expected Item Text 6"},
                {7, "Expected Header Text 7", "Expected Item Text 7"}
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
