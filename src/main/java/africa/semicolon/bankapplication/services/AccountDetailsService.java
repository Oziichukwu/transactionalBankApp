package africa.semicolon.bankapplication.services;

import africa.semicolon.bankapplication.data.models.Account;
import africa.semicolon.bankapplication.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class AccountDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String accountNumber) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);

        return new User(account.getAccountNumber(), account.getAccountPassword(), new ArrayList<>());
    }
}
