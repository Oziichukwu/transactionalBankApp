package africa.semicolon.bankapplication.services;

import africa.semicolon.bankapplication.data.dtos.request.RegisterAccountRequest;
import africa.semicolon.bankapplication.data.dtos.response.RegisterAccountResponse;

public interface AccountAuthService {
    RegisterAccountRequest registerAccount(RegisterAccountResponse registerAccountResponse);

}
