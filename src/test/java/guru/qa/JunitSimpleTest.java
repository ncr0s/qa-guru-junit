package guru.qa;

import com.codeborne.selenide.SelenideElement;
import guru.qa.data.Locale;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class JunitSimpleTest {

    SelenideElement rejectCookies = $$("button").findBy(text("Reject all"));

    @Disabled("JIRA-1312")
    @DisplayName("Demo test")
    @Test
    void simpleTest() {
        Assertions.assertTrue(true);
    }

    @CsvSource({
            "allure testops, https://qameta.io",
            "selenide, https://selenide.org"
    })
/*
other variant:
    @CsvFileSource(resources = "/testData.csv")
*/
    @DisplayName("selenide.org should be find via google search")
    @ParameterizedTest(name = "url {1} should be finde in google with {2} request")
    @Tags({@Tag("UI_TEST"), @Tag("BLOCKER")})
    void successfulSearchSelenideTest(
            String productName,
            String productUrl
    ) {
        open("https://www.google.com/");
        if (rejectCookies.isDisplayed()) {
            rejectCookies.click();
        }
        $("[name=q]").setValue(productName).pressEnter();
        $("[id=search]").shouldHave(text(productUrl));
    }
}
