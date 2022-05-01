package ui.graphic;

import ui.GraphicApp;
import ui.graphic.option.ProductAdding;
import ui.graphic.option.ViewProduct;
import ui.graphic.option.ViewProductByRanking;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class ProductOptionDisplay extends BasicTool {

    public ProductOptionDisplay(GraphicApp graphicApp) {
        super(graphicApp);
        setTitle("Product Option Display");
        Container contentPane = this.getContentPane();

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Choose what do you want to do with product information?");
        subPanel.add(label);

        JButton button1 = new JButton("Add Product");
        button1.setActionCommand("Add");
        button1.addActionListener(this);
        JButton button2 = new JButton("View Product List");
        button2.setActionCommand("View");
        button2.addActionListener(this);
        JButton button3 = new JButton("View Product By Ranking");
        button3.setActionCommand("Ranking View");
        button3.addActionListener(this);
        subPanel.add(button1);
        subPanel.add(button2);
        subPanel.add(button3);
        panel.add(subPanel);
        contentPane.add(panel);
        pack();
        setVisible(true);
    }

    // EFFECTS: if click "Add" command button, it shows ProductAdding screen
    //          if it click "View" command button, it shows ViewProduct screen
    //          if it click "Ranking View" command button, it shows ViewProductByRanking screen
    //          also call same method of super class
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")) {
            new ProductAdding(graphicApp);
        } else if (e.getActionCommand().equals("View")) {
            new ViewProduct(graphicApp);

        } else if (e.getActionCommand().equals("Ranking View")) {
            new ViewProductByRanking(graphicApp);
        }
        super.actionPerformed(e);

    }
}
