import java.math.BigDecimal;


public class Transaction {
    private String transactionId;
    private String accountId;
    private BigDecimal amount;
    private TransactionType type;
    private String destinationAccountId;
    private String settlementAccountId;

    public Transaction(String transactionId, String accountId, BigDecimal amount, TransactionType type, String destinationAccountId, String settlementAccountId) {
      this.transactionId = transactionId;
      this.accountId = accountId;
      this.amount = amount;
      this.type = type;
      this.destinationAccountId = destinationAccountId;
      this.settlementAccountId = settlementAccountId;
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

    public String getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(String destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    public String getSettlementAccountId() {
        return settlementAccountId;
    }

    public void setSettlementAccountId(String settlementAccountId) {
        this.settlementAccountId = settlementAccountId;
    }
}
