package africa.semicolon.bankapplication.controller;

import africa.semicolon.bankapplication.data.dtos.request.AuthenticationRequest;
import africa.semicolon.bankapplication.data.dtos.request.RegisterAccountRequest;
import africa.semicolon.bankapplication.data.exceptions.AccountAlreadyExistException;
import africa.semicolon.bankapplication.data.exceptions.InsufficientInitialDepositException;
import africa.semicolon.bankapplication.data.exceptions.InvalidAccountNameException;
import africa.semicolon.bankapplication.services.AccountAuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/auth")
@RestController
@AllArgsConstructor
public class AccountAuthenticationController {

    private final AccountAuthService accountAuthService;

    @PostMapping("/api/v1/create_account")
    public ResponseEntity<?> register (@RequestBody RegisterAccountRequest registerAccountRequest){
        try{
            return new ResponseEntity<>(accountAuthService.registerAccount(registerAccountRequest), HttpStatus.OK);
        }catch (AccountAlreadyExistException | InsufficientInitialDepositException | InvalidAccountNameException e){
                return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/v1/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request){
        try {
            return new ResponseEntity<>(accountAuthService.login(request), HttpStatus.ACCEPTED);
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.FORBIDDEN);
        }
    }
}
