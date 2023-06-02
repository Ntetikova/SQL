package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.cleanDatabase;


public class BankLoginTest {

    @AfterAll
    static void teardown() {
        cleanDatabase();
    }

    @Test
    @DisplayName("Should successfully login with login and password from sut test data")
    void shouldSuccessLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.checkVerificationPage();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    void shouldBeErrorLoginWithRandomData() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.generateRandomData();
        loginPage.validLogin(authInfo);
        loginPage.checkErrorNotification();
    }

    @Test
    void shouldBeErrorNotificationWithRandomVerificationCode() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.checkVerificationPage();
        var verificationCode = DataHelper.generateRandomCode();
        verificationPage.checking(verificationCode.getCode());
        verificationPage.setErrorNotification();
    }

    @Test
    @DisplayName("Block page with tree incorrect passwords")
    void shouldBlockPageWithThreeIncorrectPasswords() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getIncorrectPassword();
        for (int i = 0; i < 3; i++) {
            loginPage.validLogin(authInfo);
            loginPage.checkErrorNotification();
            loginPage.errorButton();
            loginPage.clearFields();
        }
        loginPage.validLogin(authInfo);
        loginPage.checkErrorNotification();
    }

}
