import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TaxCalculatorGUI extends JFrame implements ActionListener {

    private JLabel incomeLabel, taxLabel;
    private JTextField incomeTextField, taxTextField;
    private JButton calculateButton;

    public TaxCalculatorGUI() {
        super("Tax Calculator");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        incomeLabel = new JLabel("Enter Annual Income:");
        add(incomeLabel);

        incomeTextField = new JTextField(10);
        add(incomeTextField);

        calculateButton = new JButton("Calculate Tax");
        add(calculateButton);
        calculateButton.addActionListener(this);

        taxLabel = new JLabel("Tax Obligation:");
        add(taxLabel);

        taxTextField = new JTextField(10);
        taxTextField.setEditable(false);
        add(taxTextField);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            double income = Double.parseDouble(incomeTextField.getText());
            double tax = calculateTax(income);
            taxTextField.setText(String.format("%.2f", tax));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter a valid number.");
        } catch (ArithmeticException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Division by zero error.");
        }
    }

    private double calculateTax(double income) throws ArithmeticException {
        double tax;

        if (income <= 48535) {
            tax = income * 0.15;
        } else if (income <= 97069) {
            tax = 7280 + (income - 48535) * 0.205;
        } else if (income <= 150473) {
            tax = 17230 + (income - 97069) * 0.26;
        } else if (income <= 214368) {
            tax = 31115 + (income - 150473) * 0.29;
        } else {
            tax = 49645 + (income - 214368) * 0.33;
        }

        if (income == 0) {
            throw new ArithmeticException();
        }

        return tax;
    }

    public static void main(String[] args) {
        TaxCalculatorGUI taxGUI = new TaxCalculatorGUI();
    }

}