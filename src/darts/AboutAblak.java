package darts;

import javax.swing.*;
import java.awt.*;

public class AboutAblak extends JFrame {
    JPanel cp;
    JLabel label = new JLabel("Kontika");
    BorderLayout bl = new BorderLayout();


    public AboutAblak() {
        cp = (JPanel) getContentPane();
        cp.setLayout(bl);
        this.setTitle("about");
        cp.add(label);
        pack();
        setVisible(true);
    }


}
