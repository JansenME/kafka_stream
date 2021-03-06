package nl.demo.kafka.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum CurrencyCode {
    EUR("EUR"),
    USD("USD"),
    BHD("BHD"),
    BRU("BRU");

    private final String currency;

    private static final List<CurrencyCode> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    CurrencyCode(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public static CurrencyCode randomCurrencyCode() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}