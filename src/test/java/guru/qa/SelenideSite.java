package guru.qa;

import guru.qa.data.Locale;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

import java.util.List;
import java.util.stream.Stream;

public class SelenideSite {

    static Stream<Arguments> selenideSiteDataProvider() {
        return Stream.of(
                Arguments.of(Locale.EN, List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes")),
                Arguments.of(Locale.RU, List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы", ""))
        );
    }

    @MethodSource("selenideSiteDataProvider")
    @ParameterizedTest(name = "For locale {0}")
    void selenideShouldContainAllOfButtonsForDifferentLocales(Locale locale, List<String> buttons) {
        open("https://selenide.org");
        $$("#languages a").find(text(locale.name())).click();
        $$(".main-menu-pages a").shouldHave(texts(buttons));
    }
}
