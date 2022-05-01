package ui.graphic;

import ui.GraphicApp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class BasicTool extends JFrame implements ActionListener {
    protected JButton firstScreen;
    protected JButton quit;
    protected JButton save;
    protected GraphicApp graphicApp;
    protected JPanel panel;

    // EFFECTS: Constructor
    public BasicTool(GraphicApp graphicApp) {
        this.graphicApp = graphicApp;
        setPreferredSize(new Dimension(500, 300));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new FlowLayout());

        firstScreen = new JButton("RETURN");
        firstScreen.setActionCommand("RETURN");
        firstScreen.addActionListener(this);

        quit = new JButton("QUIT");
        quit.setActionCommand("QUIT");
        quit.addActionListener(this);

        save = new JButton("SAVE");
        save.setActionCommand("SAVE");
        save.addActionListener(this);

        subPanel.add(firstScreen);
        subPanel.add(quit);
        subPanel.add(save);
        panel.add(subPanel, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    // EFFECTS: if click "RETURN" command button, it goes back to first screen
    //          if it click "QUIT" command button, it shows save screen
    //          if it click "SAVE" command button, it save customer list and product list
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("RETURN")) {
            new FirstScreen(graphicApp);
        } else if (e.getActionCommand().equals("QUIT")) {
            new SaveScreen(graphicApp);
        } else if (e.getActionCommand().equals("SAVE")) {
            saveCustomerList();
            saveProductList();
        }
    }

    // EFFECTS: saves the customer list to file
    private void saveCustomerList() {
        try {
            graphicApp.customerJsonWriter.open();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        graphicApp.customerJsonWriter.write(graphicApp.customerList);
        graphicApp.customerJsonWriter.close();

    }

    // EFFECTS: saves the product list to file
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
