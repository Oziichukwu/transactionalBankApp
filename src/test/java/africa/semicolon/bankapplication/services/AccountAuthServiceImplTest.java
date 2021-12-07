package africa.semicolon.bankapplication.services;

import africa.semicolon.bankapplication.data.dtos.request.RegisterAccountRequest;
import africa.semicolon.bankapplication.data.dtos.response.RegisterAccountResponse;
import africa.semicolon.bankapplication.data.exceptions.AccountAlreadyExistException;
import africa.semicolon.bankapplication.data.exceptions.InsufficientInitialDepositException;
import africa.semicolon.bankapplication.data.exceptions.InvalidAccountNameException;
import africa.semicolon.bankapplication.data.models.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;

class AccountAuthServiceImplTest {
    private static AccountAuthService accountAuthService;

    @BeforeEach
    void setUp() {
        accountAuthService = new AccountAuthServiceImpl();
    }

    @AfterEach
    void tearDown() {
        accountAuthService = null;
    }

    @Test
    void registerAccount() {
        RegisterAccountRequest registerAccountRequest = new RegisterAccountRequest();

        registerAccountRequest.setAccountName("micheal ukaegbu");
        registerAccountRequest.setAccountPassword("456ugc");
        registerAccountRequest.setInitialDeposit(600.00);

        Account account = new Account();
        account.setAccountNumber(accountNumberGenerator());

        accountAuthService.registerAccount(registerAccountRequest);

        assertEquals(1, accountAuthService.findAllAccounts().size());
    }

    private RegisterAccountResponse registerAccountTest() {
        RegisterAccountRequest registerAccountRequest = new RegisterAccountRequest();

        registerAccountRequest.setAccountName("micheal ukaegbu");
        registerAccountRequest.setAccountPassword("456ugc");
        registerAccountRequest.setInitialDeposit(600.00);

        return accountAuthService.registerAccount(registerAccountRequest);
    }

    private String accountNumberGenerator() {
        SecureRandom mySecureRandom = new SecureRandom();
        String accountNumber = (10000 + mySecureRandom.nextInt(99999)) + "" + (10000 + mySecureRandom.nextInt(99999));
        return accountNumber;
    }

    @Test
    void throwsExceptionWhenAccountNameAlreadyExist() {
        registerAccountTest();

        assertThrows(AccountAlreadyExistException.class, () -> registerAccountTest());
    }


    @Test
    void throwsExceptionWhenInitialDepositIsBelow500() {
        assertThrows(InsufficientInitialDepositException.class, () -> registerAccountTestForInsufficientBalance());
    }

    private RegisterAccountResponse registerAccountTestForInsufficientBalance() {
        RegisterAccountRequest registerAccountRequest = new RegisterAccountRequest();

        registerAccountRequest.setAccountName("micheal ukaegbu");
        registerAccountRequest.setAccountPassword("456ugc");
        registerAccountRequest.setInitialDeposit(300.00);

        return accountAuthService.registerAccount(registerAccountRequest);
    }

    @Test
    void accountNumberIsExactlyOf10InLength(){
        RegisterAccountRequest registerAccountRequest = new RegisterAccountRequest();

        registerAccountRequest.setAccountName("miiheal ukaugbu");
        registerAccountRequest.setAccountPassword("456ugc");
        registerAccountRequest.setInitialDeposit(600.00);

        Account account = new Account();
        account.setAccountNumber(accountNumberGenerator());

        assertNotNull(account.getAccountNumber());
        System.out.println(accountNumberGenerator());

    }


}