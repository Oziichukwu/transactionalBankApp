package africa.semicolon.bankapplication.services;

import africa.semicolon.bankapplication.data.dtos.request.DepositRequest;
import africa.semicolon.bankapplication.data.dtos.request.WithdrawRequest;
import africa.semicolon.bankapplication.data.dtos.response.AccountInfo;
import africa.semicolon.bankapplication.data.dtos.response.TransactionResponse;
import africa.semicolon.bankapplication.data.models.Transaction;

import java.util.List;

public interface AccountService {

    TransactionResponse withdraw(WithdrawRequest request);

    TransactionResponse deposit(DepositRequest request);

    AccountInfo getAccountInfo(String accountNumber, String password);

    List<Transaction> getAllAccountTransaction(String accountNumber);

}
