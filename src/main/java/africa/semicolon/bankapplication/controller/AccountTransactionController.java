package africa.semicolon.bankapplication.controller;

import africa.semicolon.bankapplication.data.dtos.request.DepositRequest;
import africa.semicolon.bankapplication.data.dtos.request.WithdrawRequest;
import africa.semicolon.bankapplication.data.exceptions.AccountAlreadyExistException;
import africa.semicolon.bankapplication.data.exceptions.AccountDoesNotExistException;
import africa.semicolon.bankapplication.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;

@RequestMapping("/account")
@RestController
@AllArgsConstructor
public class AccountTransactionController {

    private final AccountService accountService;

    @PostMapping("/api/v1/deposit")
    public ResponseEntity<?> deposit(@RequestBody DepositRequest depositRequest){
        try {
            return new ResponseEntity<>(accountService.deposit(depositRequest), HttpStatus.OK);
        }catch (AccountDoesNotExistException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/v1/withdrawal")
    public ResponseEntity<?> withdraw (@RequestBody WithdrawRequest withdrawRequest){
        try {
            return new ResponseEntity<>(accountService.withdraw(withdrawRequest), HttpStatus.OK);
        }catch (AccountDoesNotExistException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/v1/{accountNumber}")
    public ResponseEntity<?> getAccountInfo(@PathVariable String accountNumber, @RequestParam String password){
        try {
            return new ResponseEntity<>(accountService.getAccountInfo(accountNumber,password), HttpStatus.OK);
        }catch (AccountDoesNotExistException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/account_statement/{accountNumber}")
    public ResponseEntity<?> accountStatement(@PathVariable String accountNumber){
        return new ResponseEntity<>(accountService.getAllAccountTransaction(accountNumber), HttpStatus.OK);
    }

}
