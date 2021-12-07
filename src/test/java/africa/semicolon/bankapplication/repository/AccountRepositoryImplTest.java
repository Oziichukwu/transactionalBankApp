package africa.semicolon.bankapplication.repository;

import africa.semicolon.bankapplication.data.models.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryImplTest {
    private static AccountRepository accountRepository;
    @BeforeEach
    void setUp() {
        accountRepository = new AccountRepositoryImpl();
    }

    @AfterEach
    void tearDown() {
        accountRepository = null;
    }

    @Test
    void save() {

        Account account = new Account();

        account.setAccountName("helen micheal");
        account.setAccountNumber(getAccountNumber());
        account.setAccountPassword("456ugc");
        account.setAccountBalance(570.00);

         Account savedAccount = accountRepository.save(account);
        System.out.println(getAccountNumber());

        assertEquals(1, accountRepository.getAllAccountNumber().size());

    }

    public Account accountTestInfo(){

        Account account = new Account();

        account.setAccountName("helen micheal");
        account.setAccountNumber(getAccountNumber());
        account.setAccountPassword("456ugc");
        account.setAccountBalance(570.00);

        return accountRepository.save(account);
    }

    private String getAccountNumber (){
        SecureRandom randomAccountNumber = new SecureRandom();

        String accountNumber = (10000 + randomAccountNumber.nextInt(99999)) + "" + (10000 + randomAccountNumber.nextInt(99999));
        return accountNumber;
    }

    @Test
    void findAccountByAccountNumber() {
        Account savedAccount = accountTestInfo();

        assertEquals(savedAccount, accountRepository.findAccountByAccountNumber(savedAccount.getAccountNumber()));
    }

    @Test
    void deleteAccount() {

        Account savedAccount = accountTestInfo();

        accountRepository.deleteAccount(savedAccount.getAccountNumber());

        assertEquals(0, accountRepository.getAllAccountNumber().size());
    }


    @Test
    void findAccountNumberByName() {
        Account savedAccount = accountTestInfo();

        assertEquals(savedAccount.getAccountName(), accountRepository.findAccountNumberByName(savedAccount.getAccountName()));
    }
}