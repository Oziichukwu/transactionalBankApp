package africa.semicolon.bankapplication.data.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DepositRequest {

    private String accountNumber;

    private Double amount;
}
