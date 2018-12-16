package nl.demo.kafka.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum CurrencyCode {
    EUR("EUR"),
    USD("USD");

    private final String currencyCode;

    private static final List<CurrencyCode> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    CurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public static CurrencyCode randomCurrencyCode() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}