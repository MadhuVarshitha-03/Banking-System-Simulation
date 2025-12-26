package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import service.BankService;
import task.TransactionTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankingGUI extends JFrame {

    private JTextField accField;
    private JTextField toAccField;
    private JTextField amtField;

    private BankService bankService;
    private ExecutorService executor;

    public BankingGUI() {

        bankService = new BankService();
        executor = Executors.newFixedThreadPool(3);

        setTitle("Banking System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        panel.add(new JLabel("Account Number:"));
        accField = new JTextField();
        panel.add(accField);

        panel.add(new JLabel("To Account (Transfer):"));
        toAccField = new JTextField();
        panel.add(toAccField);

        panel.add(new JLabel("Amount:"));
        amtField = new JTextField();
        panel.add(amtField);

        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton transferBtn = new JButton("Transfer");

        panel.add(depositBtn);
        panel.add(withdrawBtn);
        panel.add(transferBtn);

        add(panel);

        // Deposit
        depositBtn.addActionListener(e -> {
            try {
                int acc = Integer.parseInt(accField.getText());
                double amt = Double.parseDouble(amtField.getText());

                bankService.deposit(acc, amt);
                JOptionPane.showMessageDialog(this,
                        "Deposit Successful");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Withdraw
        withdrawBtn.addActionListener(e -> {
            try {
                int acc = Integer.parseInt(accField.getText());
                double amt = Double.parseDouble(amtField.getText());

                bankService.withdraw(acc, amt);
                JOptionPane.showMessageDialog(this,
                        "Withdrawal Successful");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Transfer (Multithreaded)
        transferBtn.addActionListener(e -> {
            try {
                int from = Integer.parseInt(accField.getText());
                int to = Integer.parseInt(toAccField.getText());
                double amt = Double.parseDouble(amtField.getText());

                executor.execute(
                        new TransactionTask(bankService, from, to, amt)
                );

                JOptionPane.showMessageDialog(this,
                        "Transfer Initiated");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void launch() {
        SwingUtilities.invokeLater(() -> {
            new BankingGUI().setVisible(true);
        });
    }
}
