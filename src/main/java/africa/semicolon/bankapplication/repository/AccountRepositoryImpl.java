package africa.semicolon.bankapplication.repository;

import africa.semicolon.bankapplication.data.models.Account;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AccountRepositoryImpl implements AccountRepository{

    private final Map<String, Account> database = new HashMap<>();

    @Override
    public Account save(Account account) {
        database.put(account.getAccountNumber(), account);

        return account;
    }

    @Override
    public Account findAccountByAccountNumber(String accountNumber) {
        return database.get(accountNumber);
    }

    @Override
    public void deleteAccount(String accountNumber) {
        database.remove(accountNumber);
    }

    @Override
    public List<Account> getAllAccountNumber() {

        List<Account> accounts = new ArrayList<>();
        Set<String>keysInDb = database.keySet();
        for (String key : keysInDb){
            accounts.add(database.get(key));
        }
        return accounts;
    }

    @Override
    public String findAccountNumberByName(String accountName) {
        for (Account account : database.values()){
            if (account.getAccountName().equalsIgnoreCase(accountName)){
                return account.getAccountName();
            }
        }
        return null;
    }
}
