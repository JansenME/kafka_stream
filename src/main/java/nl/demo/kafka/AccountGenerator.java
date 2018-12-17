package nl.demo.kafka;

import nl.demo.kafka.model.Account;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static nl.demo.kafka.enums.CurrencyCode.randomCurrencyCode;

@Service
public class AccountGenerator {
    public static List<Account> generateAccounts(int numberOfAccounts) {
        List<Account> accountList = new ArrayList<>();

        if (numberOfAccounts < 0) {
            return accountList;
        }

        for (int i = 0; i < numberOfAccounts; i++) {
            Iban iban = generateIban();

            accountList.add(new Account(generateBic(iban), iban.toString(), generateCurrencyCode(), generateBalance()));
        }

        return accountList;
    }

    public static Account generateAccount() {
        Iban iban = generateIban();

        return new Account(generateBic(iban), iban.toString(), generateCurrencyCode(), generateBalance());
    }

    private static Iban generateIban() {
        return Iban.random(CountryCode.NL);
    }

    private static int generateBalance() {
        return ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
    }

    private static String generateBic(Iban iban) {
        return iban.getBankCode() + iban.getCountryCode().name();
    }

    private static String generateCurrencyCode() {
        return randomCurrencyCode().getCurrencyCode();
    }
}
