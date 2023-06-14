import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class InvalidTransactionException extends Exception {
    public InvalidTransactionException(String message) {
        super(message);
    }
}
public class FinancialTransactionProcessingSystem {
    private Map<String, Account> accountMap;

    public FinancialTransactionProcessingSystem() {
        accountMap = new HashMap<>();
    }

    public void createAccount(String accountId, BigDecimal initialBalance) {
        Account account = new Account(accountId, initialBalance);
        accountMap.put(accountId, account);
    }

    public void processTransaction(Transaction transaction) throws AccountNotFoundException, InsufficientBalanceException, InvalidTransactionException {
            validateTransaction(transaction);

        Account account = accountMap.get(transaction.getAccountId());
        if (account == null) {
            throw new AccountNotFoundException("Account not found.");
        }

        if (transaction.getType() == TransactionType.PAYMENT) {
            if (account.getBalance().compareTo(transaction.getAmount()) < 0) {
                throw new InsufficientBalanceException("Insufficient balance for the attempted transaction.");
            }
            account.setBalance(account.getBalance().subtract(transaction.getAmount()));
        } else if (transaction.getType() == TransactionType.TRANSFER) {
            String destinationAccountId = transaction.getDestinationAccountId();
            Account destinationAccount = accountMap.get(destinationAccountId);

            if (destinationAccount == null) {
                throw new AccountNotFoundException("Destination account not found.");
            }

            if (account.getBalance().compareTo(transaction.getAmount()) < 0) {
                throw new InsufficientBalanceException("Insufficient balance for attempted transfer.");
            }
            account.setBalance(account.getBalance().subtract(transaction.getAmount()));
            destinationAccount.setBalance(destinationAccount.getBalance().add(transaction.getAmount()));
        } else if (transaction.getType() == TransactionType.SETTLEMENT) {

            String settlementAccountId = transaction.getSettlementAccountId();
            Account settlementAccount = accountMap.get(settlementAccountId);
            if (settlementAccount == null) {
                throw new AccountNotFoundException("Settlement account not found.");
            }

            settlementAccount.setBalance(settlementAccount.getBalance().add(transaction.getAmount()));
        }

        logTransaction(transaction);
    }

    private void validateTransaction(Transaction transaction) throws InvalidTransactionException {

        if (transaction.getTransactionId() == null || transaction.getTransactionId().isEmpty()) {
            throw new InvalidTransactionException("Invalid transaction ID.");
        }

        if (transaction.getAccountId() == null || transaction.getAccountId().isEmpty()) {
            throw new InvalidTransactionException("Invalid account ID.");
        }

        if (transaction.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionException("Invalid transaction amount.");
        }
    }

    private void logTransaction(Transaction transaction) {

        System.out.println("Transaction ID: " + transaction.getTransactionId());
        System.out.println("Account ID: " + transaction.getAccountId());
        System.out.println("Amount: " + transaction.getAmount());
        System.out.println("Type: " + transaction.getType());
        System.out.println();
    }

    public static void main(String[] args) {

        FinancialTransactionProcessingSystem system = new FinancialTransactionProcessingSystem();

        system.createAccount("acc1", new BigDecimal("320.00"));
        system.createAccount("acc2", new BigDecimal("1282.00"));
        system.createAccount("acc3", new BigDecimal("9965.00"));

        Transaction transaction = new Transaction("txn1", "acc1", new BigDecimal("200.00"), TransactionType.PAYMENT, null, null);


        try {
            system.processTransaction(transaction);
        } catch (AccountNotFoundException e) {
            System.out.println("Account not found: " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println("Insufficient balance: " + e.getMessage());
        } catch (InvalidTransactionException e) {
            System.out.println("Invalid transaction: " + e.getMessage());
        }
    }
}