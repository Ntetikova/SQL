package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");

    public void checkVerificationPage() {
        codeField.shouldBe(visible);
    }

    public void setErrorNotification() {
        errorNotification.shouldBe(visible);
    }

    public DashboardPage validVerify(String verificationCode) {
        checking(verificationCode);
       return new DashboardPage();
    }

    public void checking(String verificationCode) {
        codeField.setValue(verificationCode);
        verifyButton.click();
    }
}