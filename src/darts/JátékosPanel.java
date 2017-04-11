package darts;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Color;


public class JátékosPanel extends JPanel {
    JTextField név = new JTextField();
    JLabel pont = new JLabel();
    Főablak fõablak;

    public JátékosPanel(String n,int p,Főablak fõablak) {
        pont.setText(Integer.toString(p));
        név.setText(n);
        név.setSelectionColor(Color.blue);
        név.setToolTipText("Faszkalap játékos");
        név.setEditable(false);
        név.addFocusListener(new TextFieldFocusListener(név,fõablak));
        név.addKeyListener(new TextFieldKeyListener(név,fõablak));
        this.add(név);
        this.add(pont);
    }

    public void setText(String str) {
        pont.setText(str);
    }

    public int getPont() {
        return Integer.parseInt(pont.getText());
    }

    public String getNév() {
        return név.getText();
    }
}

class TextFieldKeyListener implements KeyListener{
    JTextField textfield;
    Főablak fõablak;
    public TextFieldKeyListener(JTextField textfield,Főablak fõablak){
        this.textfield = textfield;
        this.fõablak = fõablak;
    }

    public void keyTyped(KeyEvent e){
        fõablak.pack();
    }

    public void keyReleased(KeyEvent e){
    }

    public void keyPressed(KeyEvent e){
    }
}

class TextFieldFocusListener implements FocusListener {
    JTextField textfield;
    Főablak fõablak;

    public TextFieldFocusListener(JTextField textfield,Főablak fõablak){
        this.textfield = textfield;
        this.fõablak = fõablak;
    }

    public void focusLost(FocusEvent e){
        textfield.setEditable(false);
        textfield.setSelectionStart(0);
        textfield.setSelectionEnd(0);
        fõablak.pack();
    }

    public void focusGained(FocusEvent e){
        textfield.setEditable(true);
        textfield.setSelectionStart(0);
        textfield.setSelectionEnd(textfield.getWidth());
    }
}
