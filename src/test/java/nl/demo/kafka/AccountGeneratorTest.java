package nl.demo.kafka;

import nl.demo.kafka.model.Account;
import org.junit.Test;

import java.util.List;

import static nl.demo.kafka.AccountGenerator.generateAccounts;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountGeneratorTest {
    @Test
    public void accountGeneratorNegativeNumber() {
        List<Account> accountList =  generateAccounts(-1);

        assertThat(accountList.size()).isEqualTo(0);
    }

    @Test
    public void accountGeneratorZeroAccounts() {
        List<Account> accountList =  generateAccounts(0);

        assertThat(accountList.size()).isEqualTo(0);
    }

    @Test
    public void accountGeneratorNormalNumber() {
        List<Account> accountList =  generateAccounts(10000);

        assertThat(accountList.size()).isEqualTo(10000);
    }
}