package africa.semicolon.bankapplication.data.dtos.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RegisterAccountRequest {

    private String accountName;

    private String accountPassword;

    private double initialDeposit;
}
