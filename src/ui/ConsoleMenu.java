package ui;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import service.BankService;
import task.TransactionTask;

public class ConsoleMenu {

    public static void start() {

        Scanner sc = new Scanner(System.in);
        BankService bankService = new BankService();
        ExecutorService executor = Executors.newFixedThreadPool(3);

        while (true) {
            System.out.println("\n===== BANKING SYSTEM MENU =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();

            try {
                switch (choice) {

                case 1:
                    System.out.print("Enter Account Number: ");
                    int depAcc = sc.nextInt();
                    System.out.print("Enter Amount: ");
                    double depAmt = sc.nextDouble();

                    bankService.deposit(depAcc, depAmt);
                    System.out.println("✅ Deposit successful");
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    int witAcc = sc.nextInt();
                    System.out.print("Enter Amount: ");
                    double witAmt = sc.nextDouble();

                    bankService.withdraw(witAcc, witAmt);
                    System.out.println("✅ Withdrawal successful");
                    break;

                case 3:
                    System.out.print("From Account: ");
                    int from = sc.nextInt();
                    System.out.print("To Account: ");
                    int to = sc.nextInt();
                    System.out.print("Amount: ");
                    double amt = sc.nextDouble();

                    executor.execute(
                        new TransactionTask(bankService, from, to, amt)
                    );
                    break;

                case 4:
                    executor.shutdown();
                    System.out.println("👋 Exiting Banking System");
                    sc.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice");
                }

            } catch (Exception e) {
                System.out.println("❌ " + e.getMessage());
            }
        }
    }
}
