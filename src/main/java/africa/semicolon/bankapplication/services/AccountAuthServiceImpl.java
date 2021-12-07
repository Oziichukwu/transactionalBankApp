package africa.semicolon.bankapplication.services;

import africa.semicolon.bankapplication.data.dtos.request.AuthenticationRequest;
import africa.semicolon.bankapplication.data.dtos.request.RegisterAccountRequest;
import africa.semicolon.bankapplication.data.dtos.response.RegisterAccountResponse;
import africa.semicolon.bankapplication.data.exceptions.AccountAlreadyExistException;
import africa.semicolon.bankapplication.data.exceptions.InsufficientInitialDepositException;
import africa.semicolon.bankapplication.data.exceptions.InvalidAccountNameException;
import africa.semicolon.bankapplication.data.models.Account;
import africa.semicolon.bankapplication.repository.AccountRepository;
import africa.semicolon.bankapplication.repository.AccountRepositoryImpl;
import africa.semicolon.bankapplication.security.jwt.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountAuthServiceImpl implements AccountAuthService {


    private static final AccountRepository accountRepository = new AccountRepositoryImpl();

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final AccountDetailsService accountDetailsService;


    @Override
    public RegisterAccountResponse registerAccount(RegisterAccountRequest registerAccountRequest) throws InvalidAccountNameException{

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

    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.getAllAccountNumber();
    }

    @Override
    public Account findAccountByAccountNumber(String accountNumber) {
        return accountRepository.findAccountByAccountNumber(accountNumber);
    }

    private String accountNumberGenerator(){
        SecureRandom mySecureRandom = new SecureRandom();
        String accountNumber = (10000 + mySecureRandom.nextInt(99999)) + "" + (10000 + mySecureRandom.nextInt(99999));
        while (accountRepository.getAllAccountNumber().contains(accountNumber)){
            accountNumber = (10000 + mySecureRandom.nextInt(99999)) + "" + (10000 + mySecureRandom.nextInt(99999));

        }
        return accountNumber;
    }

    public String login (AuthenticationRequest authenticationRequest) throws BadCredentialsException {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getAccountNumber(),
                authenticationRequest.getAccountPassword()));
        UserDetails userDetails = accountDetailsService.loadUserByUsername(authenticationRequest.getAccountNumber());

        return jwtUtil.generateToken(userDetails);
    }
}
