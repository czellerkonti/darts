package darts;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class BevitelPanel extends JPanel{
    JTextField tf_elsõ_dobás = new JTextField("0");
    JTextField tf_második_dobás = new JTextField("0");
    JTextField tf_harmadik_dobás = new JTextField("0");
    JRadioButton rb_elsõ[] = new JRadioButton[3];
    JRadioButton rb_második[] = new JRadioButton[3];
    JRadioButton rb_harmadik[] = new JRadioButton[3];
    GridLayout gl = new GridLayout();
    ButtonGroup elsõ = new ButtonGroup();
    ButtonGroup második = new ButtonGroup();
    ButtonGroup harmadik = new ButtonGroup();
    JButton ok1 = new JButton();
    JButton ok = new JButton();
    Főablak fõablak;
    int aktuálisJátékos = 0;

    public BevitelPanel(Főablak fõablak) {
        gl.setColumns(3);
        gl.setRows(5);
        tf_elsõ_dobás.addFocusListener(new JTextFieldFocusListener(tf_elsõ_dobás));
        tf_második_dobás.addFocusListener(new JTextFieldFocusListener(tf_második_dobás));
        tf_harmadik_dobás.addFocusListener(new JTextFieldFocusListener(tf_harmadik_dobás));
        tf_elsõ_dobás.addKeyListener(new JTextFieldKeyListener(tf_elsõ_dobás));
        tf_második_dobás.addKeyListener(new JTextFieldKeyListener(tf_második_dobás));
        tf_harmadik_dobás.addKeyListener(new JTextFieldKeyListener2(tf_harmadik_dobás,ok));
        this.setLayout(gl);
        this.add(tf_elsõ_dobás);
        this.add(tf_második_dobás);
        this.add(tf_harmadik_dobás);
        for (int i = 0;i<=2;i++) {
            rb_elsõ[i] = new JRadioButton(i+1+"x");
            rb_elsõ[i].setMnemonic(i+1);
            rb_elsõ[i].setName(Integer.toString(i+1));
            rb_második[i] = new JRadioButton(i+1+"x");
            rb_második[i].setMnemonic(i+1);
            rb_második[i].setName(Integer.toString(i+1));
            rb_harmadik[i] = new JRadioButton(i+1+"x");
            rb_harmadik[i].setMnemonic(i+1);
            rb_harmadik[i].setName(Integer.toString(i+1));
            elsõ.add(rb_elsõ[i]);
            második.add(rb_második[i]);
            harmadik.add(rb_harmadik[i]);
            this.add(rb_elsõ[i]);
            this.add(rb_második[i]);
            this.add(rb_harmadik[i]);
        }
        rb_elsõ[0].setSelected(true);
        rb_második[0].setSelected(true);
        rb_harmadik[0].setSelected(true);
        this.add(ok1);

        ok1.setVisible(false);
        ok.setText("OK");
        ok.addActionListener(new OKButtonListener(this,fõablak.getJátékosok()));
        this.add(ok);
        this.fõablak = fõablak;

    }

    public void tfReset(){
       tf_elsõ_dobás.setText("0");
       tf_második_dobás.setText("0");
       tf_harmadik_dobás.setText("0");

    }

    public void reset(){
        rb_elsõ[0].setSelected(true);
        rb_második[0].setSelected(true);
        rb_harmadik[0].setSelected(true);
        //aktuálisJátékos = 0;
        System.out.println("Reset kész");
        ok.setEnabled(true);
        tf_elsõ_dobás.setText("0");
        tf_második_dobás.setText("0");
        tf_harmadik_dobás.setText("0");
    }

    public int getJátékosok(){
        return fõablak.getJátékosok();
    }

    public int getAktuálisJátékos(){
        return aktuálisJátékos;
    }

    public void setAktuálisJátékos(int x){
        aktuálisJátékos = x;
    }

    public void setOKButtonEnabled(boolean b){
        ok.setEnabled(b);
    }

    public void levon(){
        int dobottPont = 0;
        int szorzó = 1;
        dobottPont = elsõ.getSelection().getMnemonic()*Integer.parseInt(tf_elsõ_dobás.getText()) +
                     második.getSelection().getMnemonic()*Integer.parseInt(tf_második_dobás.getText()) +
                     harmadik.getSelection().getMnemonic()*Integer.parseInt(tf_harmadik_dobás.getText());

        fõablak.levon(aktuálisJátékos,dobottPont);
        aktuálisJátékos++;
        System.out.println("A "+ aktuálisJátékos +".játékos "+dobottPont+" pontot dobott.");
    }
}

class JTextFieldKeyListener implements KeyListener{
    JTextField tf;

    public JTextFieldKeyListener(JTextField  tf){
        this.tf = tf;
    }

    public void keyTyped(KeyEvent e){

    }

    public void keyReleased(KeyEvent e){

    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == 10){
            tf.nextFocus();

        }
    }
}

class JTextFieldKeyListener2 implements KeyListener{
    JTextField tf;
    JButton jb;

    public JTextFieldKeyListener2(JTextField  tf,JButton jb){
        this.tf = tf;
        this.jb = jb;
    }

    public void keyTyped(KeyEvent e){

    }

    public void keyReleased(KeyEvent e){

    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == 10){
            jb.doClick();
        }
    }
}

class JTextFieldFocusListener implements FocusListener{
    JTextField tf;

    public JTextFieldFocusListener(JTextField tf){
       this.tf = tf;
   }

   public void focusLost(FocusEvent e){

   }

   public void focusGained(FocusEvent e){
       tf.setSelectionStart(0);
       tf.setSelectionEnd(tf.getText().length());
   }

}

class OKButtonListener implements ActionListener{
    BevitelPanel bevitelPanel;
    int játékosok;


    public OKButtonListener(BevitelPanel bevitelPanel,int játékosok){
         this.bevitelPanel = bevitelPanel;
         this.játékosok = játékosok;
    }

    public void actionPerformed(ActionEvent e) {
        játékosok = bevitelPanel.getJátékosok();
        if (bevitelPanel.getAktuálisJátékos() == játékosok) {
            bevitelPanel.setAktuálisJátékos(0);
            System.out.println("Következõ kör !");
        }

        bevitelPanel.levon();
        bevitelPanel.reset();
    }
}

