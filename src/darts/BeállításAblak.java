package darts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.NumberFormatException;

public class BeállításAblak extends JFrame{


    JLabel játékosokLabel = new JLabel("Játékosok száma: ");
    JLabel pontszámLabel = new JLabel("Pontszám: ");
    JComboBox játékosokSzáma = new JComboBox();
    JTextField pontszám = new JTextField("0");
    JPanel cp;
    JPanel cp_kontroll = new JPanel();
    JPanel cp_értékek = new JPanel();
    GridLayout gl_értékek = new GridLayout();
    FlowLayout fl_kontroll = new FlowLayout();
    GridLayout gl = new GridLayout();
    JButton bt_ok = new JButton("OK");
    JButton bt_cancel = new JButton("Mégse");
    Főablak fõablak;


    public BeállításAblak(Főablak fõablak) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setTitle("Beállítások");
        setLocation( screenSize.height/2, screenSize.width/2 );
        setResizable(false);
        this.fõablak = fõablak;
        gl.setColumns(1);
        gl.setRows(2);
        gl_értékek.setColumns(2);
        gl_értékek.setRows(2);
        cp = (JPanel) getContentPane();
        cp.setLayout(gl);
        cp_értékek.setLayout(gl_értékek);
        cp_kontroll.setLayout(fl_kontroll);
        cp.add(cp_értékek);
        cp.add(cp_kontroll);
        for (int i=1;i<6;i++){
            játékosokSzáma.addItem( Integer.toString(i) );
        }
        cp_értékek.add(játékosokLabel);
        cp_értékek.add(játékosokSzáma);
        cp_értékek.add(pontszámLabel);
        cp_értékek.add(pontszám);
        cp_kontroll.add(bt_ok);
        cp_kontroll.add(bt_cancel);
        bt_ok.addActionListener(new BeállításButtonListener(fõablak,this));
        bt_cancel.addActionListener(new BeállításButtonListener(fõablak,this));

        pack();

        setVisible(false);
    }

    public int getJátékosok(){
        return Integer.parseInt((String)játékosokSzáma.getSelectedItem());
    }


    public void reset(){
          System.out.println("------------------------------------------");
           fõablak.dinamikusCuccLevétel();
           try{
           fõablak.setPont(Integer.parseInt(pontszám.getText()));
           }catch(NumberFormatException ex){
               System.out.println("Túl nagy szám !");
               fõablak.setPont(301);
           }
           fõablak.setJátékosok(getJátékosok());
           fõablak.bevitelPanelReset();
           fõablak.setBeállításAblakVisible(false);
           fõablak.dinamikusCuccFelrakás();

    }



}

class BeállításButtonListener implements ActionListener{
    Főablak fõablak;
    BeállításAblak beállításablak;

    public BeállításButtonListener(Főablak fõablak,BeállításAblak beállításablak){
        this.fõablak = fõablak;
        this.beállításablak = beállításablak;
    }
    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand() == "OK"){
          beállításablak.reset();

        }
        if (e.getActionCommand() == "Mégse"){
           fõablak.setBeállításAblakVisible(false);

        }


    }

}
