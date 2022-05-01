package ui.graphic;

import ui.GraphicApp;
import ui.graphic.option.CustomerAdding;
import ui.graphic.option.Buying;
import ui.graphic.option.ViewCustomer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CustomerOptionDisplay extends BasicTool {

    // EFFECTS: Constructor
    public CustomerOptionDisplay(GraphicApp graphicApp) {
        super(graphicApp);
        setTitle("Customer Option Display");
        Container contentPane = this.getContentPane();

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Choose what do you want to do with customer information?");
        subPanel.add(label);

        JButton button1 = new JButton("Add Customer");
        button1.setActionCommand("Add");
        button1.addActionListener(this);
        JButton button2 = new JButton("View customer List");
        button2.setActionCommand("View");
        button2.addActionListener(this);
        JButton button3 = new JButton("Buy Product");
        button3.setActionCommand("Buy");
        button3.addActionListener(this);

        subPanel.add(button1);
        subPanel.add(button2);
        subPanel.add(button3);
        panel.add(subPanel);
        contentPane.add(panel);
        pack();
        setVisible(true);
    }

    // EFFECTS: if click "Add" command button, it shows customer adding screen
    //          if it click "View" command button, it shows view customer screen
    //          if it click "Buy" command button, it shows buy product(buying) screen
    //          also call same method of super class
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")) {
            new CustomerAdding(graphicApp);
        } else if (e.getActionCommand().equals("View")) {
            new ViewCustomer(graphicApp);

        } else if (e.getActionCommand().equals("Buy")) {
            new Buying(graphicApp);
        }
        super.actionPerformed(e);
    }
}
