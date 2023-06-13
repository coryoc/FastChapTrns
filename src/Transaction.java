import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Transaction {
    private String transactionId;
    private String accountId;
    private BigDecimal amount;
    private TransactionType type;

    public Transaction(String transactionId, String accountId, BigDecimal amount, TransactionType type) {
      this.transactionId = transactionId;
      this.accountId = accountId;
      this.amount = amount;
      this.type = type;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }
}
