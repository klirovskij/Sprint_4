package ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageObjects.MainPage;
import pageObjects.OrderPage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@Parameterized.Parameters
public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
        {"Клава", "Птичкина", "Москва, ул. Дорожная, д. 12, кв. 34", "Сокол", "81234567890", "01.05.2023", "четверо суток", "чёрный жемчуг", "Коммент!"},
        {"Иван", "Петров", "Москва, ул. Скобелевская, д. 26, кв. 1", "Улица Скобелевская", "89876543210", "21.05.2023", "трое суток", "серая безысходность", "Привезите в первой половине дня"},
        });
        }

@RunWith(Parameterized.class)
public class OrderPageTests extends BaseTest {
    private final String name, surname, address, metro, phone, date, term, color, comment;
    private final String expectedOrderSuccessText = "Заказ оформлен";

    public OrderPageTests(
            String name,
            String surname,
            String address,
            String metro,
            String phone,
            String date,
            String term,
            String color,
            String comment
    ) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.term = term;
        this.color = color;
        this.comment = comment;
    }

    @Test
    public void orderWithHeaderButtonWhenSuccess() {
        MainPage mainPage = new MainPage(this.webDriver);
        OrderPage orderPage = new OrderPage(this.webDriver);

        mainPage.clickOnCookieAcceptButton();
        mainPage.clickOrderButtonHeader();
        makeOrder(orderPage);

        assertTrue("Order form is not displayed", orderPage.isOrderFormDisplayed());
    }

    @Test
    public void orderWithBodyButtonWhenSuccess() {
        MainPage mainPage = new MainPage(this.webDriver);
        OrderPage orderPage = new OrderPage(this.webDriver);

        mainPage.clickOnCookieAcceptButton();
        mainPage.clickOrderButtonBody();
        orderPage.waitForLoadForm();

        makeOrder(orderPage);

        assertTrue("Order form is not displayed", orderPage.isOrderFormDisplayed());
    }

    @Test
    public void orderFormIsDisplayed() {
        MainPage mainPage = new MainPage(this.webDriver);
        OrderPage orderPage = new OrderPage(this.webDriver);

        mainPage.clickOnCookieAcceptButton();
        mainPage.clickOrderButtonHeader();

        assertTrue("Order form is not displayed", orderPage.isOrderFormDisplayed());
    }

    private void makeOrder(OrderPage orderPage) {
        orderPage.setName(this.name);
        orderPage.setSurname(this.surname);
        orderPage.setAddress(this.address);
        orderPage.setMetro(this.metro);
        orderPage.setPhone(this.phone);

        orderPage.clickNextButton();

        orderPage.setDate(this.date);
        orderPage.setTerm(this.term);
        orderPage.setColor(this.color);
        orderPage.setComment(this.comment);

        orderPage.makeOrder();
    }

}
