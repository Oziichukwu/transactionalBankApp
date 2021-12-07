package africa.semicolon.bankapplication.services;

import africa.semicolon.bankapplication.data.dtos.request.DepositRequest;
import africa.semicolon.bankapplication.data.dtos.request.WithdrawRequest;
import africa.semicolon.bankapplication.data.dtos.response.AccountInfo;
import africa.semicolon.bankapplication.data.dtos.response.TransactionResponse;
import africa.semicolon.bankapplication.data.exceptions.AccountDoesNotExistException;
import africa.semicolon.bankapplication.data.exceptions.ExcessWithdrawnAmountException;
import africa.semicolon.bankapplication.data.models.Account;
import africa.semicolon.bankapplication.data.models.Transaction;
import africa.semicolon.bankapplication.data.models.TransactionType;
import africa.semicolon.bankapplication.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    @Override
    public TransactionResponse withdraw(WithdrawRequest request) {
        if (request.getWithdrawnAmount() < 1.00){
            throw new IllegalArgumentException("Cannot withdraw below 1.00");
        }
        Account account = accountRepository.findAccountByAccountNumber(request.getAccountNumber());
        if (account == null){
            throw new AccountDoesNotExistException("Account does not exist");
        }

        if (account.getAccountBalance() - request.getWithdrawnAmount() < 500){
            throw new ExcessWithdrawnAmountException("Cannot withdraw amount, because a " +
                    "limit of #500 must be left in the account");
        }

        Double newBalance = account.getAccountBalance() - request.getWithdrawnAmount();

        Transaction transaction = Transaction.builder()
                .transactionDate(LocalDate.now())
                .transactionType(TransactionType.WITHDRAWAL.name())
                .narration("transaction successful")
                .amount(request.getWithdrawnAmount())
                .accountBalance(newBalance)
                .build();
        account.addTransaction(transaction);
        account.setAccountBalance(newBalance);
        accountRepository.save(account);

        return new TransactionResponse(true, request.getWithdrawnAmount() +" was successfully withdrawn from account and new account balance is "+ account.getAccountBalance());
    }

    @Override
    public TransactionResponse deposit(DepositRequest request) {
        if (request.getAmount() < 1.00 || request.getAmount() > 1000000.00){
            throw new IllegalArgumentException("Cannot deposit below #1.00 or #1000000.00");
        }

        Account account = accountRepository.findAccountByAccountNumber(request.getAccountNumber());
        if (account == null){
            throw new AccountDoesNotExistException("Account does not exist");
        }

        Double newBalance  = account.getAccountBalance() + request.getAmount();

        Transaction transaction = Transaction.builder()
                .transactionDate(LocalDate.now())
                .transactionType(TransactionType.DEPOSIT.name())
                .narration("transaction successful")
                .amount(request.getAmount())
                .accountBalance(newBalance)
                .build();

        account.addTransaction(transaction);
        account.setAccountBalance(newBalance);
        accountRepository.save(account);

        return new TransactionResponse(true, request.getAccountNumber() + " was successfully deposited and new balance is " + account.getAccountBalance());

    }

    @Override
    public AccountInfo getAccountInfo(String accountNumber, String password) {
        return null;
    }

    @Override
    public List<Transaction> getAllAccountTransaction(String accountNumber) {
        return null;
    }
}
