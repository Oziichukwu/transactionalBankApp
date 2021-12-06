package africa.semicolon.bankapplication.repository;

import africa.semicolon.bankapplication.data.models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    Account save(Account account);

    Optional<Account> findAccountByAccountNumber(String accountNumber);

    void deleteAccount(String accountNumber);

    List<Account> getAllAccountNumber();

    String findAccountNumberByName(String accountName);

}