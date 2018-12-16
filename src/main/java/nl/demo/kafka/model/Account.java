package nl.demo.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.StringJoiner;

@Data
@AllArgsConstructor
public class Account {
    private String bic;
    private String accountNumber;
    private String currencyCode;
    private int balance;

    public String createAccountKey() {
       return new StringJoiner(":")
                .add(this.bic)
                .add(this.accountNumber)
                .add(this.currencyCode)
                .toString();
    }
}
