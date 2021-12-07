package africa.semicolon.bankapplication.services;

import africa.semicolon.bankapplication.data.dtos.request.RegisterAccountRequest;
import africa.semicolon.bankapplication.data.dtos.response.RegisterAccountResponse;
import africa.semicolon.bankapplication.data.models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountAuthService {
    RegisterAccountResponse registerAccount(RegisterAccountRequest registerAccountRequest);

    List<Account> findAllAccounts();

    Account findAccountByAccountNumber(String accountNumber);

}
