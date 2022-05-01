package ui.graphic.option;

import model.customer.Customer;
import model.product.Product;
import ui.GraphicApp;
import ui.graphic.BasicTool;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class Buying extends BasicTool {
    private JButton button;
    private JTextField telNum;
    private JTextField productName;
    private JTextField amount;
    private JTextField year;
    private JTextField month;
    private JTextField day;
    private JTextField hour;
    private JTextField minute;

    // EFFECTS: Constructor
    public Buying(GraphicApp graphicApp) {
        super(graphicApp);
        setTitle("Buy Product");
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridLayout(8, 2));
        Container contentPane = getContentPane();
        contentPane.add(panel);

        button = new JButton("OK");
        button.setActionCommand("Buy");
        button.addActionListener(this);

        createField();
        addToSubPanel(subPanel);
        panel.add(subPanel);
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(button, BorderLayout.LINE_END);
        pack();
        setVisible(true);
    }

    // EFFECTS: create new text field
    private void createField() {
        telNum = new JTextField();
        productName = new JTextField();
        amount = new JTextField();
        year = new JTextField();
        month = new JTextField();
        day = new JTextField();
        hour = new JTextField();
        minute = new JTextField();
    }

    // EFFECTS: add label and field to sub panel
    private void addToSubPanel(JPanel subPanel) {
        subPanel.add(new Label("Customer telNum"));
        subPanel.add(telNum);
        subPanel.add(new Label("Product name"));
        subPanel.add(productName);
        subPanel.add(new Label("Amount"));
        subPanel.add(amount);
        subPanel.add(new Label("Year"));
        subPanel.add(year);
        subPanel.add(new Label("Month"));
        subPanel.add(month);
        subPanel.add(new Label("Day"));
        subPanel.add(day);
        subPanel.add(new Label("Hour"));
        subPanel.add(hour);
        subPanel.add(new Label("Minute"));
        subPanel.add(minute);
    }

    // MODIFIES: Graphic App
    // EFFECTS: If click "Buy" command button, bring telNumber then indicate customer with given telNumber
    //          bring product with given product name then indicate product with given name
    //          bring amount, year, month, day, hour, minute and conduct buy product with given values
    //          also call same method of super class
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Buy")) {
            long telNumber = Long.parseLong(telNum.getText());
            Customer customer = graphicApp.customerList.getCustomerWithGivenInfo(telNumber);
            Product product = graphicApp.productList.findProductWithName(productName.getText());
            int amount1 = Integer.parseInt(amount.getText());
            int year1 = Integer.parseInt(year.getText());
            int month1 = Integer.parseInt(month.getText());
            int day1 = Integer.parseInt(day.getText());;
            int hour1 = Integer.parseInt(hour.getText());;
            int minute1 = Integer.parseInt(minute.getText());;
            customer.buyProduct(product, amount1, year1, month1, day1, hour1, minute1);
        }
        super.actionPerformed(e);

    }
}
