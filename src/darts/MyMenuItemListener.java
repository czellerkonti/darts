package darts;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyMenuItemListener implements ActionListener {
    BeállításAblak beállítás;


    public MyMenuItemListener(BeállításAblak ba) {
        beállítás = ba;
    }

    public void actionPerformed (ActionEvent e){

        if (e.getActionCommand() == "about") {
            new AboutAblak();
        }

        if (e.getActionCommand() == "Beállítás" ){
            beállítás.setVisible(true);


        }

        if (e.getActionCommand() == "Új játék") {
            beállítás.reset();

        }
    }
}
