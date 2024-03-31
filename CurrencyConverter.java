import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter extends JFrame implements ActionListener {
    private JTextField inputField;
    private JComboBox<String> currentCurrencyComboBox;
    private JComboBox<String> targetCurrencyComboBox;
    private JLabel resultLabel;

    private Map<String, Double> conversionRates;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel inputLabel = new JLabel("Enter amount:");
        inputField = new JTextField();

        JLabel currentCurrencyLabel = new JLabel("Current currency:");
        currentCurrencyComboBox = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "JPY", "INR"});

        JLabel targetCurrencyLabel = new JLabel("Target currency:");
        targetCurrencyComboBox = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "JPY", "INR"});

        JButton convertButton = new JButton("Convert");
        JLabel outputLabel = new JLabel("Converted amount:");

        resultLabel = new JLabel();

        panel.add(inputLabel);
        panel.add(inputField);
        panel.add(currentCurrencyLabel);
        panel.add(currentCurrencyComboBox);
        panel.add(targetCurrencyLabel);
        panel.add(targetCurrencyComboBox);
        panel.add(convertButton);
        panel.add(outputLabel);
        panel.add(new JLabel());
        panel.add(resultLabel);

        convertButton.addActionListener(this);

        add(panel);
        setVisible(true);

        initializeConversionRates();
    }

    private void initializeConversionRates() {
        conversionRates = new HashMap<>();
        conversionRates.put("USD-USD", 1.0);
        conversionRates.put("USD-EUR", 0.84);
        conversionRates.put("USD-GBP", 0.73);
        conversionRates.put("USD-JPY", 110.35);
        conversionRates.put("USD-INR", 83.37);
        conversionRates.put("EUR-USD", 1.19);
        conversionRates.put("EUR-EUR", 1.0);
        conversionRates.put("EUR-GBP", 0.86);
        conversionRates.put("EUR-JPY", 129.54);
        conversionRates.put("EUR-INR", 97.35);
        conversionRates.put("GBP-USD", 1.37);
        conversionRates.put("GBP-EUR", 1.16);
        conversionRates.put("GBP-GBP", 1.0);
        conversionRates.put("GBP-JPY", 149.90);
        conversionRates.put("GBP-INR", 112.92);
        conversionRates.put("JPY-USD", 0.0091);
        conversionRates.put("JPY-EUR", 0.0077);
        conversionRates.put("JPY-GBP", 0.0067);
        conversionRates.put("JPY-JPY", 1.0);
        conversionRates.put("JPY-INR", 0.75);
        conversionRates.put("INR-USD", 0.012);
        conversionRates.put("INR-EUR", 0.010);
        conversionRates.put("INR-GBP", 0.0089);
        conversionRates.put("INR-JPY", 1.34);
        conversionRates.put("INR-INR", 1.0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Convert")) {
            try {
                double amount = Double.parseDouble(inputField.getText());
                String currentCurrency = (String) currentCurrencyComboBox.getSelectedItem();
                String targetCurrency = (String) targetCurrencyComboBox.getSelectedItem();

                String key = currentCurrency + "-" + targetCurrency;
                double rate = conversionRates.get(key);
                double convertedAmount = amount * rate;

                DecimalFormat df = new DecimalFormat("#.##");
                resultLabel.setText(df.format(convertedAmount) + " " + targetCurrency);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new CurrencyConverter();
    }
}
