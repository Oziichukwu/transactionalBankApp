package africa.semicolon.bankapplication.data.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionResponse {

    private boolean success;

    private String message;
}
