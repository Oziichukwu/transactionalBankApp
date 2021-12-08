package africa.semicolon.bankapplication.services;

import africa.semicolon.bankapplication.data.dtos.request.DepositRequest;
import africa.semicolon.bankapplication.data.dtos.request.WithdrawRequest;
import africa.semicolon.bankapplication.data.dtos.response.AccountInfo;
import africa.semicolon.bankapplication.data.dtos.response.TransactionResponse;
import africa.semicolon.bankapplication.data.exceptions.AccountDoesNotExistException;
import africa.semicolon.bankapplication.data.exceptions.ExcessWithdrawnAmountException;
import africa.semicolon.bankapplication.data.models.Transaction;

import java.util.List;

public interface AccountService {

    TransactionResponse withdraw(WithdrawRequest request)throws AccountDoesNotExistException, ExcessWithdrawnAmountException;

    TransactionResponse deposit(DepositRequest request)throws AccountDoesNotExistException;

    AccountInfo getAccountInfo(String accountNumber, String password)throws AccountDoesNotExistException;

    List<Transaction> getAllAccountTransaction(String accountNumber);

}
