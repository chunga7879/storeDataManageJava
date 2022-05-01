package ui.graphic.option;

import javazoom.jl.player.Player;
import model.customer.Customer;
import model.customer.SilverCustomer;
import ui.GraphicApp;
import ui.graphic.BasicTool;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CustomerAdding extends BasicTool {
    private JButton button;
    private JTextField name;
    private JTextField telNum;
    private Container contentPane;


    // EFFECTS: Constructor
    public CustomerAdding(GraphicApp graphicApp) {
        super(graphicApp);
        setTitle("Add Customer");
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridLayout(2, 2));

        contentPane = getContentPane();

        name = new JTextField(10);
        telNum = new JTextField(10);
        subPanel.add(new Label("Customer Name"));
        subPanel.add(name);
        subPanel.add(new Label("Customer telNum"));
        subPanel.add(telNum);

        button = new JButton("OK");
        button.setMaximumSize(new Dimension(25, 25));
        button.setActionCommand("Add");
        button.addActionListener(this);
        panel.add(subPanel);
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(button, BorderLayout.LINE_END);

        pack();
        setVisible(true);
    }

    // EFFECTS: Play AddCustomer mp3
    void playSound() {
        try {
            String file = "AddCustomer.mp3";
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            Player player = new Player(bis);
            player.play();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    // MODIFIES: Graphic App
    // EFFECTS: If click "ADD" command button, bring telNumber and name then create customer
    //          add that customer to customer list, and play addCustomer mp3
    //          also call same method of super class
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")) {
            long telNumber = Long.parseLong(telNum.getText());
            Customer customer = new SilverCustomer(name.getText(), telNumber);
            graphicApp.customerList.addCustomerToProperList(customer);
            playSound();
        }
        super.actionPerformed(e);
    }
}
