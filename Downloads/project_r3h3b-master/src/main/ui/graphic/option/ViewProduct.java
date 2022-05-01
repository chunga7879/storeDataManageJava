package ui.graphic.option;

import ui.GraphicApp;
import ui.graphic.FirstScreen;
import ui.graphic.SaveScreen;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewProduct extends JFrame implements ActionListener {
    TextArea textArea;
    GraphicApp graphicApp;
    protected JButton firstScreen;
    protected JButton quit;

    // EFFECTS: Constructor
    public ViewProduct(GraphicApp graphicApp) {
        this.graphicApp = graphicApp;
        textArea = new TextArea(15,80);
        textArea.setEditable(false);
        setTitle("View Product List");

        for (int i = 0; i < graphicApp.productList.getProductList().size(); i++) {
            textArea.append(graphicApp.productList.getProductList().get(i).toString());
            textArea.append("\n");
        }
        add(textArea, BorderLayout.NORTH);
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new FlowLayout());
        firstScreen = new JButton("RETURN");
        firstScreen.setActionCommand("RETURN");
        firstScreen.addActionListener(this);

        quit = new JButton("QUIT");
        quit.setActionCommand("QUIT");
        quit.addActionListener(this);

        subPanel.add(firstScreen);
        subPanel.add(quit);
        add(subPanel, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }


    // EFFECTS: if click "RETURN" command button, it goes back to first screen
    //          if it click "QUIT" command button, it shows save screen
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("RETURN")) {
            new FirstScreen(graphicApp);
        } else if (e.getActionCommand().equals("QUIT")) {
            new SaveScreen(graphicApp);
        }
    }
}
