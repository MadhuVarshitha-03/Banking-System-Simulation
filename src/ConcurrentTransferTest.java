import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import service.BankService;
import task.TransactionTask;

public class ConcurrentTransferTest {

    public static void main(String[] args) {

        BankService bankService = new BankService();

        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.execute(new TransactionTask(bankService, 101, 102, 500));
        executor.execute(new TransactionTask(bankService, 101, 102, 700));
        executor.execute(new TransactionTask(bankService, 102, 101, 300));

        executor.shutdown();
    }
}
