package africa.semicolon.bankapplication.data.dtos.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterAccountResponse {

    private boolean success;

    private String message;
}
