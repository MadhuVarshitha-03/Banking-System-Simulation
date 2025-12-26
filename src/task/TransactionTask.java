package task;

import service.BankService;

public class TransactionTask implements Runnable {

    private BankService bankService;
    private int fromAccount;
    private int toAccount;
    private double amount;

    public TransactionTask(BankService bankService,
                           int fromAccount,
                           int toAccount,
                           double amount) {
        this.bankService = bankService;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        try {
            bankService.transfer(fromAccount, toAccount, amount);
            System.out.println(Thread.currentThread().getName()
                    + " ✅ Transfer successful");
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName()
                    + " ❌ " + e.getMessage());
        }
    }
}
