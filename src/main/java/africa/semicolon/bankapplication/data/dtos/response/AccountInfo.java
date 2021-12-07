package africa.semicolon.bankapplication.data.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountInfo {

    private String accountName;

    private String accountNumber;

    private Double balance;

}
