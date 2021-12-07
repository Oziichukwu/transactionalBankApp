package africa.semicolon.bankapplication.data.dtos.response;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RegisterAccountResponse {

    private boolean success;

    private String message;

    private String accountNumber;
}
