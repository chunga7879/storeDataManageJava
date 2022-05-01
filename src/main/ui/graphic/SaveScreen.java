package ui.graphic;

import ui.GraphicApp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SaveScreen extends JFrame implements ActionListener {

    GraphicApp graphicApp;

    // EFFECTS: Constructor
    public SaveScreen(GraphicApp graphicApp) {
        this.graphicApp = graphicApp;

        setPreferredSize(new Dimension(500, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Save");
        Container contentPane = this.getContentPane();

        JPanel panel = new JPanel();
        contentPane.add(panel);
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Do you want to save customer/product list??");
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

    // EFFECTS: if click "YES" command button, it saves customer list and product list to file
    //          and finishes the program
    //          if it click "NO" command button, it finishes the program
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("YES")) {
            saveCustomerList();
            saveProductList();
            System.exit(0);
        } else if (e.getActionCommand().equals("NO")) {
            System.exit(0);
        }
    }

    // EFFECTS: saves the workroom to file
    private void saveCustomerList() {
        try {
            graphicApp.customerJsonWriter.open();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        graphicApp.customerJsonWriter.write(graphicApp.customerList);
        graphicApp.customerJsonWriter.close();

    }

    // EFFECTS: saves the workroom to file
    private void saveProductList() {
        try {
            graphicApp.productJsonWriter.open();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        graphicApp.productJsonWriter.write(graphicApp.productList);
        graphicApp.productJsonWriter.close();
    }

}
