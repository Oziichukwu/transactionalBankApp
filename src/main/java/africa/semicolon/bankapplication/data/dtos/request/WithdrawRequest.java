package africa.semicolon.bankapplication.data.dtos.request;


import lombok.Data;

@Data
public class WithdrawRequest {

    private String accountNumber;

    private String accountPassword;

    private Double withdrawnAmount;
}
