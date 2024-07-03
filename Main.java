import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    private JTextField display;
    private double operand1, operand2;
    private char operator;

    public Main() {
        super("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        panel.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        panel.add(buttonPanel, BorderLayout.CENTER);
        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "0": case "1": case "2": case "3":
            case "4": case "5": case "6": case "7":
            case "8": case "9":
                display.setText(display.getText() + command);
                break;
            case "+": case "-": case "*": case "/":
                operand1 = Double.parseDouble(display.getText());
                operator = command.charAt(0);
                display.setText("");
                break;
            case "=":
                operand2 = Double.parseDouble(display.getText());
                double result = calculateResult();
                display.setText(String.valueOf(result));
                break;
            case "C":
                display.setText("");
                break;
        }
    }

    private double calculateResult() {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0)
                    throw new ArithmeticException("Cannot divide by zero");
                return operand1 / operand2;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}
