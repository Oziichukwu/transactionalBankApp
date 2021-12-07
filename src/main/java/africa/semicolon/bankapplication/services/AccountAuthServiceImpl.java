package africa.semicolon.bankapplication.services;

import africa.semicolon.bankapplication.data.dtos.request.RegisterAccountRequest;
import africa.semicolon.bankapplication.data.dtos.response.RegisterAccountResponse;
import africa.semicolon.bankapplication.data.exceptions.AccountAlreadyExistException;
import africa.semicolon.bankapplication.data.exceptions.InsufficientInitialDepositException;
import africa.semicolon.bankapplication.data.exceptions.InvalidAccountNameException;
import africa.semicolon.bankapplication.data.models.Account;
import africa.semicolon.bankapplication.repository.AccountRepository;
import africa.semicolon.bankapplication.repository.AccountRepositoryImpl;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class AccountAuthServiceImpl implements AccountAuthService{


    private static final AccountRepository accountRepository = new AccountRepositoryImpl();
    @Override
    public RegisterAccountResponse registerAccount(RegisterAccountRequest registerAccountRequest) {

        if (registerAccountRequest.getAccountName() == null || registerAccountRequest.getAccountName().isEmpty()){
            throw new InvalidAccountNameException("Account Name must not be empty");
        }

        if (registerAccountRequest.getInitialDeposit() < 500.00){
            throw new InsufficientInitialDepositException("Initial deposit must not be less than 500.00");
        }

        String existingAccountName = accountRepository.findAccountNumberByName(registerAccountRequest.getAccountName());
        if (existingAccountName != null){
            throw new AccountAlreadyExistException("Account Name " + registerAccountRequest.getAccountName() + "already exist");

        }
        Account account = Account.builder()
                .accountNumber(accountNumberGenerator())
                .accountName(registerAccountRequest.getAccountName())
                .accountPassword(registerAccountRequest.getAccountPassword())
                .accountBalance(registerAccountRequest.getInitialDeposit())
                .build();

        accountRepository.save(account);
        return new RegisterAccountResponse(true, " Account Created Successfully", account.getAccountNumber());
    }

    private String accountNumberGenerator(){
        SecureRandom mySecureRandom = new SecureRandom();
        String accountNumber = (10000 + mySecureRandom.nextInt(99999)) + "" + (10000 + mySecureRandom.nextInt(99999));
        while (accountRepository.getAllAccountNumber().contains(accountNumber)){
            accountNumber = (10000 + mySecureRandom.nextInt(99999)) + "" + (10000 + mySecureRandom.nextInt(99999));

        }
        return accountNumber;
    }
}
