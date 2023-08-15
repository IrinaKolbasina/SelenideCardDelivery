package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTets {

    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldRegisterDeliveryCadr() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        String currentDate = generateDate(4, "dd.mm.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[data-test-id='name'] input").setValue("Петров Леондид");
        $("[data-test-id='phone'] input").setValue("+79298184425");
        $("[data-test-id='agreement'] input").click();
        $("button.button").click();
        $("notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на" + currentDate));
    }

}


