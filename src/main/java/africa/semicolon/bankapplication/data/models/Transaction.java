package africa.semicolon.bankapplication.data.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class Transaction {

    private LocalDate transactionDate;

    private String transactionType;

    private String narration;

    private Double amount;

    private Double accountBalance;
}
