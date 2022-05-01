package ui.graphic.option;

import javazoom.jl.player.Player;
import model.product.Product;
import ui.GraphicApp;
import ui.graphic.BasicTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class ProductAdding extends BasicTool {
    private JButton button;
    private JTextField productName;
    private JTextField price;
    private JTextField amount;
    private JTextField manager;
    private JTextField floor;

    // EFFECTS: Constructor
    public ProductAdding(GraphicApp graphicApp) {
        super(graphicApp);
        setTitle("Add Product");
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridLayout(5, 2));
        Container contentPane = getContentPane();

        createField();
        addToSubPanel(subPanel);
        panel.add(subPanel);

        button = new JButton("OK");
        button.setMaximumSize(new Dimension(25, 25));
        button.setActionCommand("Add");
        button.addActionListener(this);

        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(button, BorderLayout.LINE_END);

        pack();
        setVisible(true);

    }

    // EFFECTS: add label and field to sub panel
    private void addToSubPanel(JPanel subPanel) {
        subPanel.add(new Label("Product Name"));
        subPanel.add(productName);
        subPanel.add(new Label("Product price"));
        subPanel.add(price);
        subPanel.add(new Label("Product amount"));
        subPanel.add(amount);
        subPanel.add(new Label("Product manager"));
        subPanel.add(manager);
        subPanel.add(new Label("Product floor"));
        subPanel.add(floor);
    }

    // EFFECTS: create new text field
    private void createField() {
        productName = new JTextField();
        price = new JTextField();
        amount = new JTextField();
        manager = new JTextField();
        floor = new JTextField();
    }

    // EFFECTS: Play AddProduct mp3
    void playSound() {
        try {
            String file = "AddProduct.mp3";
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            Player player = new Player(bis);
            player.play();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    // MODIFIES: Graphic App
    // EFFECTS: If click "ADD" command button, bring productPrice, productAmount, floorNum then create product
    //          add that product to product list, and play addProduct mp3
    //          also call same method of super class
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")) {
            double productPrice = Double.parseDouble(price.getText());
            int productAmount = Integer.parseInt(amount.getText());
            int floorNum = Integer.parseInt(floor.getText());
            Product product = new Product(productName.getText(), productPrice, productAmount);
            product.setManager(manager.getText());
            product.setFloor(floorNum);
            graphicApp.productList.addProductToList(product);
            playSound();
        }
        super.actionPerformed(e);
    }
}
