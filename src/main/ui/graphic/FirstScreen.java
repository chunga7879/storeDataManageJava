package ui.graphic;

import ui.GraphicApp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FirstScreen extends BasicTool {

    // EFFECTS: Constructor
    public FirstScreen(GraphicApp graphicApp) {
        super(graphicApp);
        setTitle("Choose C/P");
        Container contentPane = this.getContentPane();

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Choose what information do you want to manage(C/P)?");
        subPanel.add(label);

        JButton button1 = new JButton("Customer");
        button1.setActionCommand("Customer");
        button1.addActionListener(this);
        JButton button2 = new JButton("Product");
        button2.setActionCommand("Product");
        button2.addActionListener(this);

        subPanel.add(button1);
        subPanel.add(button2);
        panel.add(subPanel);
        contentPane.add(panel);

        pack();
        setVisible(true);
    }

    // EFFECTS: if click "Customer" command button, it shows CustomerOptionDisplay screen
    //          if it click "Product" command button, it shows ProductOptionDisplay screen
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Customer")) {
            new CustomerOptionDisplay(graphicApp);
        } else if (e.getActionCommand().equals("Product")) {
            new ProductOptionDisplay(graphicApp);
        }
    }
}
