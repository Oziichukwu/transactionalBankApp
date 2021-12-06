package africa.semicolon.bankapplication.data.models;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private String accountNumber;

    private String accountName;

    private String accountPassword;

    private String accountBalance;

    private List<Transaction>transactions;

    public void addTransaction(Transaction transaction){
        if (transactions == null){
            transactions = new ArrayList<>();
        }
        transactions.add(transaction);
    }

}
