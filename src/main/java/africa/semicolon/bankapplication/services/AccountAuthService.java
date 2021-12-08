package africa.semicolon.bankapplication.services;

import africa.semicolon.bankapplication.data.dtos.request.AuthenticationRequest;
import africa.semicolon.bankapplication.data.dtos.request.RegisterAccountRequest;
import africa.semicolon.bankapplication.data.dtos.response.RegisterAccountResponse;
import africa.semicolon.bankapplication.data.exceptions.AccountAlreadyExistException;
import africa.semicolon.bankapplication.data.exceptions.InsufficientInitialDepositException;
import africa.semicolon.bankapplication.data.exceptions.InvalidAccountNameException;
import africa.semicolon.bankapplication.data.models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountAuthService {
    RegisterAccountResponse registerAccount(RegisterAccountRequest registerAccountRequest)throws InvalidAccountNameException, InsufficientInitialDepositException, AccountAlreadyExistException;

    List<Account> findAllAccounts();

    Account findAccountByAccountNumber(String accountNumber);

    String login (AuthenticationRequest authenticationRequest);
}
