package ui.graphic;

import ui.GraphicApp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoadScreen extends JFrame implements ActionListener {
    GraphicApp graphicApp;

    // EFFECTS: Constructor
    public LoadScreen(GraphicApp graphicApp) {
        this.graphicApp = graphicApp;

        setPreferredSize(new Dimension(500, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Load");
        Container contentPane = this.getContentPane();

        JPanel panel = new JPanel();
        contentPane.add(panel);
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Do you want to load customer/product list??");
        panel.add(label);

        JButton button1 = new JButton("YES");
        button1.setActionCommand("YES");
        button1.addActionListener(this);
        JButton button2 = new JButton("NO");
        button2.setActionCommand("NO");
        button2.addActionListener(this);

        panel.add(button1);
        panel.add(button2);
        contentPane.add(panel);
        pack();
        setVisible(true);
    }

    // EFFECTS: if click "YES" command button, it load customer list and product list from file
    //          and show first screen
    //          if it click "NO" command button, it shows show first screen
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("YES")) {
            loadCustomerList();
            loadProductList();
            new FirstScreen(graphicApp);
        } else {
            new FirstScreen(graphicApp);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void loadCustomerList() {
        try {
            graphicApp.customerList = graphicApp.customerJsonReader.read();
            System.out.println("Loaded " + graphicApp.customerList.getCustomerListName()
                    + " from " + graphicApp.JSON_STORE_CUSTOMER);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + graphicApp.JSON_STORE_CUSTOMER);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void loadProductList() {
        try {
            graphicApp.productList = graphicApp.productJsonReader.read();
            System.out.println("Loaded " + graphicApp.productList.getProductListName()
                    + " from " + graphicApp.JSON_STORE_PRODUCT);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + graphicApp.JSON_STORE_PRODUCT);
        }
    }



}
