package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {

    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }


    public static AuthInfo getAuthInfoTestData() {

        return new AuthInfo("vasya", "qwerty123");
    }

    public static String generateRandomLogin(){
        return faker.name().username();
    }

    private static String generateRandomPassword() {
        return faker.internet().password();
    }

    public static AuthInfo generateRandomData() {
        return new AuthInfo(generateRandomLogin(), generateRandomPassword());
    }
    public static AuthInfo getIncorrectPassword() {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        String code;

    }
    public static VerificationCode generateRandomCode() {
        return new VerificationCode(faker.numerify("#####"));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthCode {
        private String id;
        private String user_id;
        private String code;
        private String created;
    }
}