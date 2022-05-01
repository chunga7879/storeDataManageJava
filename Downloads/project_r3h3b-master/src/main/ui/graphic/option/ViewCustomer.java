package ui.graphic.option;

import ui.GraphicApp;
import ui.graphic.FirstScreen;
import ui.graphic.SaveScreen;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewCustomer extends JFrame implements ActionListener {
    TextArea textArea;
    GraphicApp graphicApp;
    protected JButton firstScreen;
    protected JButton quit;

    // EFFECTS: Constructor
    public ViewCustomer(GraphicApp graphicApp) {
        this.graphicApp = graphicApp;
        textArea = new TextArea(15,80);
        textArea.setEditable(false);

        silverText(graphicApp);
        goldText(graphicApp);
        vipText(graphicApp);

        add(textArea, BorderLayout.NORTH);
        setTitle("View Customer List");

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

    // EFFECTS: print out vip customers information
    private void vipText(GraphicApp graphicApp) {
        textArea.append("VIP: ");
        textArea.append("\n");

        for (int i = 0; i < graphicApp.customerList.getVipCustomerList().size(); i++) {
            textArea.append(graphicApp.customerList.getVipCustomerList().get(i).toString());
            textArea.append("\n");

        }
        textArea.append("\n");
        textArea.append("\n");
    }

    // EFFECTS: print out gold customers information
    private void goldText(GraphicApp graphicApp) {
        textArea.append("Gold: ");
        textArea.append("\n");
        for (int i = 0; i < graphicApp.customerList.getGoldCustomerList().size(); i++) {
            textArea.append(graphicApp.customerList.getGoldCustomerList().get(i).toString());
            textArea.append("\n");
        }
        textArea.append("\n");
        textArea.append("\n");
    }

    // EFFECTS: print out silver customers information
    private void silverText(GraphicApp graphicApp) {
        textArea.append("Silver: ");
        textArea.append("\n");
        for (int i = 0; i < graphicApp.customerList.getSilverCustomerList().size(); i++) {
            textArea.append(graphicApp.customerList.getSilverCustomerList().get(i).toString());
            textArea.append("\n");

        }
        textArea.append("\n");
        textArea.append("\n");
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
