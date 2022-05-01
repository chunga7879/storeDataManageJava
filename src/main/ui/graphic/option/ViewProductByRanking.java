package ui.graphic.option;

import ui.GraphicApp;
import ui.graphic.FirstScreen;
import ui.graphic.SaveScreen;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewProductByRanking  extends JFrame implements ActionListener {
    TextArea textArea;
    GraphicApp graphicApp;
    protected JButton firstScreen;
    protected JButton quit;

    // EFFECTS: Constructor
    public ViewProductByRanking(GraphicApp graphicApp) {
        this.graphicApp = graphicApp;
        textArea = new TextArea(15,80);
        textArea.setEditable(false);
        setTitle("View Product List by Ranking");

        doViewListOfProductByRanking();
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

    // EFFECTS: view product information by ranking of amount of product sold
    public void doViewListOfProductByRanking() {
        int[] rank = new int[graphicApp.productList.getProductList().size()];
        for (int i = 0; i < graphicApp.productList.getProductList().size(); i++) {
            rank[i] = 1;
        }

        for (int i = 0; i < rank.length; i++) {
            for (int j = 0; j < graphicApp.productList.getProductList().size(); j++) {
                if (graphicApp.productList.getProductList().get(i).getNumberOfSold()
                        < graphicApp.productList.getProductList().get(j).getNumberOfSold()) {
                    rank[i]++;
                }
            }
        }

        int[] index = new int[rank.length];
        for (int i = 0; i < rank.length; i++) {
            index[rank[i] - 1] = i;
        }

        for (int i = 0; i < rank.length; i++) {
            int t = index[i];
            textArea.append(rank[t] + " : " + graphicApp.productList.getProductList().get(t).toString());
            textArea.append("\n");
        }

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
