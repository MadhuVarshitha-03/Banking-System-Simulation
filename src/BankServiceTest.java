import service.BankService;

public class BankServiceTest {
    public static void main(String[] args) {
        try {
            BankService bank = new BankService();

            bank.deposit(101, 1000);
            System.out.println("✅ Deposit successful");

            bank.withdraw(101, 500);
            System.out.println("✅ Withdrawal successful");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
