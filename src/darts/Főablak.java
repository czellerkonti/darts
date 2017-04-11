package darts;

import java.awt.GridLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.lang.Object;

public class Főablak extends JFrame {

    JPanel contentPane;
    GridLayout gridLayout1 = new GridLayout();
    JMenuBar menu = new JMenuBar();
    JMenu súgó_menu = new JMenu("Súgó");
    JMenu fájl_menu = new JMenu("Menü");
    JMenuItem beállítás = new JMenuItem("Beállítás");
    JMenuItem about = new JMenuItem("about");
    JMenuItem újJáték = new JMenuItem("Új játék");
    int játékosok = 2;
    int pontszám = 301;
    JPanel jp_játékosok = new JPanel();
    Főablak a;
    JLabel aktuális_játékos = new JLabel("Aktuális");
    JátékosPanel jp_játékos[] = new JátékosPanel[5];
    BevitelPanel bevitel = new BevitelPanel(this);
    BeállításAblak beállításablak = new BeállításAblak(this);

    public Főablak() {
        try {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void jbInit() {
         int i = 0;
         // Fõablak felosztás
         contentPane = (JPanel) getContentPane();
         gridLayout1.setColumns(1);
         gridLayout1.setRows(3);
         contentPane.setLayout(gridLayout1);
         // menü flerakása
         setJMenuBar(menu);
         menu.add(fájl_menu);
         menu.add(súgó_menu);
         fájl_menu.add(újJáték);
         újJáték.addActionListener(new MyMenuItemListener(beállításablak));
         fájl_menu.add(beállítás);
         //menu listenerek
         beállítás.addActionListener(new MyMenuItemListener(beállításablak));
         súgó_menu.add(about);
         about.addActionListener(new MyMenuItemListener(beállításablak));
         this.dinamikusCuccFelrakás();
         // megrajzolás
         pack();
         setTitle("Darts");

    }

    public int getJátékosok(){
       return játékosok;
   }


    public void setJátékosok(int j){
        játékosok = j;
        System.out.println("Játékosok száma beállítva: "+játékosok);
    }


   public int getPont(){
       return pontszám;
   }

    public void setPont(int p){
        pontszám = p;
        System.out.println("Pontszám beállítva: " + pontszám);
    }

    public void dinamikusCuccLevétel() {
        int i = 0;
        System.out.println(játékosok+"db-ot kell levenni. Levétel indul...");
        for (i=0;i!=játékosok;i++){
            System.out.print("---"+i+". levétel...");
            jp_játékosok.remove(jp_játékos[i]);
            System.out.println("kész");
            jp_játékos[i] = null;
            System.gc();

        }
        pack();
        this.repaint();
        System.out.println("Levétel sikerült");

    }

    public void dinamikusCuccFelrakás() {
        boolean ujraKellRajzolni = false;
        System.out.println("Tömb: "+jp_játékos.length);
        System.out.println(játékosok+"db-ot kell felrakni. Felrakás indul...");
        for (int i = 0;i!=játékosok;i++){
          System.out.print("---"+i+". felrakás...");
          jp_játékos[i] = new JátékosPanel(Integer.toString(i+1), pontszám,this);
          jp_játékosok.add(jp_játékos[i]);
          System.out.println("kész");
          ujraKellRajzolni = true;
        }
        if (ujraKellRajzolni){

            contentPane.add(jp_játékosok);
            contentPane.add(bevitel);
            contentPane.add(aktuális_játékos);
            aktuális_játékos.setText(jp_játékos[2].getNév());

            System.out.println("Felrakás sikerült");
        }
        pack();
        this.repaint();
    }

    public void setBeállításAblakVisible(boolean b){
        beállításablak.setVisible(b);
    }

    public void bevitelPanelReset(){
        bevitel.reset();
    }

    public void levon(int aktuálisJátékos,int pont){
      int ujpont;
      /*System.out.println(jp_játékos[aktuálisJátékos].getPont() - pont);*/
      if ((jp_játékos[aktuálisJátékos].getPont()-pont >= 0) ) {
          ujpont = jp_játékos[aktuálisJátékos].getPont() - pont;
          jp_játékos[aktuálisJátékos].setText(Integer.toString(ujpont));
      }
      else{
          jp_játékos[aktuálisJátékos].setText(Integer.toString(pontszám));
      }
      if (jp_játékos[aktuálisJátékos].getPont() == 0) {
          System.out.println(aktuálisJátékos+" nyert !");
          bevitel.setOKButtonEnabled(false);
          System.out.println("gomb kilõve");
      }
  }

}
