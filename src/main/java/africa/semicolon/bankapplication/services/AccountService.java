package africa.semicolon.bankapplication.services;

import africa.semicolon.bankapplication.data.dtos.request.RegisterAccountRequest;
import africa.semicolon.bankapplication.data.dtos.response.RegisterAccountResponse;

public interface AccountService {
    RegisterAccountRequest registerAccount(RegisterAccountResponse registerAccountResponse);

}
